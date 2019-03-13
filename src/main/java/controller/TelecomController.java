package controller;

import exception.CustomerDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import model.PhoneNumber;
import service.TelecomService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/")
public class TelecomController {

    private static final Logger log = LoggerFactory.getLogger(TelecomController.class);

    private final TelecomService telecomService;

    @Autowired
    public TelecomController(TelecomService telecomService) {
        this.telecomService = telecomService;
    }

    @GetMapping("/phoneNumbers")
    public ResponseEntity<List<PhoneNumber>> getAllPhoneNumbers() {
        return new ResponseEntity<>(telecomService.getAllPhoneNumbers(), HttpStatus.OK);
    }

    @GetMapping("/phoneNumbers/{id}")
    public ResponseEntity<List<PhoneNumber>> getCustomerPhoneNumbers(@PathVariable(value = "id") String id) {
        try {
            return new ResponseEntity<>(telecomService.getSingleCustomerPhoneNumbers(id), HttpStatus.OK);
        } catch (CustomerDoesNotExistException e) {
            log.error("Error: ", e);
        }

        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
    }
}
