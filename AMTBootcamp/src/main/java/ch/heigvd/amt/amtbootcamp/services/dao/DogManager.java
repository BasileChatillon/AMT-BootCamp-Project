/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services.dao;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ejb.Stateless;

/**
 *
 * @author basilechatillon
 */
@Stateless
public class DogManager implements DogManagerLocal{

    @Resource(lookup = "jdbc/dog")
    private DataSource dataSource;
    
    @Override
    public List<Dog> findAllDogs() {
        List<Dog> dogs = new ArrayList<>();
        
        Connection connection;
        try {
            connection = dataSource.getConnection();
            System.out.println("Schema : " + connection.getSchema());
            System.out.println("Catalog : " + connection.getCatalog());
            
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM dog");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String name= rs.getString("name");
                int age = rs.getInt("age");
                double weight = rs.getDouble("weight");
                String quote = rs.getString("quote");
                dogs.add(new Dog(name, age, weight, quote));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DogManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dogs;
    }
}
