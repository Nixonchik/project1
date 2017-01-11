package com.korobkin.dao;

import com.korobkin.dao.car.filters.CarFilter;
import com.korobkin.data.source.DBFactory;
import com.korobkin.model.Car;
import com.korobkin.properties.SQLQueries;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Java Developer on 15.12.2015.
 */
public class CarDAO {
    private static final Logger logger = Logger.getLogger(CarDAO.class);

    private static final String addCarQuery = SQLQueries.carQuery("ADD_CAR_QUERY");
    private static final String getCarByIdQuery = SQLQueries.carQuery("GET_CAR_BY_ID");

    protected CarDAO() {}

    /**
     * Adding car to DB.
     *
     * @param car car to add to DB
     * @return id of car in DB or -1 if has not been added.
     */
    public int addCar(Car car) {
        try (Connection c = DBFactory.getDBConnection();
             PreparedStatement ps = c.prepareStatement(addCarQuery, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, car.getModel());
            ps.setInt(2, car.getYear());
            ps.setString(3, car.getColor());
            ps.setFloat(4, car.getEngine());
            ps.setFloat(5, car.getExpenditure());
            ps.setBoolean(6, car.isAutomat());
            ps.setInt(7, car.getPrice());
            ps.setString(8, car.getDescription());

            int i = ps.executeUpdate();
            if (i == 1) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                generatedKeys.next();
                return generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            logger.info(e);
        }
        return -1;
    }

    /**
     * Searching cars by particular filters.
     *
     * @param filterList list of filters.
     * @return List of Cars from DB and empty List in case of Exception
     */
    public List<Car> findCars(List<CarFilter> filterList) {
        List<Car> res = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM car");
        try (Connection c = DBFactory.getDBConnection();
            Statement ps = c.createStatement()) {
            switch (filterList.size()) {
                case 0:
                    break;
                case 1:
                    query.append("where ")
                    .append(filterList.get(0).stringPattern());
                    break;
                default:
                    query.append(("where"))
                    .append(filterList.remove(0).stringPattern());
                    for (CarFilter filter : filterList) {
                        query.append(" and ").append(filter.stringPattern());
                    }
            }
            ResultSet rs = ps.executeQuery(query.toString());
            while (rs.next()) {
                res.add(getCarFromResultSet(rs));
            }
            return res;
        } catch (SQLException e) {
            logger.info(e);
        }
        return new ArrayList<Car>();
    }

    /**
     * Returns Instance of Car from DB.
     *
     * @param id carId from DB.
     * @return instance of Car or null in case of Exception or if not exist.
     */
    public Car getCar(int id) {
        Car car = null;
        try (Connection c = DBFactory.getDBConnection();
            PreparedStatement ps = c.prepareStatement(getCarByIdQuery)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return null;
            return getCarFromResultSet(rs);
        } catch (SQLException e) {
            logger.info(e);
        }
        return null;
    }

    private Car getCarFromResultSet(ResultSet rs) throws SQLException {
        return Car.newBuilder()
                .setId(rs.getInt("car.id"))
                .setModel(rs.getString("car.model"))
                .setYear(rs.getInt("car.year"))
                .setColor(rs.getString("car.color"))
                .setEngine(rs.getFloat("car.engine"))
                .setExpenditure(rs.getFloat("car.expenditure"))
                .setTransmission((rs.getBoolean("car.automat")) ? Car.Transmission.AUTOMAT: Car.Transmission.MANUAL)
                .setPrice(rs.getInt("car.price"))
                .setDescription(rs.getString("car.description"))
                .build();
    }
}

