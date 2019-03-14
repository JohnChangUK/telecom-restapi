package telecom.service;

import telecom.exception.CustomerDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telecom.model.Customer;
import telecom.model.PhoneNumber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static telecom.util.Utils.*;

@Service
public class TelecomService {

    private Map<Customer, List<PhoneNumber>> customerPhoneMapping;

    @Autowired
    public TelecomService(Map<Customer, List<PhoneNumber>> customerPhoneMapping) {
        this.customerPhoneMapping = customerPhoneMapping;
        populateCustomerPhoneMap(customerPhoneMapping);
    }

    public List<PhoneNumber> getAllPhoneNumbers() {
        return customerPhoneMapping.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<PhoneNumber> getSingleCustomerPhoneNumbers(String id) throws CustomerDoesNotExistException {
        for (Customer customer : customerPhoneMapping.keySet()) {
            if (customer.getId().equals(id)) {
                return customerPhoneMapping.get(customer);
            }
        }

        throw new CustomerDoesNotExistException(CUSTOMER_DOES_NOT_EXIST);
    }

    public PhoneNumber activatePhoneNumber(String id, String phoneNumber) throws CustomerDoesNotExistException {
        Optional<Customer> customer = getCustomer(id);
        Optional<PhoneNumber> customerPhoneNumber = Optional.empty();

        if (customer.isPresent()) {
            customerPhoneNumber = customerPhoneMapping.get(customer.get())
                    .stream()
                    .filter(x -> x.getPhoneNumber().equals(phoneNumber))
                    .findFirst();

            customerPhoneNumber.ifPresent(number -> number.setActivated(true));
        }

        if (customerPhoneNumber.isPresent()) {
            return customerPhoneNumber.get();
        } else {
            throw new CustomerDoesNotExistException(CUSTOMER_DOES_NOT_EXIST);
        }
    }

    public List<PhoneNumber> addPhoneNumberToCustomer(String id, String phoneNumber) {
        PhoneNumber newPhoneNumber = createPhoneNumber(phoneNumber);
        Optional<Customer> customer = getCustomer(id);

        if (!customer.isPresent()) {
            Customer newCustomer = createCustomer(id);

            List<PhoneNumber> phoneNumberList = new ArrayList<>();
            phoneNumberList.add(newPhoneNumber);
            customerPhoneMapping.put(newCustomer, phoneNumberList);

            return customerPhoneMapping.get(newCustomer);
        }

        customer.ifPresent(x -> customerPhoneMapping.get(customer.get()).add(newPhoneNumber));
        return customerPhoneMapping.get(customer.get());
    }

    private Optional<Customer> getCustomer(String id) {
        return customerPhoneMapping.keySet()
                .stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

}
