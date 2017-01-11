package com.korobkin.model;

import java.util.Calendar;

/**
 * POJO of Defect entity in DAO pattern with Builder.
 */
public class Defect {

    int id;
    int carId;
    int clientId;
    String description;
    float priceForClient;
    Calendar date;
    boolean paid; // Has client paid for defect?
    boolean repaired = false;

    private Defect() {
    }

    public static Builder getBuilder() {
        return new Defect().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setCarId(int carId) {
            Defect.this.carId = carId;
            return this;
        }

        public Builder setClientId(int clientId) {
            Defect.this.clientId = clientId;
            return this;
        }

        public Builder setDescription(String description) {
            Defect.this.description = description;
            return this;
        }

        public Builder setPriceForClient(float priceForClient) {
            Defect.this.priceForClient = priceForClient;
            return this;
        }

        public Builder setDate(Calendar date) {
            Defect.this.date = date;
            return this;
        }

        public Builder setPaid(boolean isPaid) {
            Defect.this.paid = isPaid;
            return this;
        }

        public Builder setRepaired(boolean isRepaired) {
            Defect.this.repaired = isRepaired;
            return this;
        }

        public Builder setId(int id) {
            Defect.this.id = id;
            return this;
        }

        public Defect build() {
            return Defect.this;
        }

    }

    public boolean isPaid() {
        return paid;
    }

    public int getId() {
        return id;
    }

    public boolean isRepaired() {
        return repaired;
    }

    public int getCarId() {
        return carId;
    }

    public int getClientId() {
        return clientId;
    }

    public String getDescription() {
        return description;
    }

    public float getPriceForClient() {
        return priceForClient;
    }

    public Calendar getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Defect{" +
                "carId=" + carId +
                ", clientId=" + clientId +
                ", description='" + description + '\'' +
                ", priceForClient=" + priceForClient +
                ", date=" + date +
                '}';
    }
}
