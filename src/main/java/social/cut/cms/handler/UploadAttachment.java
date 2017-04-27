/*******************************************************************************
 *        File: UploadAttachment.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Apr 27, 2017
 *     Project: cutlet
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.cms.handler;

import java.util.Iterator;
import java.util.Set;

import com.google.inject.Inject;

import de.braintags.io.vertx.pojomapper.dataaccess.query.IQuery;
import de.braintags.io.vertx.pojomapper.dataaccess.write.IWrite;
import de.braintags.io.vertx.pojomapper.mongo.MongoDataStore;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import social.cut.cms.Attachment;

public class UploadAttachment implements Handler<RoutingContext> {

  public final Logger LOG = LoggerFactory.getLogger(UploadAttachment.class);

  @Inject
  private MongoDataStore store;
  
  /**
   * Handle file uploads.
   * id is requred.
   */
  @Override
  public void handle(RoutingContext ctx) {
    String id = ctx.pathParam("id");
    
    Set<FileUpload> fus = ctx.fileUploads();
    Iterator<FileUpload> fusi = fus.iterator();

    if (fus==null || fus.size()==0)
      ctx.response().setStatusCode(403).end();

    IQuery<Attachment> query = store.createQuery(Attachment.class);
    
    query.field(query.getMapper().getIdField().getName()).is(id);
    query.execute(find -> {
      if (find.succeeded() && !find.result().isEmpty()) {
        find.result().iterator().next(find2 -> {
          if (find2.succeeded()) {
            Attachment attachment = find2.result();
            while (fusi.hasNext()) {

              FileUpload fu = fusi.next();
              //Buffer fb = context.vertx().fileSystem().readFileBlocking(fu.uploadedFileName());
              attachment.filePath = fu.uploadedFileName();

              IWrite<Attachment> write = store.createWrite(Attachment.class);
              write.add(attachment);
              
              write.save(res -> {
                if (res.succeeded()) {
                  ctx.response().end(new JsonObject().put("id", attachment.getId()).encode());
                } else {
                  LOG.error(res.cause());
                  ctx.fail(res.cause());
                }
              });

            }

          } else {
            // find2 (conversion) failed
            LOG.error(find2.cause());
            ctx.fail(find2.cause());
          }
        });
      } else {
        // find (fetch) failed or is empty.
        LOG.error(find.cause());
        ctx.fail(find.cause());        
      }
    });
    
  }

}
