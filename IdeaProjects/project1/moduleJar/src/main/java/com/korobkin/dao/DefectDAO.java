package com.korobkin.dao;

import com.korobkin.data.source.DBFactory;
import com.korobkin.model.Defect;
import com.korobkin.properties.SQLQueries;
import com.korobkin.util.CalendarUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Java Developer on 15.12.2015.
 */
public class DefectDAO {
    private static final Logger logger = Logger.getLogger(DefectDAO.class);
    private static final String addDefectQuery = SQLQueries.defectQuery("ADD_DEFECT_QUERY");
    private static final String getDescriptionsOfNotRepairedDefectsOfCarQuery = SQLQueries.defectQuery("GET_DESCRIPTIONS_OF_NOT_REPAIRED_DEFECTS_OF_CAR_QUERY");
    private static final String getNotPaidDefectsOfCarQuery = SQLQueries.defectQuery("GET_NOT_PAID_DEFECTS_OF_CAR_QUERY");
    private static final String paidForOrdersDefectsQuery = SQLQueries.defectQuery("PAID_FOR_ORDERS_DEFECT_QUERY");
    private static final String getNotRepairedDefectsOfCarQuery = SQLQueries.defectQuery("GET_NOT_REPAIRED_DEFECTS_OF_CAR_QUERY");
    private static final String repairDefectQuery = SQLQueries.defectQuery("REPAIR_DEFECT_QUERY");

    protected DefectDAO() {}

    /**
     * Adding defect to DB
     * @param defect Defect to add to DB
     * @return true if added or false otherwise
     */
    public boolean addDefect(Defect defect) {
        try (Connection c = DBFactory.getDBConnection();
            PreparedStatement ps = c.prepareStatement(addDefectQuery)) {

            ps.setInt(1, defect.getClientId());
            ps.setString(2, defect.getDescription());
            ps.setFloat(3, defect.getPriceForClient());
            ps.setString(4, CalendarUtil.getDateString(defect.getDate()));
            ps.setInt(5, defect.getCarId());
            ps.setBoolean(6, defect.isPaid());
            int i = ps.executeUpdate();
            return (i==1);
        } catch (SQLException e) {
            logger.info(e);
        }
        return false;
    }

    /**
     * Name of method said all about his procedure
     * @param carId Id or car from DB
     * @return List of descriptions
     */
    public List<String> getDescriptionsOfNotRepairedDefectsOfCar(int carId) {
        List<String> result = new ArrayList<>();
        try (Connection c = DBFactory.getDBConnection();
            PreparedStatement ps = c.prepareStatement(getDescriptionsOfNotRepairedDefectsOfCarQuery)) {

            ps.setInt(1, carId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                result.add(rs.getString(1));
            }
        } catch (SQLException e) {
            logger.info(e);
        }
        return result;
    }

    public List<Defect> getNotPaidDefectsOfCar(int carId) {
        List<Defect> res = new ArrayList<>();
        try (Connection c = DBFactory.getDBConnection();
            PreparedStatement ps = c.prepareStatement(getNotPaidDefectsOfCarQuery)) {
            ps.setInt(1, carId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Defect defect = Defect.getBuilder()
                        .setCarId(carId)
                        .setClientId(rs.getInt("client_id"))
                        .setDescription(rs.getString("description"))
                        .setPriceForClient(rs.getFloat("client_price"))
                        .setDate(CalendarUtil.getCalendar(rs.getString("occurrence_date")))
                        .setPaid(false)
                        .build();
                res.add(defect);
            }
            return res;
        } catch (SQLException e) {
            logger.info(e);
            return res;
        }
    }

    /**
     * This method change flag is_paid for all Defects of particular order.
     * @param orderId Id of current order.
     * @return true if changed or false otherwise
     */
    public boolean paidForOrdersDefects(int orderId) {
        try (Connection c = DBFactory.getDBConnection();
            PreparedStatement ps = c.prepareStatement(paidForOrdersDefectsQuery)) {
            ps.setInt(1, orderId);
            int i = ps.executeUpdate();
            if (i > 0) return true;
        } catch (SQLException e) {
            logger.info(e);
        }
        return false;
    }

    public List<Defect> getNotRepairedDefectsOfCar(int carId) {
        List<Defect> res = new ArrayList<>();
        try (Connection c = DBFactory.getDBConnection();
            PreparedStatement ps = c.prepareStatement(getNotRepairedDefectsOfCarQuery)) {
            ps.setInt(1, carId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Defect defect = Defect.getBuilder()
                        .setId(rs.getInt("id"))
                        .setCarId(carId)
                        .setClientId(rs.getInt("client_id"))
                        .setDescription(rs.getString("description"))
                        .setPriceForClient(rs.getFloat("client_price"))
                        .setDate(CalendarUtil.getCalendar(rs.getString("occurrence_date")))
                        .setPaid(rs.getBoolean("is_paid"))
                        .build();
                res.add(defect);
            }
            return res;
        } catch (SQLException e) {
            logger.info(e);
            return res;
        }
    }

    /**
     * This method change column "repaired" to true of current defect.
     * To check request uses price of defect.
     *
     * @param defectId ID of defect from DB
     * @param price defect.price + ""
     */
    public void repairDefect(int defectId, String price) {
        try (Connection c = DBFactory.getDBConnection();
            PreparedStatement ps = c.prepareStatement(repairDefectQuery)) {
            ps.setInt(1, defectId);
            ps.setString(2, price);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.info(e);
        }
    }
}
