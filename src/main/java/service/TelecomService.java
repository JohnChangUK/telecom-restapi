package service;

import exception.CustomerDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import model.Customer;
import model.PhoneNumber;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TelecomService {

    private final Map<Customer, List<PhoneNumber>> customerPhoneMapping;

    @Autowired
    public TelecomService(Map<Customer, List<PhoneNumber>> customerPhoneMapping) {
        this.customerPhoneMapping = customerPhoneMapping;
    }

    public List<PhoneNumber> getAllPhoneNumbers() {
        List<PhoneNumber> allPhoneNumbers = customerPhoneMapping.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return allPhoneNumbers;
    }

    public List<PhoneNumber> getSingleCustomerPhoneNumbers(String customerId) throws CustomerDoesNotExistException {
        for (Customer customer : customerPhoneMapping.keySet()) {
            if (customer.getId().equals(customerId)) {
                return customerPhoneMapping.get(customer);
            }
        }

        throw new CustomerDoesNotExistException("Customer does not exist");
    }

    public List<PhoneNumber> addPhoneNumberToCustomer(String customerId, String phoneNumber) {
        PhoneNumber newPhoneNumber = createPhoneNumber(phoneNumber);

        Optional<Customer> customerAccount = customerPhoneMapping.keySet().stream()
                .filter(x -> x.getId().equals(customerId))
                .findFirst();

        if (!customerAccount.isPresent()) {
            Customer newCustomer = createCustomer(customerId);
            customerPhoneMapping.put(newCustomer, Collections.singletonList(newPhoneNumber));

            return customerPhoneMapping.get(newCustomer);
        }

        customerAccount.ifPresent(customer -> customerPhoneMapping.get(customer).add(newPhoneNumber));
        return customerPhoneMapping.get(customerAccount.get());
    }

    private Customer createCustomer(String customerId) {
        return new Customer.Builder()
                        .withId(customerId)
                        .build();
    }

    private PhoneNumber createPhoneNumber(String phoneNumber) {
        return new PhoneNumber.Builder()
                    .withPhoneNumber(phoneNumber)
                    .build();
    }
}