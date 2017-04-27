/*******************************************************************************
 *        File: Find.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Apr 27, 2017
 *     Project: socia.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.common.handler;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.inject.Inject;

import de.braintags.io.vertx.pojomapper.dataaccess.query.IQuery;
import de.braintags.io.vertx.pojomapper.dataaccess.query.IQueryResult;
import de.braintags.io.vertx.pojomapper.mongo.MongoDataStore;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import social.cut.common.Model;

public class Find<T extends Model> implements Handler<RoutingContext> {

  public final Logger LOG = LoggerFactory.getLogger(Find.class);

  private Class<T> cls;
  
  @Inject
  private MongoDataStore store;
  
  public Find(Class<T> cls) {
    this.cls = cls;
  }
  
  @Override
  public void handle(RoutingContext ctx) {
    JsonObject query = getQueryParams(ctx);
    JsonObject sort = getSortParams(ctx);
    
    handle(query, sort, res-> {
      if (res.succeeded()) {
        ctx.response().end(res.result().encode());
      } else {
        LOG.error(res.cause());
        ctx.fail(res.cause());
      }
    });
    
  }
  
  public void handle(JsonObject query, JsonObject sort, Handler<AsyncResult<JsonObject>> resultHandler) {
    
    IQuery<T> q = store.createQuery(cls);
    
    // Sorting...
    q.addSort(sort.getString("orderBy","createdAt"), sort.getBoolean("asc",false));
    q.setLimit(sort.getInteger("limit",25));
    int offset = sort.getInteger("page", 0) * sort.getInteger("limit", 25);
    q.setStart(offset);

    // Default general query (id is not null)
    q.field(q.getMapper().getIdField().getName()).isNot(null);

    Iterator<Map.Entry<String, Object>> it = query.iterator();
    while(it.hasNext()) {
      Entry<String, Object> param = it.next();
      String key = param.getKey();
      q.field(key).is(param.getValue());
    }

    q.execute(res -> {
      if (res.succeeded()) {
        IQueryResult<T> qr = res.result();
        qr.toArray(ar -> {
          if (ar.succeeded()) {
            count(query, cnt -> {
              if (cnt.succeeded()) {
                JsonObject json = new JsonObject();
                
                // Put data, count, and total number of items
                json.put("data", res.result());
                json.put("count", res.result().size());
                json.put("total", cnt.result());
                
                // Calc and update page value
                Long limit = Long.valueOf(sort.getInteger("limit", 25));
                Long page = Long.valueOf(sort.getInteger("page", 0));
                if (page<0)
                  page = 0l;
                if (page>(cnt.result()/limit)+1)
                  page = 1 + (cnt.result()/limit);
                json.put("page", page+1);
                
                // Finished!
                resultHandler.handle(Future.succeededFuture(json));
              } else {
                // failed to count()
                resultHandler.handle(Future.failedFuture(cnt.cause()));
              }
            });
          } else {
            // Failed to retrieve array of results.
            resultHandler.handle(Future.failedFuture(ar.cause()));
          }
        });

      } else {
        // Failed to query
        resultHandler.handle(Future.failedFuture(res.cause()));
      }
    });
  }
  
  public void count(JsonObject query, Handler<AsyncResult<Long>> resultHandler) {
    
    IQuery<T> total = store.createQuery(cls);

    // Default general query (id is not null)
    total.field(total.getMapper().getIdField().getName()).isNot(null);

    Iterator<Map.Entry<String, Object>> it = query.iterator();
    while(it.hasNext()) {
      Entry<String, Object> param = it.next();
      String key = param.getKey();
      total.field(key).is(param.getValue());
    }

    // Count TOTAL without pagination and stuff
    total.executeCount(cnt -> {
      if (cnt.succeeded()) {
        long tot = cnt.result().getCount();
        resultHandler.handle(Future.succeededFuture(tot));
      } else {
        resultHandler.handle(Future.failedFuture(cnt.cause()));
      }
    });
  }
  
  public static JsonObject getSortParams(RoutingContext ctx) {
    String order = ctx.request().getParam("order");
    String orderBy = ctx.request().getParam("orderBy");
    String page = ctx.request().getParam("page");
    String limit = ctx.request().getParam("limit");
    
    JsonObject json = new JsonObject();
        
    json.put("asc",(order!="asc")?false:true);
    if (page!=null) json.put("page",Integer.valueOf(page)-1);
    if (limit!=null) json.put("limit", Integer.valueOf(limit));
    json.put("orderBy",(orderBy==null)?"createdAt":orderBy);
    
    return json;
  }
  
  public static JsonObject getQueryParams(RoutingContext ctx) {
    JsonObject query = new JsonObject();
    
    List<String> sortKeys = Arrays.asList("order","orderby","page","limit");

    List<Entry<String, String>> params = ctx.request().params().entries();
    for (Entry<String, String> param: params) {
      String key = param.getKey();
      if (sortKeys.contains(key.toLowerCase())) // ignore sort query params
        continue;
      query.put(key, param.getValue());
    }
    
    return query;

  }

}

