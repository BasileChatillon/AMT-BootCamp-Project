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
