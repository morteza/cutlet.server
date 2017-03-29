/*******************************************************************************
 *        File: CMSController.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 29, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.cms;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.mongo.MongoClient;

@Path("/api/v1/cms")
public class CMSController {

  //FIXME move to CMS service.
  private MongoClient mongo;
  
  private final Logger logger = LoggerFactory.getLogger(CMSController.class);

  public CMSController() {
    JsonObject config = Vertx.currentContext().config();
    mongo = MongoClient.createShared(Vertx.currentContext().owner(), config);
    logger.debug("MongoClient initiated.");
  }
  
  @GET
  @Path("/")
  @Produces(MediaType.APPLICATION_JSON)
  public void getAll(@Suspended final AsyncResponse response) {
    JsonObject query = new JsonObject();
    
    mongo.find("social.cut.cms", query, res -> {
      logger.info("RESULT: " + res.result());
      response.resume(Response.ok().entity("listing done!").build());
    });
  }
  
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public void get(@Suspended final AsyncResponse response, @PathParam("id") String _id) {
    JsonObject query = new JsonObject().put("_id", _id);
    
    mongo.findOne("social.cut.cms", query, null, res -> {
      logger.info("RESULT: " + res.result());
      response.resume(Response.ok().entity("done!").build());
    });
  }
  
  @POST
  @Path("/")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public void post(@Suspended final AsyncResponse response, @Context Vertx vertx, Document document) {
    logger.info("Document: " + document.title);
    response.resume(Response.ok().build());
  }

}
