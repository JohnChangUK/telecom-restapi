package telecom.util;

import telecom.model.Customer;
import telecom.model.PhoneNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Utils {

    public static final String CUSTOMER_DOES_NOT_EXIST= "Customer does not exist, " +
            "please create an account with phone number first";

    public static void populateCustomerPhoneMap(Map<Customer, List<PhoneNumber>> customerPhoneMapping) {
        customerPhoneMapping.put(createCustomer("1"), new ArrayList<>(Arrays.asList(createPhoneNumber("0777"))));
        customerPhoneMapping.put(createCustomer("1"), new ArrayList<>(Arrays.asList(createPhoneNumber("0888"))));
        customerPhoneMapping.put(createCustomer("2"), new ArrayList<>(Arrays.asList(createPhoneNumber("0111"))));
        customerPhoneMapping.put(createCustomer("3"), new ArrayList<>(Arrays.asList(createPhoneNumber("0222"))));
        customerPhoneMapping.put(createCustomer("4"), new ArrayList<>(Arrays.asList(createPhoneNumber("0333"))));
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
