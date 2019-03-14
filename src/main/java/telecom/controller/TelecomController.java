package telecom.controller;

import org.springframework.web.bind.annotation.PostMapping;
import telecom.exception.CustomerDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import telecom.model.PhoneNumber;
import telecom.service.TelecomService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static telecom.util.Utils.createPhoneNumber;

@RestController
@RequestMapping("/")
public class TelecomController {

    private static final Logger log = LoggerFactory.getLogger(TelecomController.class);

    private final TelecomService telecomService;

    @Autowired
    public TelecomController(TelecomService telecomService) {
        this.telecomService = telecomService;
    }

    /**
     * REST Endpoint returns all existing phone numbers in the database
     */
    @GetMapping(value = "/phoneNumbers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneNumber>> getAllPhoneNumbers() {
        return new ResponseEntity<>(telecomService.getAllPhoneNumbers(), HttpStatus.OK);
    }

    /**
     * REST Endpoint returns all existing phone numbers corresponding to the Customer
     * @param id Customer ID
     */
    @GetMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PhoneNumber>> getCustomerPhoneNumbers(
            @PathVariable(value = "id") String id) {

        try {
            return new ResponseEntity<>(
                    telecomService.getSingleCustomerPhoneNumbers(id), HttpStatus.OK);
        } catch (CustomerDoesNotExistException e) {
            log.error("Error: ", e);
        }

        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
    }

    /**
     * REST Endpoint activates the customer's provided phone number
     * @param id Customer ID
     * @param phoneNumber Customer Phone Number
     */
    @PutMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneNumber> activatePhoneNumber(
            @PathVariable(value = "id") String id,
            @RequestParam(value = "phoneNumber") String phoneNumber) {

        try {
            return new ResponseEntity<>(
                    telecomService.activatePhoneNumber(id, phoneNumber), HttpStatus.OK);
        } catch (CustomerDoesNotExistException e) {
            log.error("Error: ", e);
        }

        return new ResponseEntity<>(createPhoneNumber(phoneNumber), HttpStatus.BAD_REQUEST);
    }

    /**
     * REST Endpoint adds phone numbers to the customer's account
     * If customer does not have an account, the API creates a customer account
     * with the provided ID
     * @param id Customer ID
     * @param phoneNumber Phone number to add
     */
    @PostMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, List<PhoneNumber>>> addCustomerPhoneNumber(
            @PathVariable(value = "id") String id,
            @RequestParam(value = "phoneNumber") String phoneNumber) {

        return new ResponseEntity<>(
                telecomService.addPhoneNumberToCustomer(id, phoneNumber), HttpStatus.OK);
    }
}
