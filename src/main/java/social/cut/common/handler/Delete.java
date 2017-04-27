/*******************************************************************************
 *        File: Delete.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Apr 27, 2017
 *     Project: socia.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.common.handler;

import org.apache.http.HttpStatus;

import de.braintags.io.vertx.pojomapper.dataaccess.delete.IDelete;
import de.braintags.io.vertx.pojomapper.dataaccess.query.IQuery;
import de.braintags.io.vertx.pojomapper.mongo.MongoDataStore;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import social.cut.common.Model;

public class Delete<T extends Model> implements Handler<RoutingContext> {

  private Class<T> cls;
  
  public final Logger LOG = LoggerFactory.getLogger(Delete.class);
  
  private MongoDataStore store;
  
  public Delete(Class<T> cls) {
    this.cls = cls;
  }
  
  public Delete<T> setStore(MongoDataStore store) {
    this.store = store;
    return this;
  }
  
  @Override
  public void handle(RoutingContext ctx) {

    String id = ctx.request().getParam("id");
    
    IQuery<T> query = store.createQuery(cls);
    IDelete<T> delete = store.createDelete(cls);
    query.field(query.getMapper().getIdField().getName()).is(id);
    delete.setQuery(query);

    delete.delete(res -> {
      if (res.succeeded()) {
        JsonObject json = new JsonObject();
        json.put("result", HttpStatus.SC_OK);
        json.put("deletedInstances", res.result().getDeletedInstances());
        ctx.response().end(json.encode());
      } else {
        LOG.error(res.cause());
        ctx.fail(res.cause());
      }
    }); 
  }
  
}

