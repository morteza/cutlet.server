/*******************************************************************************
 *        File: FindById.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Apr 27, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.common.handler;

import com.google.inject.Inject;

import de.braintags.io.vertx.pojomapper.dataaccess.query.IQuery;
import de.braintags.io.vertx.pojomapper.mongo.MongoDataStore;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class FindById<T> implements Handler<RoutingContext> {

  private Class<T> cls;
  
  @Inject
  private MongoDataStore store;
  
  public FindById(Class<T> cls) {
    this.cls = cls;
  }
  @Override
  public void handle(RoutingContext ctx) {
    
  }
  
  public void handle(String id, Handler<AsyncResult<T>> resultHandler) {
    IQuery<T> dbQuery = store.createQuery(cls);
    
    JsonObject query = new JsonObject().put(dbQuery.getMapper().getIdField().getName(), id);
    JsonObject sort = new JsonObject();
    
    new Find<T>(cls).handle(query, sort, res -> {
      if (res.succeeded()) {
        resultHandler.handle(Future.succeededFuture(res.result().get(0)));
      } else {
        resultHandler.handle(Future.failedFuture(res.cause()));
      }
    });
  }

}
