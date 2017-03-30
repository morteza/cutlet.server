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
import javax.ws.rs.Path;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import social.cut.utils.ShareableRouter;
import social.cut.utils.annotations.DELETE;
import social.cut.utils.annotations.GET;
import social.cut.utils.annotations.POST;
import social.cut.utils.annotations.PUT;

public class Controller extends AbstractVerticle {
  protected Router router = null;
  protected ShareableRouter sharedRouter = null;
  
  public void start(Future<Void> startFuture) {
    addRoutes();
  }
  
  public void addRoutes() {
    router = Router.router(vertx);
    
    //FIXME this is just  a workaround to store all routes in a single router.
    LocalMap<String, ShareableRouter> routers = vertx.sharedData().getLocalMap("routers");
    sharedRouter = routers.get("main");

    try {
      Class<? extends Controller> clazz = this.getClass();
      Method[] methods = clazz.getMethods();

      for (Method method : methods) {
        Annotation[] annotations = method.getAnnotations();

        for (Annotation annotation : annotations) {
          if (annotation instanceof GET) {
            GET get = (GET) annotation;
            Handler<RoutingContext> handler = createRoutingHandler(method);
            router.get(get.value()).handler(handler);
          } else if (annotation instanceof POST) {
            POST post = (POST) annotation;
            router.post(post.value()).handler(BodyHandler.create());
            Handler<RoutingContext> handler = createRoutingHandler(method);
            router.post(post.value()).handler(handler);
          } else if (annotation instanceof PUT) {
            PUT put = (PUT) annotation;
            router.put(put.value()).handler(BodyHandler.create());
            Handler<RoutingContext> handler = createRoutingHandler(method);
            router.put(put.value()).handler(handler);
          } else if (annotation instanceof DELETE) {
            DELETE delete = (DELETE) annotation;
            Handler<RoutingContext> handler = createRoutingHandler(method);
            router.delete(delete.value()).handler(handler);
          }

        }
        
      }

      Path path = (Path) clazz.getAnnotation(Path.class);
      sharedRouter.getRouter().mountSubRouter(path.value(), router);

    } catch (Throwable t) {
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
