package com.korobkin.model;

/**
 * POJO of Client entity in DAO pattern with Builder.
 */
public class Client {

    int id;
    String email;
    String firstName;
    String lastName;
    String phone;

    public Client() {
    }

    /**
     * Order has-a Client, so we usually use Client object with correct only id field to compare clients from order equals.
     *
     * @param id Id field of Client object.
     */
    public Client(int id) {
        this.id = id;
    }

    public static Builder newBuilder() {
        return new Client().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(int id) {
            Client.this.id = id;
            return this;
        }

        public Builder setEmail(String email) {
            Client.this.email = email;
            return this;
        }

        public Builder setFirstName(String firstname) {
            Client.this.firstName = firstname;
            return this;
        }

        public Builder setLastName(String lastName) {
            Client.this.lastName = lastName;
            return this;
        }

        public Builder setPhone(String phone) {
            Client.this.phone = phone;
            return this;
        }

        public Client build() {
            return Client.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

//        if (id == client.id && id != 0) return true;

        if (!email.equals(client.email)) return false;
        if (firstName != null ? !firstName.equals(client.firstName) : client.firstName != null) return false;
        if (lastName != null ? !lastName.equals(client.lastName) : client.lastName != null) return false;
        if (!phone.equals(client.phone)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + phone.hashCode();
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
