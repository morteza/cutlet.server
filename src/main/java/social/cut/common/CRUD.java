/*******************************************************************************
 *        File: CRUD.java
 *    Revision: 2
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Apr 18, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/
package social.cut.common;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import org.apache.http.HttpStatus;

import com.google.inject.Inject;

import de.braintags.io.vertx.pojomapper.annotation.Entity;
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
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.mongo.UpdateOptions;
import io.vertx.ext.web.RoutingContext;

@Path("/api/v1/crud")
public class CRUD<T extends Model> extends Controller {

  @Inject
  protected MongoDataStore store;
  
  @Inject
  protected MongoClient mongo;
  
  private final Logger LOG = LoggerFactory.getLogger("CRUD");
  
  private Class<T> cls;
  
  public CRUD(Class<T> cls) {
    this.cls = cls;
  }
  
  @GET
  @Path("/")
  public void all(RoutingContext ctx) {
    
    IQuery<T> query = store.createQuery(cls);

    query.execute(res -> {
      if (res.succeeded()) {
        JsonArray docs = new JsonArray();
        IQueryResult<T> qr = res.result();
        qr.toArray(ar -> {
          for (Object obj: ar.result()) {
            //FIXME test and if necesssary cast obj to T
            docs.add(JsonObject.mapFrom(obj));
          }
          ctx.response().end(docs.encode());
        });

      } else {
        LOG.error(res.cause());
        ctx.fail(500);
      }
    });
  }
  
  @GET
  @Path("/:id")
  public void get(RoutingContext ctx) {
    String id = ctx.request().getParam("id");

    IQuery<T> query = store.createQuery(cls);

    query.field(query.getMapper().getIdField().getName()).is(id);

    query.execute(res -> {
      if (res.succeeded()) {
        IQueryResult<T> qr = res.result();
        if (qr.isEmpty()) {
          JsonObject json = new JsonObject();
          json.put("result","empty");
          ctx.response().end(json.encode());
          return;
        }
        qr.iterator().next(obj -> {
          JsonObject json = JsonObject.mapFrom(obj.result());
          ctx.response().end(json.encode());
        });

      } else {
        ctx.response().setStatusCode(500).end(res.cause().getMessage());
      }
    });
  }

  /**
   * Creates a new entity.
   * TODO validate request body. 
   * @param ctx
   */
  @POST
  @Path("/")
  public void create(RoutingContext ctx) {
    T obj = Json.decodeValue(ctx.getBodyAsString(), cls);
    obj.setId(null);
    
    IWrite<T> write = store.createWrite(cls);
    write.add(obj);
    
    write.save(res -> {
      if (res.succeeded()) {
        JsonObject json = new JsonObject();
        json.put("id", res.result().iterator().next().getId());
        ctx.response().setStatusCode(200).end(json.encode());
      } else {
        LOG.error(res.cause());
        ctx.fail(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      }
    });    
  }
  
  @PUT
  @Path("/:id")
  public void update(RoutingContext ctx) {
    String id = ctx.request().getParam("id");

    //TODO JsonObject query = new JsonObject().put("_id", new JsonObject().put("$oid", id));
    JsonObject query = new JsonObject().put("_id", id);

    JsonObject update = ctx.getBodyAsJson();

    UpdateOptions options = new UpdateOptions().setMulti(false).setUpsert(false);

    String collection = this.getClass().getAnnotation(Entity.class).name();
    mongo.updateCollectionWithOptions(
        collection,
        query,
        update,
        options,
        res -> {
          if(res.succeeded()) {
            ctx.response().end(res.result().toJson().encode());
          } else {
            LOG.error(res.cause());
            ctx.fail(HttpStatus.SC_INTERNAL_SERVER_ERROR);
          }
        });
  }
  
  /**
   * Removes an entity.
   * TODO check if current user has the authority to remove.
   * @param ctx
   */
  @DELETE
  @Path("/:id")
  public void remove(RoutingContext ctx) {
    String id = ctx.request().getParam("id");
    
    IQuery<T> query = store.createQuery(cls);
    IDelete<T> delete = store.createDelete(cls);
    query.field(query.getMapper().getIdField().getName()).is(id);
    delete.setQuery(query);

    delete.delete(res -> {
      if (res.succeeded()) {
        JsonObject json = new JsonObject();
        json.put("result", HttpStatus.SC_OK);
        json.put("deletedInstances", res.result().getDeletedInstances());
        ctx.response().end(json.encode());
      } else {
        LOG.error(res.cause());
        ctx.fail(HttpStatus.SC_INTERNAL_SERVER_ERROR);
      }
    });    
  }
}
