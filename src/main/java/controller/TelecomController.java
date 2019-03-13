package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import model.PhoneNumber;
import service.TelecomService;

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
    public List<PhoneNumber> getAllPhoneNumbers() {
        return telecomService.getAllPhoneNumbers();
    }

    @GetMapping("/phoneNumbers/{customerId}")
    public List<PhoneNumber> getAllPhoneNumbers(@PathVariable(value = "customerId") String customerId) {
        return telecomService.getSingleCustomerPhoneNumbers(customerId);
    }
}
