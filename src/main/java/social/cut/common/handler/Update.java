/*******************************************************************************
 *        File: Update.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Apr 27, 2017
 *     Project: socia.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.common.handler;

import org.apache.http.HttpStatus;

import com.google.inject.Inject;

import de.braintags.io.vertx.pojomapper.annotation.Entity;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.mongo.UpdateOptions;
import io.vertx.ext.web.RoutingContext;
import social.cut.common.Model;

public class Update<T extends Model> implements Handler<RoutingContext> {

  public final Logger LOG = LoggerFactory.getLogger(Update.class);

  private Class<T> cls;
  
  @Inject
  private MongoClient mongo;
  
  public Update(Class<T> cls) {
    this.cls = cls;
  }
  
  @Override
  public void handle(RoutingContext ctx) {
    String id = ctx.request().getParam("id");

    //TODO JsonObject query = new JsonObject().put("_id", new JsonObject().put("$oid", id));
    JsonObject query = new JsonObject().put("_id", id);

    JsonObject update = ctx.getBodyAsJson();

    UpdateOptions options = new UpdateOptions().setMulti(false).setUpsert(false);

    String collection = this.getClass().getAnnotation(Entity.class).name();
    mongo.updateCollectionWithOptions(
        collection,
        query,
        update,
        options,
        res -> {
          if(res.succeeded()) {
            ctx.response().end(res.result().toJson().encode());
          } else {
            LOG.error(res.cause());
            ctx.fail(HttpStatus.SC_INTERNAL_SERVER_ERROR);
          }
        });
  }

}

