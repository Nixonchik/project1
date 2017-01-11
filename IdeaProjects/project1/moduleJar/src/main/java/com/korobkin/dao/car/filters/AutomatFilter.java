package com.korobkin.dao.car.filters;

import com.korobkin.model.Car;

/**
 * Created by Java Developer on 24.11.2015.
 */
public class AutomatFilter implements CarFilter {
    boolean automat;

    public AutomatFilter(Car.Transmission transmission) {
        automat = (transmission == Car.Transmission.AUTOMAT);
    }

    @Override
    public String stringPattern() {
        StringBuilder s = new StringBuilder();
        s.append("car.automat = ")
                .append(automat);
        return s.toString();
    }
}
