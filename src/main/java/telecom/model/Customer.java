package telecom.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {

    private String id;
    private String name;
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
        phoneNumbers.add(new PhoneNumber.Builder().withPhoneNumber("232").build());
        phoneNumbers.add(new PhoneNumber.Builder().withPhoneNumber("232232").build());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public static class Builder {
        private String id;
        private String name;

        public Customer build() {
            return new Customer(id, name);
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
