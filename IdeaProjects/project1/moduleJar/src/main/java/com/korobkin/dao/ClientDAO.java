package com.korobkin.dao;

import com.korobkin.data.source.DBFactory;
import com.korobkin.model.Client;
import com.korobkin.properties.SQLQueries;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * Created by Java Developer on 15.12.2015.
 */
public class ClientDAO {
    private static final Logger logger = Logger.getLogger(ClientDAO.class);

    private static final String addClientQuery = SQLQueries.clientQuery("ADD_CLIENT_QUERY");
    private static final String updateClientQuery = SQLQueries.clientQuery("UPDATE_CLIENT_QUERY");

    protected ClientDAO() {}

    /**
     * Add Client to DB
     *
     * @param client to add to DB
     * @return Id of client or -1 if something wrong.
     */
    public int addClient(Client client) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(addClientQuery, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, client.getEmail());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getLastName());
            ps.setString(4, client.getPhone());
            int i = ps.executeUpdate();
            if (i != 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            logger.info(e);
        }
        return -1;
    }

    /**
     * Update client by Id. So method update row where clientId equals client.getId()
     * @param client new client Object
     * @return true if update or false in case of SQLException.
     */
    public boolean updateClient(Client client) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(updateClientQuery)) {

            ps.setString(1, client.getEmail());
            ps.setString(2, client.getPhone());
            ps.setString(3, client.getFirstName());
            ps.setString(4, client.getLastName());
            ps.setInt(5, client.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.info(e);
            return false;
        }
    }
}
