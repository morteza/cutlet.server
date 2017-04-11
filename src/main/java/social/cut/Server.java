package social.cut;

import java.security.MessageDigest;
import java.util.ArrayList;

import com.google.inject.Guice;
import com.google.inject.Injector;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.JWTAuthHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTOptions;
import io.vertx.ext.auth.mongo.MongoAuth;
import social.cut.auth.AuthUtils;
import social.cut.cms.CMSController;
import social.cut.inbox.InboxController;
import social.cut.utils.DeploymentOptionsUtils;

public class Server extends AbstractVerticle {

  private final Logger LOG = LoggerFactory.getLogger(Server.class);

  private MongoClient mongo;
  @Override
  public void start() {

    // Load configs from file
    JsonObject confs = DeploymentOptionsUtils.readConfigJsonResource();
    DeploymentOptions options = new DeploymentOptions().setConfig(confs);
        
    Router router = Router.router(vertx);

    mongo = initMongo(confs.getJsonObject("mongo"));
    
    addCorsHandler(router);
    addStaticHandler(router);

    Injector injector = Guice.createInjector(new CutletModule(vertx, router, mongo));

    injector.getInstance(AuthUtils.class).addAuthHandler();

    vertx.deployVerticle(injector.getInstance(CMSController.class), options);
    vertx.deployVerticle(injector.getInstance(InboxController.class), options);

    //TODO read port from config file.
    vertx.createHttpServer().requestHandler(router::accept)
      .listen(8080, res -> {
        if (res.succeeded())
          LOG.info("Server started...");
        else
          LOG.error("Server failed to start...");
      });
  }
  
  private MongoClient initMongo(JsonObject configs) {
    MongoClient client = MongoClient.createShared(vertx, configs);
    return client;
  }

  private Router addCorsHandler(Router router) {
    router.route().handler(CorsHandler.create("*")
        .allowedMethod(HttpMethod.GET)
        .allowedMethod(HttpMethod.POST)
        .allowedMethod(HttpMethod.OPTIONS)
        .allowedHeader("Content-Type"));
    return router;
  }

  private Router addStaticHandler(Router router) {
    router.route("/assets/*").handler(StaticHandler.create());
    return router;
  }

}
