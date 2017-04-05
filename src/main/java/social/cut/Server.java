package social.cut;

import com.google.inject.Guice;
import com.google.inject.Injector;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;
import social.cut.cms.CMSController;
import social.cut.inbox.InboxController;
import social.cut.utils.DeploymentOptionsUtils;
import social.cut.utils.ShareableRouter;

public class Server extends AbstractVerticle {

  private final Logger logger = LoggerFactory.getLogger(Server.class);
  private Router router;
  private Injector injector;

  @Override
  public void start() {

    // Load configs from file
    JsonObject confs = DeploymentOptionsUtils.readConfigJsonResource();
    DeploymentOptions options = new DeploymentOptions().setConfig(confs);
        
    router = Router.router(vertx);

    addCorsHandler(router);
    addStaticHandler(router);

    //FIXME this is a workaround to share a single router among all controllers.
    ShareableRouter sr = new ShareableRouter(router);
    LocalMap<String, ShareableRouter> routers = vertx.sharedData().getLocalMap("routers");
    routers.put("main", sr);
    
    injector = Guice.createInjector(new CutletModule(vertx, router));

    vertx.deployVerticle(injector.getInstance(CMSController.class), options);
    vertx.deployVerticle(injector.getInstance(InboxController.class), options);

    //TODO read port from config file.
    vertx.createHttpServer().requestHandler(sr.getRouter()::accept)
      .listen(8080, res -> {
        if (res.succeeded())
          logger.info("Server started...");
        else
          logger.error("Server failed to start...");
      });
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
