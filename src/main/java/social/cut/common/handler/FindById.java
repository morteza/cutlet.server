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
import de.braintags.io.vertx.pojomapper.dataaccess.query.IQueryResult;
import de.braintags.io.vertx.pojomapper.mongo.MongoDataStore;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import social.cut.common.Model;

public class FindById<T extends Model> implements Handler<RoutingContext> {

  private Class<T> cls;
  
  @Inject
  private MongoDataStore store;
  
  public FindById(Class<T> cls) {
    this.cls = cls;
  }
  @Override
  public void handle(RoutingContext ctx) {
    String id = ctx.request().getParam("id");

    IQuery<T> query = store.createQuery(cls);

    query.field(query.getMapper().getIdField().getName()).is(id);

    query.execute(res -> {
      if (res.succeeded()) {
        IQueryResult<T> qr = res.result();
        if (qr.isEmpty()) {
          JsonObject json = new JsonObject();
          json.put("result","empty");
          ctx.response().end(json.encode());
          return;
        }
        qr.iterator().next(obj -> {
          JsonObject json = JsonObject.mapFrom(obj.result());
          ctx.response().end(json.encode());
        });

      } else {
        ctx.response().setStatusCode(500).end(res.cause().getMessage());
      }
    });
  }

}
