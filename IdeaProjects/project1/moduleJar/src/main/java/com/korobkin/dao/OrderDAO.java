package com.korobkin.dao;

import com.korobkin.dao.order.filters.NoAdminFilter;
import com.korobkin.dao.order.filters.OrderFilter;
import com.korobkin.data.source.DBFactory;
import com.korobkin.model.Car;
import com.korobkin.model.Client;
import com.korobkin.model.Order;
import com.korobkin.properties.SQLQueries;
import com.korobkin.util.CalendarUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Java Developer on 15.12.2015.
 */
public class OrderDAO {
    private static final Logger logger = Logger.getLogger(OrderDAO.class);
    private static final String getOrdersFilterQuery = SQLQueries.orderQuery("GET_ORDERS_FILTER_QUERY");
    private static final String getOrderByIdQuery = SQLQueries.orderQuery("GET_ORDER_BY_ID_QUERY");
    private static final String addOrderQuery = SQLQueries.orderQuery("ADD_ORDER_QUERY");
    private static final String updateOrderQuery = SQLQueries.orderQuery("UPDATE_ORDER_QUERY");
    private static final String isAvailableDateForCarQuery = SQLQueries.orderQuery("IS_AVAILABLE_DATE_FOR_CAR_QUERY");
    private static final String isAvailableDateToEditOrder = SQLQueries.orderQuery("IS_AVAILABLE_DATE_TO_EDIT_ORDER");
    private static final String changeOrderStatusQuery = SQLQueries.orderQuery("CHANGE_ORDER_STATUS_QUERY");
    private static final String getBlockedDatesQuery = SQLQueries.orderQuery("GET_BLOCKED_DATES_QUERY");
    private static final String changeAdminOfOrderQuery = SQLQueries.orderQuery("CHANGE_ADMIN_OF_ORDER_QUERY");
    private static final String deleteOrderQuery = SQLQueries.orderQuery("DELETE_ORDER_QUERY");

    protected OrderDAO() {}

    /**
     * @param orderFilters
     * @return object of Order where Car has only ID, model and price, Client has only id.
     */
    public List<Order> getOrdersWithoutClientAndCar(List<OrderFilter> orderFilters) {
        List<Order> res = new ArrayList<>();
        try (Connection c = DBFactory.getDBConnection();
             Statement statement = c.createStatement()) {

            StringBuilder sb = new StringBuilder(getOrdersFilterQuery);
            switch (orderFilters.size()) {
                case 0:
                    sb.append(new NoAdminFilter().stringPattern());
                    break;
                case 1:
                    sb.append(orderFilters.get(0).stringPattern());
                    break;
                default:
                    sb.append(orderFilters.remove(0).stringPattern());
                    for (OrderFilter of : orderFilters) {
                        sb.append(" and ")
                                .append(of.stringPattern());
                    }
            }

            ResultSet rs = statement.executeQuery(sb.toString());
            while (rs.next()) {
                Car.Builder carBuilder = new Car().newBuilder();
                Car car = carBuilder.setId(rs.getInt(9))
                        .setModel(rs.getString(10))
                        .build();

                Order order = Order.newBuilder().setId(rs.getInt(1))
                        .setAdmin(rs.getString(2))
                        .setClient(new Client(rs.getInt(3)))
                        .setStart(CalendarUtil.getCalendar(rs.getString(4)))
                        .setEnd(CalendarUtil.getCalendar(rs.getString(5)))
                        .setDetails(rs.getString(6))
                        .setChildChair(rs.getBoolean(7))
                        .setGps(rs.getBoolean(8))
                        .setCar(car)
                        .setPrice(rs.getInt(11))
                        .setStatus(rs.getInt(12))
                        .build();

                res.add(order);
            }
            return res;

        } catch (SQLException e) {
            logger.info(e);
            return null;
        }
    }

