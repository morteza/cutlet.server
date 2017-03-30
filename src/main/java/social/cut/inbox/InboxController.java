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

import javax.ws.rs.Path;

import io.vertx.ext.web.RoutingContext;
import social.cut.common.Controller;
import social.cut.utils.annotations.GET;

@Path("/api/inbox")
public class InboxController extends Controller {

  @GET("/test")
  public void test(RoutingContext ctx) {
    ctx.response().end("Thank you!");
  }
}
