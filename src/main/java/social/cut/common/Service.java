/*******************************************************************************
 *        File: Service.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 29, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.common;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public abstract class Service extends AbstractVerticle {
  
  private String name;
  protected Logger logger;
  
  public void setName(String name) {
    this.name = name;
    this.logger = LoggerFactory.getLogger(name);
  }
  
  public String getConsumerAddress() {
    return "social.cut.".concat(name.toLowerCase());
  }
}
