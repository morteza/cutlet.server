/*******************************************************************************
 *        File: Create.java
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

import de.braintags.io.vertx.pojomapper.dataaccess.write.IWrite;
import de.braintags.io.vertx.pojomapper.mongo.MongoDataStore;
import io.vertx.core.Handler;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import social.cut.common.Model;

public class Create<T extends Model> implements Handler<RoutingContext> {

  public final Logger LOG = LoggerFactory.getLogger(Create.class);

  private Class<T> cls;
    
  @Inject
  private MongoDataStore store;
  
  public Create(Class<T> cls) {
    this.cls = cls;
  }
  
  @Override
  public void handle(RoutingContext ctx) {
    T obj = Json.decodeValue(ctx.getBodyAsString(), cls);
    
    // To prevent accidental overwrite
    obj.setId(null);
    
    IWrite<T> write = store.createWrite(cls);
    write.add(obj);
    
    write.save(res -> {
      if (res.succeeded()) {
        JsonObject json = new JsonObject();
        json.put("id", res.result().iterator().next().getId());
        ctx.response().end(json.encode());
      } else {
        LOG.error(res.cause());
        ctx.fail(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      }
    }); 
  }
  
}

