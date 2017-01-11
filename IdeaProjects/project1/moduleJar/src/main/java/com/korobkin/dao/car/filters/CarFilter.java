package com.korobkin.dao.car.filters;

/**
 * Created by Java Developer on 22.11.2015.
 */
public interface CarFilter {

    /**
     * At last we want to get SQL query like:
     * "select ... from ... where ... and " + ourPattern + " and ...;"
     *
     * @return pattern like "(param = 50 or param = 60)" to add to SQL query.
     */
    String stringPattern();
}
