package com.korobkin.dao.order.filters;

/**
 * Created by Java Developer on 29.11.2015.
 */
public class CarFilter implements OrderFilter {
    int carId;

    public CarFilter(int carId) {
        this.carId = carId;
    }

    @Override
    public String stringPattern() {
        return "car_id = " + carId;
    }
}
