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

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.RoutingContext;
import social.cut.common.Controller;

@Path("/api/v1/cms")
public class CMSController extends Controller {

  private MongoClient mongo;
  
  private final Logger logger = LoggerFactory.getLogger(CMSController.class);

  public CMSController() {
    JsonObject config = Vertx.currentContext().config();
    mongo = MongoClient.createShared(Vertx.currentContext().owner(), config);
    logger.debug("MongoClient initiated.");
  }
  
  @GET
  @Path("/")
  public void getAll(RoutingContext ctx) {
    JsonObject query = new JsonObject();
    
    mongo.find("social.cut.cms", query, res -> {
      logger.info("Mongo.find(): " + res.result());
      ctx.response().end("getAll...");
    });
  }
  
  @GET
  @Path("/:id")
  public void get(RoutingContext ctx) {
    String _id = ctx.request().getParam("id");
    JsonObject query = new JsonObject().put("_id", _id);
    
    mongo.findOne("social.cut.cms", query, null, res -> {
      ctx.response().end("get...");
    });
  }
  
  @PUT
  @Path("/:id")
  public void update(RoutingContext ctx) {
    Document document = new Document();//TODO read from ctx.request()
    document.setTitle("Testing...");
    logger.info("Document: " + document.title);
    ctx.response().end("update...");
  }
  
  @POST
  @Path("/")
  public void create(RoutingContext ctx) {
    Document document = new Document();//TODO read from ctx.request()
    document.setTitle("Testing...");
    logger.info("Document: " + document.title);
    ctx.response().end("create...");
  }

}
