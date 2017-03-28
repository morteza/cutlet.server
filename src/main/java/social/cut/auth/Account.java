/*******************************************************************************
 *        File: Account.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 29, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.auth;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.AuthProvider;
import io.vertx.ext.auth.User;
import social.cut.common.Model;

public class Account extends Model implements io.vertx.ext.auth.User {
  
  private String fullName;
  private String email;
  
  private String hashedPassword;
  private String token;

  AccountRole role;

  @Override
  public User isAuthorised(String authority, Handler<AsyncResult<Boolean>> resultHandler) {
    return null;
  }

  @Override
  public User clearCache() {
    return null;
  }

  @Override
  public JsonObject principal() {
    JsonObject json = new JsonObject();
    json.put("email", email);
    return json;
  }

  @Override
  public void setAuthProvider(AuthProvider authProvider) { }
  
}
