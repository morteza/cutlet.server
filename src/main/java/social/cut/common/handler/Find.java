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
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;

import com.google.inject.Inject;

import de.braintags.io.vertx.pojomapper.dataaccess.query.IQuery;
import de.braintags.io.vertx.pojomapper.dataaccess.query.IQueryResult;
import de.braintags.io.vertx.pojomapper.mongo.MongoDataStore;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class Find<T> implements Handler<RoutingContext> {

  private Class<T> cls;
  
  @Inject
  private MongoDataStore store;
  
  public Find(Class<T> cls) {
    this.cls = cls;
  }
  
  @Override
  public void handle(RoutingContext ctx) {
    ctx.fail(HttpStatus.SC_NOT_IMPLEMENTED);
  }
  
  public void handle(JsonObject query, JsonObject sort, Handler<AsyncResult<List<T>>> resultHandler) {
    
    IQuery<T> q = store.createQuery(cls);
    
    // Sorting...
    q.addSort(sort.getString("orderBy","createdAt"), sort.getBoolean("ascending",false));
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
            List<T> objs = Arrays.stream(ar.result()).map(e -> (T)e).collect(Collectors.toList());
            resultHandler.handle(Future.succeededFuture(objs));
          } else {
            resultHandler.handle(Future.failedFuture(ar.cause()));
          }
        });

      } else {
        resultHandler.handle(Future.failedFuture(res.cause()));
      }
    });
  }

}

