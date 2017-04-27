/*******************************************************************************
 *        File: Model.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 29, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import de.braintags.io.vertx.pojomapper.annotation.field.Id;
import de.braintags.io.vertx.pojomapper.annotation.lifecycle.BeforeSave;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Model {

  @Id
  protected String id;
  
  public Date createdAt;
  public Date modifiedAt;

  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  @BeforeSave
  public void updateTimestamps() {
    if (createdAt==null) {
      this.createdAt = new Date();
    }
    this.modifiedAt = new Date();
  }
}
