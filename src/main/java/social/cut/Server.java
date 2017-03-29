package social.cut;

import org.jboss.resteasy.plugins.server.vertx.VertxRequestHandler;
import org.jboss.resteasy.plugins.server.vertx.VertxResteasyDeployment;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import social.cut.cms.CMSController;

public class Server extends AbstractVerticle {

  private final Logger logger = LoggerFactory.getLogger(Server.class);
  private MongoClient mongo;
  
  @Override
  public void start() {
    
    VertxResteasyDeployment deployment = new VertxResteasyDeployment();
    deployment.start();
    deployment.getRegistry().addPerInstanceResource(CMSController.class);
    
    mongo = MongoClient.createShared(vertx, config());
    
    Router router = Router.router(vertx);
    router.route().handler(StaticHandler.create());

    vertx.createHttpServer()
        .requestHandler(new VertxRequestHandler(vertx, deployment)).listen(8080, res -> {
          logger.info("Server started on port " + res.result().actualPort());
        });

  }

}
