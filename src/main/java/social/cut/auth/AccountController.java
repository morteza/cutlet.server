/*******************************************************************************
 *        File: AccountController.java
 *    Revision: 3
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 29, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.auth;

import com.google.inject.Inject;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import social.cut.common.handler.Create;
import social.cut.common.handler.Find;
import social.cut.common.handler.FindById;
import social.cut.common.handler.Delete;
import social.cut.common.handler.Update;

public class AccountController extends AbstractVerticle {

  @Inject
  Router router;
  
  public void start(Future<Void> future) {
    Router api = Router.router(vertx);

    // CRUD
    api.get("/").handler(new Find<Account>(Account.class));
    api.get("/:id").handler(new FindById<Account>(Account.class));
    api.post("/").handler(new Create<Account>(Account.class));
    api.put("/:id").handler(new Update<Account>(Account.class));
    api.delete("/:id").handler(new Delete<Account>(Account.class));
    
    router.mountSubRouter("/api/v1/accounts", api);
    future.complete();
  }

}
