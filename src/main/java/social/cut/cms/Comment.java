/*******************************************************************************
 *        File: Comment.java
 *    Revision: 2
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 29, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.cms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import de.braintags.io.vertx.pojomapper.annotation.Entity;
import social.cut.common.Model;

@Entity(name="cms.comment")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment extends Model {

}
