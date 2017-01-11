package com.korobkin.model;

import com.korobkin.command.admin.order.state.*;
import com.korobkin.util.CalendarUtil;

import java.util.Calendar;
import java.util.Locale;

/**
 * POJO of Order entity in DAO pattern with Builder.
 */
public class Order {

    int id;
    Client client;
    String admin;
    Car car;
    Calendar start;
    Calendar end;
    boolean gps, childChair;
    String details;
    Status status;
    int price;

    public static Builder newBuilder() {
        return new Order().new Builder();
    }

    // Not deep equals. Car and client are compared only by ID field.
    // Result doesn't depend of Price, details and status.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (childChair != order.childChair) return false;
        if (gps != order.gps) return false;
        if (id != order.id) return false;
//        if (price != order.price) return false;
        if (admin != null ? !admin.equals(order.admin) : order.admin != null) return false;
        if (car.getId() != (order.car.getId())) return false;
        if (client.getId() != (order.client.getId())) return false;
//        if (details != null ? !details.equals(order.details) : order.details != null) return false;
        if (!end.equals(order.end)) return false;
        if (!start.equals(order.start)) return false;
//        if (status != order.status) return false;

        return true;
    }

    /**
     * Hash code dependents of carId and start Date.
     */
    @Override
    public int hashCode() {
        int result = car != null ? car.hashCode() : 0;
        result = 31 * result + start.hashCode();
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public String getStartParseString() {
        return CalendarUtil.getDateString(start);
    }

    public String getEndParseString() {
        return CalendarUtil.getDateString(end);
    }

    public String getStartString() {
        StringBuilder result = new StringBuilder();
        result
                .append(start.get(Calendar.DATE))
                .append(" ")
                .append(start.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH))
                .append(" ")
                .append(start.get(Calendar.YEAR));
        return result.toString();
    }

    public String getEndString() {
        StringBuilder result = new StringBuilder();
        result
                .append(end.get(Calendar.DATE))
                .append(" ")
                .append(end.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH))
                .append(" ")
                .append(end.get(Calendar.YEAR));
        return result.toString();
    }

    public boolean isGps() {
        return gps;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    public int getStatusInt() {
        return status.getStatus();
    }

    public String getStatusString() {
        switch (status) {
            case NOT_CONFIRM:
                return "Not confirm";
            case CONFIRMED:
                return "Confirmed";
            case CAR_WAS_GOT:
                return "Car has been got";
            case CAR_WAS_RETURNED:
                return "Car has been returned";
        }
        return null;
    }

    public OrderState getState() {
        switch (status) {
            case NOT_CONFIRM:
                return new NotConfirmOrderState();
            case CONFIRMED:
                return new ConfirmedOrderState();
            case CAR_WAS_GOT:
                return new CarWasGotOrderState();
            case CAR_WAS_RETURNED:
                return new CarWasReturnedOrderState();
        }
        return null;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isChildChair() {
        return childChair;
    }

    public void setChildChair(boolean childChair) {
        this.childChair = childChair;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Status of Order stores in DB like int.
     */
    public enum Status {
        NOT_CONFIRM(0), CONFIRMED(1), CAR_WAS_GOT(2), CAR_WAS_RETURNED(3);
        int status;

        Status(int i) {
            status = i;
        }

        public int getStatus() {
            return status;
        }

    }

    // ПОКА НЕ НАДО
    public class Builder {

        private Builder() {
        }

        public Builder setId(int id) {
            Order.this.id = id;
            return this;
        }

        public Builder setClient(Client client) {
            Order.this.client = client;
            return this;
        }

        public Builder setAdmin(String admin) {
            Order.this.admin = admin;
            return this;
        }

        public Builder setCar(Car car) {
            Order.this.car = car;
            return this;
        }

        public Builder setStart(Calendar start) {
            Order.this.start = start;
            return this;
        }

        public Builder setEnd(Calendar end) {
            Order.this.end = end;
            return this;
        }

        public Builder setGps(boolean gps) {
            Order.this.gps = gps;
            return this;
        }

        public Builder setChildChair(boolean childChair) {
            Order.this.childChair = childChair;
            return this;
        }

        public Builder setDetails(String details) {
            Order.this.details = details;
            return this;
        }

        public Builder setPrice(int price) {
            Order.this.price = price;
            return this;
        }

        public Builder setStatus(int status) {
            Order.this.status = Status.values()[status];
            return this;
        }

        public Order build() {
            return Order.this;
        }
    }

//    @Override
//    public String toString() {
//        return "Order{" +
//                "id=" + id +
//                ", clientId=" + client.getId() +
//                ", admin='" + admin + '\'' +
//                ", car=" + car +
//                ", start=" + start +
//                ", end=" + end +
//                ", gps=" + gps +
//                ", childChair=" + childChair +
//                ", details='" + details + '\'' +
//                ", price=" + price +
//                '}';
//    }
}