package social.cut;

import com.github.aesteve.vertx.nubes.VertxNubes;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class Application extends VertxNubes {

  public Application(Vertx vertx, JsonObject config) {
    super(vertx, config);
  }

  private static final Logger LOG = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
  }
}
