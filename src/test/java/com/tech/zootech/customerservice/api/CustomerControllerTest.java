package com.tech.zootech.customerservice.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.domain.dto.CustomerDto;
import com.tech.zootech.customerservice.domain.dto.CustomerFullName;
import com.tech.zootech.customerservice.domain.entity.Customer;
import com.tech.zootech.customerservice.repository.CustomerRepository;
import com.tech.zootech.customerservice.service.CustomerValidator;
import com.tech.zootech.customerservice.service.impl.CustomerServiceImpl;
import com.tech.zootech.customerservice.service.impl.EmailSenderServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class CustomerControllerTest {

    /**
     * Method under test: {@link CustomerController#registerCustomer(CustomerRegistrationData)}
     */
    @Test
    public void testRegisterCustomer2() throws MailException {
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.save((Customer) any()))
                .thenReturn(new Customer("Jane", "Doe", "jane.doe@example.org", 1));
        CustomerValidator customerValidator = mock(CustomerValidator.class);
        doNothing().when(customerValidator).validateRegistrationData((CustomerRegistrationData) any());
        JavaMailSender javaMailSender = mock(JavaMailSender.class);
        doNothing().when(javaMailSender).send((SimpleMailMessage) any());
        CustomerController customerController = new CustomerController(
                new CustomerServiceImpl(customerRepository, customerValidator, new EmailSenderServiceImpl(javaMailSender)));
        ResponseEntity<CustomerDto> actualRegisterCustomerResult = customerController
                .registerCustomer(new CustomerRegistrationData());
        assertTrue(actualRegisterCustomerResult.hasBody());
        assertTrue(actualRegisterCustomerResult.getHeaders().isEmpty());
        assertEquals(200, actualRegisterCustomerResult.getStatusCodeValue());
        CustomerDto body = actualRegisterCustomerResult.getBody();
        assertNull(body.getLastName());
        assertNull(body.getFirstName());
        assertNull(body.getEmail());
        verify(customerRepository).save((Customer) any());
        verify(customerValidator).validateRegistrationData((CustomerRegistrationData) any());
        verify(javaMailSender).send((SimpleMailMessage) any());
    }

    /**
     * Method under test: {@link CustomerController#getAll()}
     */
    @Test
    public void testGetAll() {
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.getAllByCreateDate()).thenReturn(new ArrayList<>());
        CustomerValidator customerValidator = mock(CustomerValidator.class);
        ResponseEntity<List<CustomerDto>> actualAll = (new CustomerController(new CustomerServiceImpl(customerRepository,
                customerValidator, new EmailSenderServiceImpl(new JavaMailSenderImpl())))).getAll();
        assertTrue(actualAll.hasBody());
        assertEquals(200, actualAll.getStatusCodeValue());
        assertTrue(actualAll.getHeaders().isEmpty());
        verify(customerRepository).getAllByCreateDate();
    }

    /**
     * Method under test: {@link CustomerController#getNames()}
     */
    @Test
    public void testGetNames() {
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.getNames()).thenReturn(new ArrayList<>());
        CustomerValidator customerValidator = mock(CustomerValidator.class);
        ResponseEntity<List<CustomerFullName>> actualNames = (new CustomerController(new CustomerServiceImpl(
                customerRepository, customerValidator, new EmailSenderServiceImpl(new JavaMailSenderImpl())))).getNames();
        assertTrue(actualNames.hasBody());
        assertEquals(200, actualNames.getStatusCodeValue());
        assertTrue(actualNames.getHeaders().isEmpty());
        verify(customerRepository).getNames();
    }
}

