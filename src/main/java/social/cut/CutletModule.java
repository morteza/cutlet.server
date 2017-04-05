/*******************************************************************************
 *        File: CutletModule.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Apr 05, 2017
 *     Project: cutlet
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut;

import java.util.Properties;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;

public class CutletModule extends AbstractModule {

  private final Vertx vertx;
  private final Router router;
  private final MongoClient mongo;
  private final Context context;

  public CutletModule(Vertx vertx, Router router, MongoClient mongo) {
      this.vertx = vertx;
      this.router = router;
      this.mongo = mongo;
      this.context = vertx.getOrCreateContext();
  }

  @Override
  protected void configure() {
    bind(EventBus.class).toInstance(vertx.eventBus());
    bind(Router.class).toInstance(router);
    bind(MongoClient.class).toInstance(mongo);
    Properties props = toProperties(context.config().getJsonObject("inject"));
    Names.bindProperties(binder(), props);
  }
  
  private Properties toProperties(JsonObject config) {
    Properties properties = new Properties();
    if (config==null)
      return properties;
    config.getMap().keySet().stream().forEach((String key) -> {
        properties.setProperty(key, (String) config.getValue(key));
    });
    return properties;
  }

}
