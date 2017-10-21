/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services.dao;

import ch.heigvd.amt.amtbootcamp.model.Dog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class UpdateDog implements UpdateDogLocal {

    @Resource(lookup = "java:/jdbc/dogdb")
    private DataSource dataSource;

    @Override
    public boolean updateDog(int id, Dog dog) {
        int i = 0;
        Connection connection;
        
        try {
            connection = dataSource.getConnection();
            System.out.println("Schema : " + connection.getSchema());
            System.out.println("Catalog : " + connection.getCatalog());

            String query = "UPDATE dog SET dog_name = ?, dog_age = ?, dog_weight = ?, dog_quote = ? WHERE dog_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            
            pstmt.setString(1, dog.getName());
            pstmt.setInt(2, dog.getAge());
            pstmt.setDouble(3, dog.getWeight());
            pstmt.setString(4, dog.getQuote());
            pstmt.setInt(5, id);

            i = pstmt.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UpdateDog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return (i > 0);
    }

}
