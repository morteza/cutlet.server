/*******************************************************************************
 *        File: Document.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 29, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.cms;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import social.cut.common.Model;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Document extends Model {
  public String path;
  String title;
  String content;
  String template;
  Date createdAt;
}
