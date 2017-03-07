package social.cut;

import java.util.Map;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Model {
  
  protected ObjectId _id;

  @JsonGetter("_id")
  public String get_id() {
    return (_id==null)?null:_id.toHexString();
  }
  
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
