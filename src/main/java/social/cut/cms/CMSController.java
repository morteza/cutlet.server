/*******************************************************************************
 *        File: CMSController.java
 *    Revision: 3
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 30, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.cms;

import javax.ws.rs.Path;

import com.google.inject.Inject;

import de.braintags.io.vertx.pojomapper.mongo.MongoDataStore;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import social.cut.cms.handler.UploadAttachment;
import social.cut.common.handler.Create;
import social.cut.common.handler.Find;
import social.cut.common.handler.FindById;
import social.cut.common.handler.Delete;
import social.cut.common.handler.Update;

@Path("/api/v1/cms")
public class CMSController extends AbstractVerticle {

  @Inject
  private Router router;
  
  @Inject
  private MongoDataStore store;
  
  @Inject
  private MongoClient mongo;
  
  @Override
  public void start() {
    Router api = Router.router(vertx);
    
    // CRUD
    api.get("/").handler(new Find<Document>(Document.class).setStore(store));
    api.get("/:id").handler(new FindById<Document>(Document.class).setStore(store));
    api.post("/").handler(new Create<Document>(Document.class).setStore(store));
    api.put("/:id").handler(new Update<Document>(Document.class).setMongoClient(mongo));
    api.delete("/:id").handler(new Delete<Document>(Document.class).setStore(store));
    
    api.put("/attachment/:id").handler(new UploadAttachment());
    
    String path = this.getClass().getAnnotation(Path.class).value();
    router.mountSubRouter(path, api);
  }

}
