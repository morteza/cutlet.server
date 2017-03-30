/*******************************************************************************
 *        File: ShareableRouter.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 30, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.utils;

import io.vertx.core.shareddata.Shareable;
import io.vertx.ext.web.Router;

public class ShareableRouter implements Shareable {
  private Router router;

  public ShareableRouter(Router router) {
    this.router = router;
  }
  
  public Router getRouter() {
    return router;
  }

  public void setRouter(Router router) {
    this.router = router;
  }
  
}
