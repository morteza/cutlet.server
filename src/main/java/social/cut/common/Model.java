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
import java.util.Map;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Model {

  protected ObjectId _id;
  
  public Date createdAt;
  public Date modifiedAt;

  @JsonGetter("_id")
  public String get_id() {
    return (_id==null)?null:_id.toHexString();
  }
  
  //FIXME use more robust methods!
  @JsonSetter("_id")
  public void set_id(Object _id) {
    String oid = null;
    if (_id==null)
      return;
    if (_id instanceof Map<?,?>) {
      oid = ((Map<String, String>)_id).get("$oid");
    } else if (_id instanceof String) {
      oid = (String)_id;
    }
    this._id = new ObjectId(oid);
  }
}
