/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services.dao;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import ch.heigvd.amt.amtbootcamp.services.RandomDogGenerationLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.ejb.EJB;
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
    
    @EJB
    RandomDogGenerationLocal randomDogGeneration;
    
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
    public int createRandomDogs(int number) {
        final int batchSize = 1000;
        int dogCount = 0;
        List<Dog> dogsGenerated = new ArrayList<>();
        
        Connection connection;
        try {
            connection = dataSource.getConnection();

            String query = "INSERT INTO dog (dog_name, dog_age, dog_weight, dog_quote) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            
            System.out.println("Schema : " + connection.getSchema());
            System.out.println("Catalog : " + connection.getCatalog());
            
            int nbToIter = number / batchSize;
            System.out.println("ch.heigvd.amt.amtbootcamp.services.dao.CreateDog.createRandomDogs():: nbTo iter "  + nbToIter + ".");
            
            for(int i = 0 ; i < nbToIter ; ++i ){
                System.out.println("ch.heigvd.amt.amtbootcamp.services.dao.CreateDog.createRandomDogs():: first "  + batchSize + " added.");
                dogsGenerated = randomDogGeneration.generateDog(batchSize);
                
                for (Dog dog : dogsGenerated) {
                    pstmt.setString(1, dog.getName());
                    pstmt.setInt(2, dog.getAge());
                    pstmt.setDouble(3, dog.getWeight());
                    pstmt.setString(4, dog.getQuote());

                    pstmt.addBatch();
                }

                int[] result = pstmt.executeBatch();
                
                for(int res : result){
                    if(res > 0)
                        dogCount++;
                }
            }
            
            int restToAdd = number % batchSize;
            System.out.println("ch.heigvd.amt.amtbootcamp.services.dao.CreateDog.createRandomDogs():: last "  + restToAdd + " to added.");
            if(restToAdd > 0){
                dogsGenerated = randomDogGeneration.generateDog(restToAdd);
                
                for (Dog dog : dogsGenerated) {
                    pstmt.setString(1, dog.getName());
                    pstmt.setInt(2, dog.getAge());
                    pstmt.setDouble(3, dog.getWeight());
                    pstmt.setString(4, dog.getQuote());

                    pstmt.addBatch();
                }

                int[] result = pstmt.executeBatch();
                
                for(int res : result){
                    if(res > 0)
                        dogCount++;
                }
            }
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CreateDog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Retour du chien créé ou de null en cas d'erreur
        return dogCount;
    }
    
}
