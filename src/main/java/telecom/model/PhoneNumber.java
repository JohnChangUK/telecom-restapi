package telecom.model;

import java.util.Objects;

public class PhoneNumber {

    private String phoneNumber;
    private boolean activated;

    public PhoneNumber(String phoneNumber, boolean activated) {
        this.phoneNumber = phoneNumber;
        this.activated = activated;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public static class Builder {
        private String phoneNumber;
        private boolean activated;

        public PhoneNumber build() {
            return new PhoneNumber(phoneNumber, activated);
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber= phoneNumber;
            return this;
        }

        public Builder isActivated(boolean activated) {
            this.activated = activated;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return activated == that.activated &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber, activated);
    }
}
