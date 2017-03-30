/*******************************************************************************
 *        File: DeploymentOptionsUtils.java
 *    Revision: 1
 * Description: Utility for deployment configurations
 *      Author: Morteza Ansarinia <ansarinia@me.com>
 *  Created on: Nov 14, 2016
 *     Project: social.cut.server
 *   Copyright: See the file "LICENSE" for the full license governing this code.
 *******************************************************************************/
package social.cut.utils;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

import io.vertx.core.impl.Args;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class DeploymentOptionsUtils {

  private static final Logger LOG = LoggerFactory.getLogger(DeploymentOptionsUtils.class);
 
  public static JsonObject readJsonConfigFile(String... sargs) {
    Args args = new Args(sargs);
    String confArg = args.map.get("-conf");
    JsonObject conf;
    
    if (confArg != null) {
      try (Scanner scanner = new Scanner(new File(confArg)).useDelimiter("\\A")){
        String sconf = scanner.next();
        try {
          conf = new JsonObject(sconf);
        } catch (DecodeException e) {
          LOG.error("Configuration file " + sconf + " does not contain a valid JSON object");
          return null;
        }
      } catch (Exception e) {
        try {
          conf = new JsonObject(confArg);
        } catch (DecodeException e2) {
          LOG.error("-conf option does not point to a file and is not valid JSON: " + confArg);
          return null;
        }
      }
    } else {
      conf = null;
    }
    return conf;
  }
  
  public static JsonObject readConfigJsonResource() {
    JsonObject conf = null;
    
    InputStream stream = DeploymentOptionsUtils.class.getClass().getResourceAsStream("/config.dev.json");
    if (stream != null) {
      try (Scanner scanner = new Scanner(stream).useDelimiter("\\A")){
        String sconf = scanner.next();
        try {
          conf = new JsonObject(sconf);
        } catch (DecodeException e) {
          LOG.error("Invalid JSON configuration file; cannot decode it as JSON object.");
          return null;
        }
      } catch (Exception e) {
        LOG.error("Invalid configuration resource.");
        return null;
      }
    }
    
    return conf;
  }
}