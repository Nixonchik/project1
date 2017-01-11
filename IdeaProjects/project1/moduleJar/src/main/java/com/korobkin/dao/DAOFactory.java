package com.korobkin.dao;

import com.korobkin.dao.cache.CarDAOWithCache;

/**
 * Created by Java Developer on 26.11.2015.
 */
public class DAOFactory {

    /**
     *
     * @return new Object of AdminDAO
     */
    public static AdminDAO adminDAO() {
        return new AdminDAO();
    }

    /**
     *
     * @return new Object of ClientDAO
     */
    public static ClientDAO clientDAO() {
        return new ClientDAO();
    }

    /**
     *
     * @return new Object of CarDAOWithCache
     */
    public static CarDAO carDAO() {
        return new CarDAOWithCache();
    }

    /**
     *
     * @return new Object of OrderDAO
     */
    public static OrderDAO orderDAO() {
        return new OrderDAO();
    }

    /**
     *
     * @return new Object of DefectDAO
     */
    public static DefectDAO defectDAO() {
        return new DefectDAO();
    }

}

