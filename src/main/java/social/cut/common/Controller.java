/*******************************************************************************
 *        File: Controller.java
 *    Revision: 1
 * Description: TODO
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Mar 30, 2017
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/

package social.cut.common;

import java.lang.annotation.Annotation;
import java.lang.invoke.LambdaConversionException;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import com.google.inject.Inject;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class Controller extends AbstractVerticle {  
  
  @Inject
  protected Router router;

  public final Logger LOG = LoggerFactory.getLogger(Controller.class);
  
  @Override
  public void start(Future<Void> startFuture) {
    addRoutes(router);
  }
  
  private void addRoutes(Router router) {
    Router apiRouter = Router.router(vertx);
    
    try {
      Class<? extends Controller> clazz = this.getClass();
      Method[] methods = clazz.getMethods();

      String classPath = ((Path)clazz.getAnnotation(Path.class)).value();

      for (Method method : methods) {
        Annotation[] annotations = method.getAnnotations();

        String methodPath = "/"; //Deafult path for the method
        
        Path pathAnnot = (Path)method.getAnnotation(Path.class);
        if (pathAnnot!=null) {
          methodPath = pathAnnot.value();
        }
        
        //TODO use factory method to process annotations
        for (Annotation annotation : annotations) {
          if (annotation instanceof GET) {
            Handler<RoutingContext> handler = createRoutingHandler(method);
            apiRouter.get(methodPath).handler(handler);
          } else if (annotation instanceof POST) {
            Handler<RoutingContext> handler = createRoutingHandler(method);
            apiRouter.post(methodPath).handler(BodyHandler.create());
            apiRouter.post(methodPath).handler(handler);
          } else if (annotation instanceof PUT) {
            Handler<RoutingContext> handler = createRoutingHandler(method);
            apiRouter.put(methodPath).handler(BodyHandler.create());
            apiRouter.put(methodPath).handler(handler);
          } else if (annotation instanceof DELETE) {
            Handler<RoutingContext> handler = createRoutingHandler(method);
            apiRouter.delete(methodPath).handler(handler);
          }
        }
        
      }
      router.mountSubRouter(classPath, apiRouter);

    } catch (Throwable t) {
      t.printStackTrace();
    }

  }
  
  private Handler<RoutingContext> createRoutingHandler(Method method) throws IllegalAccessException, LambdaConversionException, Throwable {
    MethodHandles.Lookup lookup = MethodHandles.lookup();

    return (Handler<RoutingContext>) LambdaMetafactory
    .metafactory(lookup, "handle", MethodType.methodType(Handler.class, getClass()),
        MethodType.methodType(void.class, Object.class), lookup.unreflect(method),
        MethodType.methodType(void.class, RoutingContext.class))
    .getTarget().invoke(this);
  }
  
}
