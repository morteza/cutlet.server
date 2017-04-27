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

import com.google.inject.Inject;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import social.cut.common.handler.Create;
import social.cut.common.handler.Find;
import social.cut.common.handler.FindById;
import social.cut.common.handler.Delete;
import social.cut.common.handler.Update;

public class CMSController extends AbstractVerticle {

  @Inject
  private Router router;
  
  @Override
  public void start() {
    Router api = Router.router(vertx);
    
    api.get("/").handler(new Find<Document>(Document.class));
    api.get("/:id").handler(new FindById<Document>(Document.class));
    api.post("/").handler(new Create<Document>(Document.class));
    api.put("/:id").handler(new Update<Document>(Document.class));
    api.delete("/:id").handler(new Delete<Document>(Document.class));
    
    router.mountSubRouter("/api/v1/cms", api);
  }

}
