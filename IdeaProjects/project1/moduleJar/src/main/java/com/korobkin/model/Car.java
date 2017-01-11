package com.korobkin.model;

/**
 * POJO of Car entity in DAO pattern.
 */
public class Car {

    int id;
    String model;
    int year;
    String color;
    float engine; // l
    float expenditure; // l/100km
    Transmission transmission;
    int price;
    private String description;

    public Car() {
    }

    public Car(int id) {
        this.id = id;
    }

    public boolean isAutomat() {
        return transmission == Transmission.AUTOMAT;
    }

    public String getModel() {
        return model;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public float getEngine() {
        return engine;
    }

    public float getExpenditure() {
        return expenditure;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Equals compares object only by Id field.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (id != car.id) return false;

        return true;
    }

    /**
     * HashCode depends only of ID field.
     * @return
     */
    @Override
    public int hashCode() {
        return id;
    }

    public enum Transmission {
        MANUAL, AUTOMAT;
    }

    public static Builder newBuilder() {
        return new Car().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(int id) {
            Car.this.id = id;
            return this;
        }

        public Builder setModel(String model) {
            Car.this.model = model;
            return this;
        }

        public Builder setYear(int year) {
            Car.this.year = year;
            return this;
        }

        public Builder setColor(String color) {
            Car.this.color = color;
            return this;
        }

        public Builder setEngine(float engine) {
            Car.this.engine = engine;
            return this;
        }

        public Builder setExpenditure(float expenditure) {
            Car.this.expenditure = expenditure;
            return this;
        }

        public Builder setTransmission(Transmission transmission) {
            Car.this.transmission = transmission;
            return this;
        }

        public Builder setPrice(int price) {
            Car.this.price = price;
            return this;
        }

        public Builder setDescription(String description) {
            Car.this.description = description;
            return this;
        }

        public Car build() {
            return Car.this;
        }
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model=" + model +
                ", year=" + year +
                ", color='" + color + "'" +
                ", engine=" + engine +
                ", expenditure=" + expenditure +
                ", transmission=" + transmission +
                ", price=" + price +
                '}';
    }
}
