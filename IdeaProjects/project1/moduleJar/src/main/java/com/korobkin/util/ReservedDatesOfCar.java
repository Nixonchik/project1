package com.korobkin.util;

import com.korobkin.model.Order;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * All just created orders reserves dates.
 * All changes to DB or this instance with orders do in synchronize block by this class.
 * After confirm or cancel some just created order removes Order from this class and save info in DB.
 * This class uses when takes info about reserved dates of particular car.
 */
public class ReservedDatesOfCar {
    private static final Logger logger = Logger.getLogger(ReservedDatesOfCar.class);
    private Map<Integer, Set<Order>> reserves = new HashMap<>();
    static private final ReservedDatesOfCar instance = new ReservedDatesOfCar();

    private ReservedDatesOfCar() {
    }

    static class OrderComparator implements Comparator<Order> {
        @Override
        public int compare(Order o1, Order o2) {
            if (o1.getStart().before(o2.getStart())) return -1;
            if (o1.getStart().equals(o2.getStart())) return 0;
            return 1;
        }
    }

    public static void addOrderToReserve(int carId, Order order) {
        Set<Order> dates = getDates(carId);

        synchronized (ReservedDatesOfCar.class) {
            dates.add(order);
        }
    }

    public static Set<Order> getDatesOfCar(int carId) {
        return Collections.unmodifiableSet(getDates(carId));
    }

    private static Set<Order> getDates(int carId) {
        if (!instance.reserves.containsKey(carId)) {
            instance.reserves.put(carId, new TreeSet<Order>(new OrderComparator()));
        }
        return instance.reserves.get(carId);
    }

    public static boolean isAvailableDate(Order order) {
        Calendar from = order.getStart();
        Calendar to = order.getEnd();
        int carId = order.getCar().getId();
        if (!instance.reserves.containsKey(carId)) return true;
        Set<Order> reservedDates = instance.reserves.get(carId);
        for(Order o: reservedDates) {
            if (o.getStart().before(to) && o.getEnd().after(from) && o.getId()!=order.getId()) return false;
        }
        return true;
    }

    public static void removeDatesOfCar(int carId, Order order) {
        Set<Order> orders = getDates(carId);

        synchronized (ReservedDatesOfCar.class) {
            orders.remove(order);
        }
    }
}