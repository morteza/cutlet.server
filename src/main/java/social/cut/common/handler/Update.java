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
  
  public Update<T> setMongoClient(MongoClient client) {
    this.mongo = client;
    return this;
  }

  @Override
  public void handle(RoutingContext ctx) {
    String id = ctx.pathParam("id");
    JsonObject payload = ctx.getBodyAsJson();

    JsonObject query = new JsonObject().put("_id", id);

    JsonObject update = new JsonObject().put("$set", payload);

    UpdateOptions options = new UpdateOptions().setMulti(false).setUpsert(false);

    String collection = cls.getAnnotation(Entity.class).name();
    mongo.updateCollectionWithOptions(
        collection,
        query,
        update,
        options,
        res -> {
          if(res.succeeded()) {
            JsonObject json = new JsonObject().put("result", "success");
            json.put("matched", res.result().getDocMatched());
            json.put("modified", res.result().getDocModified());
            json.put("upsertedId", res.result().getDocUpsertedId());

            ctx.response().end(json.encode());

          } else {
            LOG.error(res.cause());
            ctx.fail(res.cause());
          }
        });
  }

}

