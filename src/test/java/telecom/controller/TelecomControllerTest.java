package telecom.controller;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import telecom.exception.CustomerDoesNotExistException;
import telecom.model.PhoneNumber;
import telecom.service.TelecomService;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static telecom.util.Utils.CUSTOMER_DOES_NOT_EXIST;

@RunWith(SpringJUnit4ClassRunner.class)
public class TelecomControllerTest {

    @InjectMocks
    private TelecomController telecomController;

    @Mock
    private TelecomService mockTelecomService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private TelecomService telecomService;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        telecomService = new TelecomService();
        mockMvc = MockMvcBuilders.standaloneSetup(telecomController)
                .build();
    }

    @Test
    public void testGetSingleCustomerPhoneNumbers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockTelecomService).getSingleCustomerPhoneNumbers("1");

        List<PhoneNumber> phoneNumber = telecomService.getSingleCustomerPhoneNumbers("1");
        List<PhoneNumber> mockPhoneNumber = createMockListOfPhoneNumbers("0111");

        assertTrue(phoneNumber.contains(mockPhoneNumber.get(0)));
    }

    @Test
    public void testGettingAllPhoneNumbersInDataBase() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/phoneNumbers"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockTelecomService).getAllPhoneNumbers();
    }


    @Test
    public void getActivatingCustomerPhoneNumber() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/customer/1?phoneNumber=0111"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(mockTelecomService).activatePhoneNumber("1", "0111");
    }

    @Test
    public void testThrowExceptionIfCustomerIdDoesNotExist() throws CustomerDoesNotExistException {
        expectedException.expect(CustomerDoesNotExistException.class);
        expectedException.expectMessage(CUSTOMER_DOES_NOT_EXIST);

        List<PhoneNumber> emptyPhoneNumbersList = telecomService.getSingleCustomerPhoneNumbers("123");
        assertEquals(emptyPhoneNumbersList, Collections.emptyList());
    }

    public List<PhoneNumber> createMockListOfPhoneNumbers(String phoneNumber) {
        return Collections.singletonList(new PhoneNumber.Builder()
                .withPhoneNumber(phoneNumber)
                .build());
    }
}