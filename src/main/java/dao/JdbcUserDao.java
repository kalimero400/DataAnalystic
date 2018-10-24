package dao;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthur on 16.06.18.
 */
@Component
@Qualifier("jdbcUserDao")
public class JdbcUserDao {

    @Autowired
    private DataSource dataSource;
    int countNewRow=0;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void commitUser(User user) {


        String sql = "INSERT INTO USER " +
                "(ID, PLATFORM, IMAGEID, INFO, DATE) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;


        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getId());
            ps.setInt(2, user.getPlatform());
            ps.setString(3, user.getImageId());
            ps.setString(4, user.getInfo());
            ps.setDate(5, user.getDate());

            ps.executeUpdate();
            ps.close();
            System.out.println(countNewRow++);


        } catch (SQLException e) {

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }

    }

    public User getUserById(String id) {
        String sql = "SELECT * FROM USER WHERE ID = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            User user = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = null;
            }
            rs.close();
            ps.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public List <User> getListOfImageUrls(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date sDate = null;
        try {
            sDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String sql = "SELECT * FROM USER WHERE DATE = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new Date(sDate.getTime()));
            List <User> users = new ArrayList<User>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();
                try {
                    user.setId(rs.getString("id"));
                    user.setImageId(rs.getString("imageId"));
                    users.add(user);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }


            rs.close();
            ps.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
}
