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

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.google.inject.Inject;

import de.braintags.io.vertx.pojomapper.dataaccess.delete.IDelete;
import de.braintags.io.vertx.pojomapper.dataaccess.query.IQuery;
import de.braintags.io.vertx.pojomapper.dataaccess.query.IQueryResult;
import de.braintags.io.vertx.pojomapper.dataaccess.write.IWrite;
import de.braintags.io.vertx.pojomapper.mongo.MongoDataStore;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import social.cut.common.Controller;

@Path("/api/v1/cms")
public class CMSController extends Controller {

  @Inject
  private MongoDataStore store;
  
  private final Logger LOG = LoggerFactory.getLogger(CMSController.class);
  
  @GET
  @Path("/")
  public void getAll(RoutingContext ctx) {
    IQuery<Document> query = store.createQuery(Document.class);
    
    query.execute(res -> {
      if (res.succeeded()) {
        JsonArray docs = new JsonArray();
        IQueryResult<Document> qr = res.result();
        qr.toArray(ar -> {
          for (Object doc: ar.result()) {
            docs.add(JsonObject.mapFrom((Document)doc));
          }
          ctx.response().end(docs.encode());
        });
          
      } else {
        ctx.response().setStatusCode(500).end(res.cause().getMessage());
      }
    });
    
  }
  
  @GET
  @Path("/:id")
  public void getById(RoutingContext ctx) {
    String id = ctx.request().getParam("id");
    IQuery<Document> query = store.createQuery(Document.class);
    
    query.field(query.getMapper().getIdField().getName()).is(id);
    
    query.execute(res -> {
      if (res.succeeded()) {
        IQueryResult<Document> qr = res.result();
        qr.iterator().next(obj -> {
          JsonObject doc = JsonObject.mapFrom(obj.result());
          ctx.response().end(doc.encode());
        });
          
      } else {
        ctx.response().setStatusCode(500).end(res.cause().getMessage());
      }
    });
  }
  
  //TODO migrate to dataStore
  @PUT
  @Path("/:id")
  public void update(RoutingContext ctx) {
    String id = ctx.request().getParam("id");
    Document document = Json.decodeValue(ctx.getBodyAsString(), Document.class);
    if (document.getId()==null) {
      ctx.response().setStatusCode(404).end();
    }

    JsonObject json = new JsonObject(Json.encode(document));
    json.remove("_id");
/*
    mongo.replaceDocuments(COLLECTION, query, json, res -> {
      if (res.succeeded()) {
        ctx.response().setStatusCode(200).end(res.result().toJson().encode());
      } else {
        ctx.response().setStatusCode(500).end(res.cause().getMessage());
      }
    });
    */
  }
  
  @POST
  @Path("/")
  public void create(RoutingContext ctx) {
    Document document = Json.decodeValue(ctx.getBodyAsString(), Document.class);
    
    IWrite<Document> write = store.createWrite(Document.class);
    write.add(document);
    write.save(res -> {
      if (res.succeeded()) {
        ctx.response().setStatusCode(200).end(""+res.result().iterator().next().getId());
      } else {
        LOG.error("Error: " + res.cause());
        ctx.response().setStatusCode(500).end(res.cause().getMessage());
      };
      
    });
  }
  
  @DELETE
  @Path("/:id")
  public void remove(RoutingContext ctx) {
    String id = ctx.request().getParam("id");
    
    IQuery<Document> query = store.createQuery(Document.class);
    IDelete<Document> delete = store.createDelete(Document.class);
    query.field(query.getMapper().getIdField().getName()).is(id);
    delete.setQuery(query);
    
    delete.delete(res -> {
      if (res.succeeded()) {
        ctx.response().end(""+res.result().getDeletedInstances());
      } else {
        ctx.response().setStatusCode(500).end(res.cause().getMessage());
      }
    });

  }

}
