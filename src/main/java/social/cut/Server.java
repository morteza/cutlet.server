package social.cut;


import com.github.aesteve.vertx.nubes.NubesServer;

import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class Server extends NubesServer {
			
  private static final Logger LOG = LoggerFactory.getLogger(Server.class);

	@Override
	public void init(Vertx vertx, Context context) {
		super.init(vertx, context);
		nubes = new Application(vertx, context.config());
	}

}
