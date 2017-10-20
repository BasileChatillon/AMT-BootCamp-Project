/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services.dao;

import ch.heigvd.amt.amtbootcamp.rest.DogDelete;
import ch.heigvd.amt.amtbootcamp.rest.RESTBootCampApplication;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author basilechatillon
 */
@Stateless
public class DeleteDog implements DeleteDogLocal{

    @Resource(lookup = "java:/jdbc/dogdb")
    private DataSource dataSource;
    
    @Context
    UriInfo uriInfo;
    
    @Override
    public boolean deleteDog(int id) {
        int i = 0;
        
        Connection connection;
        try {
            connection = dataSource.getConnection();
            System.out.println("Schema : " + connection.getSchema());
            System.out.println("Catalog : " + connection.getCatalog());
            
            String query = "DELETE FROM dog WHERE dog_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
                        
            i = pstmt.executeUpdate();
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteDog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (i > 0);
    }

    @Override
    public URI createLinkDeleteDog(DogDTO dog) {
        // Crée l'url de la ressource qu'on vient de créer
        return uriInfo.getBaseUriBuilder()
                      .path(RESTBootCampApplication.class)
                      .path(DogDelete.class)
                      .path(DogDelete.class, "deleteDog")
                      .build(dog.getID());        
    }

    @Override
    public List<URI> createLinksDeleteDogs(List<DogDTO> dogs) {
        return dogs.stream()
                   .map(dog -> createLinkDeleteDog(dog))
                   .collect(Collectors.toList());
    }
    
    @Override
    public List<String> createStringLinksDeleteDogs(List<URI> uris) {
        return uris.stream()
                   .map(uri -> uri.toString())
                   .collect(Collectors.toList());
    }
}
