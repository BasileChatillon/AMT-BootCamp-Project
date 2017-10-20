/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.rest;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Permet de gérer la manière dont la séréalisation se passe.
 * @author basilechatillon
 */
@ApplicationPath("/api")
public class RESTBootCampApplication extends Application{
    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        // peremt d'utiliser moxy dans glassfish pour la séréalisation
        properties.put("jersey.config.disableMoxyJson", true);
        return properties;
    }
}