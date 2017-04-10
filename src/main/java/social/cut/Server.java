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
    addAuthProvider(router);

    Injector injector = Guice.createInjector(new CutletModule(vertx, router, mongo));

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
  
  private Router addAuthProvider(Router router) {
    JsonObject config = new JsonObject().put("keyStore", new JsonObject()
        .put("path", "keystore.jceks")
        .put("type", "jceks")
        .put("password", "secret"));
    
    JWTAuth jwt = JWTAuth.create(vertx, config);
    MongoAuth authProvider = MongoAuth.create(mongo, new JsonObject()).setCollectionName("social.cut.auth");

    router.route("/register").handler(ctx-> {
      String username = ctx.request().getParam("username");
      String password = ctx.request().getParam("password");

      authProvider.insertUser(username, password, new ArrayList<String>(), new ArrayList<String>(), res-> {
        LOG.info("User created.");
        ctx.response().end("User created");
      });
    });
    
    router.route("/api/*").handler(JWTAuthHandler.create(jwt));
    
    router.get("/api/v1/me").handler(ctx -> {
      LOG.info(ctx.user());
      ctx.response().end(ctx.user().principal().encodePrettily());
    });
    
    router.get("/login").handler(ctx -> {
      String username = ctx.request().getParam("username");
      String password = ctx.request().getParam("password");

      JsonObject info = new JsonObject()
          .put("username", username)
          .put("password", password);

      authProvider.authenticate(info, res -> {
        if (res.succeeded()) {
          User user = res.result();
          String token = jwt.generateToken(new JsonObject().put("sub",user.principal().getString("username")),new JWTOptions());
          ctx.response().end(token);           
        } else {
          ctx.response().setStatusCode(403).end("Invalid username or password.");
        }
        
      });
      
    });
    
    return router;
  }
  
  public String sha512(String password, String salt){
    String generatedPassword = null;
        try {
             MessageDigest md = MessageDigest.getInstance("SHA-512");
             md.update(salt.getBytes("UTF-8"));
             byte[] bytes = md.digest(password.getBytes("UTF-8"));
             StringBuilder sb = new StringBuilder();
             for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
             }
             generatedPassword = sb.toString();
            } 
           catch (Exception e){
            e.printStackTrace();
           }
        return generatedPassword;
    }

}
