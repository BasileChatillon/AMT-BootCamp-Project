package ch.heigvd.amt.amtbootcamp.rest;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Permet de gérer la manière dont la sérialisation se passe.
 *
 */
@ApplicationPath("/api")
public class RESTBootCampApplication extends Application {

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        // Permet d'utiliser moxy dans glassfish pour la sérialisation
        properties.put("jersey.config.disableMoxyJson", true);
        return properties;
    }
}
