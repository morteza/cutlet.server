/*******************************************************************************
 *        File: AccountController.java
 *    Revision: 2
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 29, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.auth;

import social.cut.common.CRUD;

public class AccountController extends CRUD<Account> {

  public AccountController() {
    super(Account.class);
  }

}
