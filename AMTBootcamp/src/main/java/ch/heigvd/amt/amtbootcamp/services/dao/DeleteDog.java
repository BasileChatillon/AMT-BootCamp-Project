/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amtbootcamp.services.dao;

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
public class DeleteDog implements DeleteDogLocal{

    @Resource(lookup = "java:/jdbc/dogdb")
    private DataSource dataSource;
        
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
}