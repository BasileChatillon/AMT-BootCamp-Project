/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services.dao;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author basilechatillon
 */
@Stateless
public class CreateDog implements CreateDogLocal {

    @Resource(lookup = "java:/jdbc/dogdb")
    private DataSource dataSource;
    
    @Override
    public DogDTO createDog(Dog dog) {
        
        int dogID = 0;
        DogDTO newDog = null;
        
        Connection connection;
        try {
            connection = dataSource.getConnection();
            System.out.println("Schema : " + connection.getSchema());
            System.out.println("Catalog : " + connection.getCatalog());
            
            String query = "INSERT INTO dog (dog_name, dog_age, dog_weight, dog_quote) VALUE (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, dog.getName());
            pstmt.setInt(2, dog.getAge());
            pstmt.setDouble(3, dog.getWeight());
            pstmt.setString(4, dog.getQuote());
            
            int rs = pstmt.executeUpdate();
            
            // Si le chien a bien été créé, on va tenter de recupéré son ID
            if( rs > 0){
                String query2 = "SELECT LAST_INSERT_ID()";
                PreparedStatement pstmt2 = connection.prepareStatement(query2);
                ResultSet rs2 = pstmt2.executeQuery();
                
                // lorsqu'on réussi à récuprer l'ID, on va créer le chien avec son ID
                if(rs2.next()){
                    dogID = rs2.getInt(1);
                    newDog = new DogDTO(dog, dogID);
                }
            }
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CreateDog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Retour du chien créé ou de null en cas d'erreur
        return newDog;
    }

    @Override
    public List<DogDTO> createRandomDogs(int number) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
