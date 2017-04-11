/*******************************************************************************
 *        File: AuthUtils.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Apr 11, 2017
 *     Project: cutlet
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.auth;

import java.util.ArrayList;

import com.google.inject.Inject;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTOptions;
import io.vertx.ext.auth.mongo.MongoAuth;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.JWTAuthHandler;

public class AuthUtils {
  
  @Inject
  MongoClient mongo;
  
  @Inject
  Vertx vertx;
  
  @Inject
  Router router;
  
  private final Logger LOG = LoggerFactory.getLogger(AuthUtils.class);
  
  public void addAuthHandler() {
    LOG.info("Vertx: " + vertx);
    LOG.info("Mongo: " + mongo);
    LOG.info("router: " + router);
    
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
    
  }
}
