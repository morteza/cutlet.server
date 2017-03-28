/*******************************************************************************
 *        File: CMSService.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 29, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.cms;

import io.vertx.core.json.JsonObject;
import social.cut.common.Service;

public class CMSService extends Service {
    
  public static final String ACTION_GET = "get";
  public static final String ACTION_ALL = "all";
  public static final String ACTION_DELETE = "delete";
  public static final String ACTION_UPDATE = "update";
  public static final String ACTION_CREATE = "create";

  public CMSService() {
    this.setName("CMS");
  }
  
  @Override
  public void start() throws Exception {
    vertx.eventBus().<JsonObject>consumer(
        getConsumerAddress(),
        msg -> {
          JsonObject json = msg.body();
        });
  }
}
