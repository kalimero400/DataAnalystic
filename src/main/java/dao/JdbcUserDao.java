package dao;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by arthur on 16.06.18.
 */
@Component
@Qualifier("jdbcUserDao")
public class JdbcUserDao implements UserDAO {

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setUser(User user) {


        String sql = "INSERT INTO USER " +
                "(ID, NAME, PLATFORM) VALUES (?, ?, ?)";
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setInt(3, user.getPlatform());
            ps.executeUpdate();
            ps.close();

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

    public User getUserById(int id) {
        String sql = "SELECT * FROM USER WHERE ID = ?";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            User user = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getInt("PLATFORM")
                );
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
}
