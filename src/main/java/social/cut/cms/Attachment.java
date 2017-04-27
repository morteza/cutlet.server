/*******************************************************************************
 *        File: Attachment.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Apr 27, 2017
 *     Project: cutlet
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.cms;

import de.braintags.io.vertx.pojomapper.annotation.Entity;
import social.cut.common.Model;

@Entity(name="attachment")
public class Attachment extends Model {
  public String filePath;
}
