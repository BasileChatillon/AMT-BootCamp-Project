package ch.heigvd.amt.amtbootcamp.services.dao;

import ch.heigvd.amt.amtbootcamp.rest.dto.DogDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class GetDog implements GetDogLocal {

    @Resource(lookup = "java:/jdbc/dogdb")
    private DataSource dataSource;

    @Override
    public DogDTO findDog(int id) {
        DogDTO dogrequested = null;

        Connection connection;
        try {
            connection = dataSource.getConnection();
            System.out.println("Schema : " + connection.getSchema());
            System.out.println("Catalog : " + connection.getCatalog());

            String query = "SELECT * FROM dog WHERE dog_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("dog_name");
                int age = rs.getInt("dog_age");
                double weight = rs.getDouble("dog_weight");
                String quote = rs.getString("dog_quote");
                dogrequested = new DogDTO(id, name, age, weight, quote);
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetDog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dogrequested;
    }

    @Override
    public List<DogDTO> findDogs(List<Integer> IDs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<DogDTO> findAllDogs() {
        List<DogDTO> dogs = new ArrayList<>();

        Connection connection;
        try {
            connection = dataSource.getConnection();
            System.out.println("Schema : " + connection.getSchema());
            System.out.println("Catalog : " + connection.getCatalog());

            String query = "SELECT * FROM dog";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("dog_id");
                String name = rs.getString("dog_name");
                int age = rs.getInt("dog_age");
                double weight = rs.getDouble("dog_weight");
                String quote = rs.getString("dog_quote");
                dogs.add(new DogDTO(id, name, age, weight, quote));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetDog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dogs;
    }

    @Override
    public int findNumberOfDog() {
        int nbDog = 0;
        Connection connection;
        try {
            connection = dataSource.getConnection();
            System.out.println("Schema : " + connection.getSchema());
            System.out.println("Catalog : " + connection.getCatalog());

            String query = "SELECT COUNT(*) FROM dog";
            PreparedStatement pstmt = connection.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nbDog = rs.getInt(1);
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetDog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nbDog;
    }

    @Override
    public List<DogDTO> findDogsPages(int page, int nbDogInPage) {
        List<DogDTO> dogs = new ArrayList<>();

        Connection connection;
        try {
            connection = dataSource.getConnection();
            System.out.println("Schema : " + connection.getSchema());
            System.out.println("Catalog : " + connection.getCatalog());

            String query = "SELECT * FROM dog LIMIT ? OFFSET ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, nbDogInPage);
            pstmt.setInt(2, nbDogInPage * page);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("dog_id");
                String name = rs.getString("dog_name");
                int age = rs.getInt("dog_age");
                double weight = rs.getDouble("dog_weight");
                String quote = rs.getString("dog_quote");
                dogs.add(new DogDTO(id, name, age, weight, quote));
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(GetDog.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dogs;
    }

}
