/*******************************************************************************
 *        File: CMSController.java
 *    Revision: 2
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 30, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.cms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import social.cut.common.Controller;

@Path("/api/v1/cms")
public class CMSController extends Controller {

  //TODO Move this to service
  protected MongoClient mongo;
  private final String COLLECTION = "social.cut.cms";
  
  @Inject
  private Router router;
  
  private final Logger LOG = LoggerFactory.getLogger(CMSController.class);

  @Override
  public void start(Future<Void> future) {
    super.start(future);
    
    JsonObject dbConfig = config().getJsonObject("mongo");
    mongo = MongoClient.createShared(vertx, dbConfig);
    //future.complete();
    LOG.info("MongoDB client initiated");
  }
  
  @GET
  @Path("/")
  public void getAll(RoutingContext ctx) {
    JsonObject query = new JsonObject();
    
    mongo.find(COLLECTION, query, res -> {
      if(res.succeeded()) {
        
        List<Document> documents = 
            res.result().stream()
              .map(m -> Json.mapper.convertValue(m, Document.class))
              .collect(Collectors.toCollection(ArrayList::new));
        
        ctx.response().end(new JsonArray(documents).encodePrettily());
      } else {
        ctx.response().setStatusCode(500).end(res.cause().getMessage());
      }
    });
    
  }
  
  @GET
  @Path("/:id")
  public void getById(RoutingContext ctx) {
    String _id = ctx.request().getParam("id");
    JsonObject query = new JsonObject().put("_id", new JsonObject().put("$oid", _id));
    
    mongo.findOne(COLLECTION, query, null, res -> {
      if(res.succeeded()) {
        Document document = Json.mapper.convertValue(res.result(), Document.class);
        ctx.response().end(Json.encodePrettily(document));
      } else {
        ctx.response().setStatusCode(404).end(res.cause().getMessage());
      }
    });
  }
  
  @PUT
  @Path("/:id")
  public void update(RoutingContext ctx) {
    String _id = ctx.request().getParam("id");
    Document document = Json.decodeValue(ctx.getBodyAsString(), Document.class);
    if (document.get_id()==null) {
      ctx.response().setStatusCode(404).end();
    }

    JsonObject query = new JsonObject().put("_id", _id);

    JsonObject json = new JsonObject(Json.encode(document));
    json.remove("_id");

    mongo.replaceDocuments(COLLECTION, query, json, res -> {
      if (res.succeeded()) {
        ctx.response().setStatusCode(200).end(res.result().toJson().encode());
      } else {
        ctx.response().setStatusCode(500).end(res.cause().getMessage());
      }
    });
    
  }
  
  @POST
  @Path("/")
  public void create(RoutingContext ctx) {
    Document document = Json.decodeValue(ctx.getBodyAsString(), Document.class);
    JsonObject json = new JsonObject(Json.encode(document));
    json.remove("_id");
    
    mongo.save(COLLECTION, json, res -> {
      if (res.succeeded()) {
        ctx.response().setStatusCode(200).end(res.result());
      } else {
        logger.error("Error: " + res.cause());
        ctx.response().setStatusCode(500).end(res.cause().getMessage());
      };
    });
  }
  
  @DELETE
  @Path("/:id")
  public void remove(RoutingContext ctx) {
    String _id = ctx.request().getParam("id");
    JsonObject query = new JsonObject().put("_id", new JsonObject().put("$oid", _id));

    mongo.removeDocument(COLLECTION, query, res -> {
      if(res.succeeded()) {
        ctx.response()
          .end(new JsonObject()
            .put("result", res.result().getRemovedCount())
            .put("status", 200)
            .encodePrettily());
      } else {
        res.cause().printStackTrace();
        ctx.response().setStatusCode(500).end(res.cause().getMessage());
      }
    });
  }

}
