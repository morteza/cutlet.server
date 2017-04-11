/*******************************************************************************
 *        File: Document.java
 *    Revision: 2
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 29, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.cms;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import de.braintags.io.vertx.pojomapper.annotation.Entity;
import social.cut.common.Model;

@Entity(name="cms.document")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document extends Model {
  private String path;
  private String title;
  private String content;
  private String template;
  private Date createdAt;
  /**
   * @return the path
   */
  public String getPath() {
    return path;
  }
  /**
   * @param path the path to set
   */
  public void setPath(String path) {
    this.path = path;
  }
  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }
  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }
  /**
   * @return the content
   */
  public String getContent() {
    return content;
  }
  /**
   * @param content the content to set
   */
  public void setContent(String content) {
    this.content = content;
  }
  /**
   * @return the template
   */
  public String getTemplate() {
    return template;
  }
  /**
   * @param template the template to set
   */
  public void setTemplate(String template) {
    this.template = template;
  }
  /**
   * @return the createdAt
   */
  public Date getCreatedAt() {
    return createdAt;
  }
  /**
   * @param createdAt the createdAt to set
   */
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

}
