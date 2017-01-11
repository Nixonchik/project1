package com.korobkin.dao.cache;

import com.korobkin.dao.CarDAO;
import com.korobkin.model.Car;

/**
 * This class cached Cars from DB in lazy mode (getting Car with corresponding request).
 */
public class CarDAOWithCache extends CarDAO {

    /**
     * Because of small quantity of Cars we use cache for their instances.
     *
     * @param id CarId from DB.
     * @return Instance of this Car.
     */
    @Override
    public Car getCar(int id) {
        Car res = CarCache.getCar(id);
        if (res == null) {
            res = super.getCar(id);
            if (res != null) CarCache.addCar(res);
        }
        return res;
    }

}
