/*******************************************************************************
 *        File: InboxController.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 30, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.inbox;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import io.vertx.ext.web.RoutingContext;
import social.cut.common.Controller;

@Path("/api/inbox")
public class InboxController extends Controller {

  @GET
  @Path("/test")
  public void test(RoutingContext ctx) {
    ctx.response().end("Thank you!");
  }
}
