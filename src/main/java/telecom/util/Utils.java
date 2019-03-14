package telecom.util;

import telecom.model.Customer;
import telecom.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {

    public static final String CUSTOMER_DOES_NOT_EXIST= "Customer does not exist, " +
            "please create an account with phone number first";

    private static List<PhoneNumber> phoneNumberList = new ArrayList<>();
    private static List<PhoneNumber> phoneNumberList2 = new ArrayList<>();
    private static List<PhoneNumber> phoneNumberList3 = new ArrayList<>();
    private static List<PhoneNumber> phoneNumberList4 = new ArrayList<>();

    public static void populateCustomerPhoneMap(Map<Customer, List<PhoneNumber>> customerPhoneMapping) {
        populateListWithValues();

        customerPhoneMapping.put(createCustomer("1"), phoneNumberList);
        customerPhoneMapping.put(createCustomer("1"), phoneNumberList);
        customerPhoneMapping.put(createCustomer("2"), phoneNumberList2);
        customerPhoneMapping.put(createCustomer("3"), phoneNumberList3);
        customerPhoneMapping.put(createCustomer("4"), phoneNumberList4);
    }

    private static void populateListWithValues() {
        phoneNumberList.add(createPhoneNumber("0111"));
        phoneNumberList.add(createPhoneNumber("0222"));
        phoneNumberList2.add(createPhoneNumber("0333"));
        phoneNumberList3.add(createPhoneNumber("0444"));
        phoneNumberList4.add(createPhoneNumber("0555"));
    }

    public static Customer createCustomer(String id) {
        return new Customer.Builder()
                .withId(id)
                .build();
    }

    public static PhoneNumber createPhoneNumber(String phoneNumber) {
        return new PhoneNumber.Builder()
                .withPhoneNumber(phoneNumber)
                .build();
    }
}
