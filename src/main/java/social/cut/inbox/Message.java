/*******************************************************************************
 *        File: Message.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 29, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.inbox;

import social.cut.auth.Account;
import social.cut.common.Model;

public class Message extends Model {
  private String subject;
  private String content;
  private Account from;
  private Account to;
}