    public Order getOrder(int idOrder) {
        Order res = null;
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(getOrderByIdQuery)) {
            ps.setInt(1, idOrder);

            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return res;

            Client client = Client.newBuilder()
                    .setId(rs.getInt(7))
                    .setEmail(rs.getString(8))
                    .setFirstName(rs.getString(9))
                    .setLastName(rs.getString(10))
                    .setPhone(rs.getString(11))
                    .build();

            Car car = Car.newBuilder()
                    .setId(rs.getInt(12))
                    .setModel(rs.getString(13))
                    .build();

            res = Order.newBuilder()
                    .setId(idOrder)
                    .setAdmin(rs.getString(1))
                    .setStart(CalendarUtil.getCalendar(rs.getString(2)))
                    .setEnd(CalendarUtil.getCalendar(rs.getString(3)))
                    .setDetails(rs.getString(4))
                    .setChildChair(rs.getBoolean(5))
                    .setGps(rs.getBoolean(6))
                    .setStatus(rs.getInt(15))
                    .setClient(client)
                    .setCar(car)
                    .setPrice(rs.getInt(14))
                    .build();

            return res;
        } catch (SQLException e) {
            logger.info(e);
        }
        return null;
    }

    /**
     * Change column admin of current order row in DB.
     */
    public boolean changeAdminOfOrder(int orderId, String adminLogin) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(changeAdminOfOrderQuery)) {

            ps.setString(1, adminLogin);
            ps.setInt(2, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.info(e);
            return false;
        }
    }

    /**
     *
     * @param status new status of order.
     * @return true if update
     *          or false otherwise
     */
    public boolean changeOrderStatus(int orderId, Order.Status status) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(changeOrderStatusQuery)) {

            int newStatus = status.getStatus();
            ps.setInt(1, newStatus);
            ps.setInt(2, orderId);
            ps.setInt(3, newStatus - 1);
            int i = ps.executeUpdate();
            return i==1;

        } catch (SQLException e) {
            logger.info(e);
            return false;
        }
    }

    /**
     * To change dates of existing order we must know all reserved dates of this car instead of current order.
     *
     * @param start New date of rent starting
     * @param end New date of rent finishing
     * @param carId id of car
     * @param orderId id of current order (old dates of current order will not uses)
     * @return true if date period is not reserved or false otherwise.
     */
    public boolean isAvailableDateToEditOrder(Calendar start, Calendar end, int carId, int orderId) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(isAvailableDateToEditOrder)) {

            ps.setInt(1, carId);
            ps.setDate(2, new Date(end.getTimeInMillis()));
            ps.setDate(3, new Date(start.getTimeInMillis()));
            ps.setInt(4, orderId);

            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) return true;
        } catch (SQLException e) {
            logger.info(e);
        }
        return false;
    }

    /**
     * Get all dates in this period. If count > 0 return false else true.
     *
     * @param start Start date of new order
     * @param end Finish date of new order
     * @param carId ID of car from DB
     * @return true if car is not in reserve for this dates or false otherwise
     */
    public boolean isAvailableDateForCar(Calendar start, Calendar end, int carId) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(isAvailableDateForCarQuery)) {

            ps.setInt(1, carId);
            ps.setDate(2, new Date(end.getTimeInMillis()));
            ps.setDate(3, new Date(start.getTimeInMillis()));

            ResultSet resultSet = ps.executeQuery();
            if (!resultSet.next()) return true;
        } catch (SQLException e) {
            logger.info(e);
        }
        return false;
    }

    public boolean deleteOrder(int orderId) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(deleteOrderQuery)) {

            ps.setInt(1, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.info(e);
            return false;
        }
    }

    /**
     * Adding Order to DB.
     * If there are problems with DB return -1.
     *
     * @param order
     * @return orderId if created or -1 otherwise
     */
    public int addOrder(Order order) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(addOrderQuery, Statement.RETURN_GENERATED_KEYS)) {
            ps.setNull(1, Types.VARCHAR);
            ps.setInt(2, order.getClient().getId());
            ps.setInt(3, order.getCar().getId());
            ps.setString(4, CalendarUtil.getDateString(order.getStart()));
            ps.setString(5, CalendarUtil.getDateString(order.getEnd()));
            ps.setString(6, order.getDetails());
            ps.setBoolean(7, order.isChildChair());
            ps.setBoolean(8, order.isGps());
            ps.setInt(9, order.getPrice());

            int i = ps.executeUpdate();
            if (i == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.info(e);
        }
        return -1;
    }

    /**
     * Returns dates from ... to ... when this car has been reserved.
     *
     * @param carId Id od car,
     * @return set of dates or null if there was problems with DB.
     *      Orders have only carId, start and end dates.
     */
    public Set<Order> getBlockedDates(int carId) {
        Set<Order> res = new HashSet<>();
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(getBlockedDatesQuery)) {

            Calendar to = Calendar.getInstance();
            to.add(Calendar.MONTH, 3);

            ps.setInt(1, carId);
            ps.setDate(2, new Date(System.currentTimeMillis()));
            ps.setDate(3, new Date(to.getTimeInMillis()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setStart(CalendarUtil.getCalendar(rs.getString(1)));
                order.setEnd(CalendarUtil.getCalendar(rs.getString(2)));
                res.add(order);
            }
            return res;
        } catch (SQLException e) {
            logger.info(e);
            return null;
        }
    }

    /**
     * Method updates information about order in DB.
     * The order with current ID will be replaced after this method runs.
     * @return true if successfully changed row or false otherwise.
     */
    public boolean updateOrder(Order changedOrder) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(updateOrderQuery)) {

            ps.setString(1, changedOrder.getAdmin());
            ps.setInt(2, changedOrder.getCar().getId());
            ps.setString(3, CalendarUtil.getDateString(changedOrder.getStart()));
            ps.setString(4, CalendarUtil.getDateString(changedOrder.getEnd()));
            ps.setString(5, changedOrder.getDetails());
            ps.setBoolean(6, changedOrder.isChildChair());
            ps.setBoolean(7, changedOrder.isGps());
            ps.setInt(8, changedOrder.getId());

            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.info(e);
            return false;
        }
    }
}
