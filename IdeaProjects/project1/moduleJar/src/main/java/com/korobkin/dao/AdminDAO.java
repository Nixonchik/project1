package com.korobkin.dao;

import com.korobkin.data.source.DBFactory;
import com.korobkin.model.Admin;
import com.korobkin.properties.SQLQueries;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Java Developer on 15.12.2015.
 */
public class AdminDAO {

    static String isAdminQuery = SQLQueries.adminQuery("IS_ADMIN_QUERY");
    static String addAdminQuery = SQLQueries.adminQuery("ADD_ADMIN_QUERY");

    protected AdminDAO() {}

    private static final Logger logger = Logger.getLogger(AdminDAO.class);

    /**
     * Checks is login and password are correct for any admin.
     * @param login login of admin
     * @param password password of admin
     * @return
     */
    public boolean isAdmin(String login, String password) {
        try (Connection connection = DBFactory.getDBConnection();
        PreparedStatement ps = connection.prepareStatement(isAdminQuery)) {
            ps.setString(1, login.toLowerCase());
            ResultSet resultSet = ps.executeQuery();

            // Admin_login is unique column that's why we use "if" instead of "while"
            if (resultSet.next()) {

                // Passwords in DB are kept with BCrypt encoder.
                return BCrypt.checkpw(password, resultSet.getString(1));
            }

        } catch (SQLException e) {
            logger.info(e);
        }
        return false;
    }

    /**
     * Trying to add new admin to DB if not exist.
     * @param admin Admin object
     * @return 1 if added, 0 if exists, -1 if SQLException
     */
    public int addAdmin(Admin admin) {
        try (Connection c = DBFactory.getDBConnection();
                PreparedStatement ps = c.prepareStatement(addAdminQuery)) {
            ps.setString(1, admin.getLogin().toLowerCase());
            String hashedPass = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
            ps.setString(2, hashedPass);
            ps.setString(3, admin.getFirstName());
            ps.setString(4, admin.getLastName());
            int i = ps.executeUpdate();
            return i;
        } catch (SQLException e) {
            logger.info(e);
            return -1;
        }
    }
}
