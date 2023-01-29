package com.tech.zootech.customerservice.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
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
import com.tech.zootech.customerservice.service.EmailSenderService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {CustomerServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceImplTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerServiceImpl customerServiceImpl;

    @MockBean
    private CustomerValidator customerValidator;

    @MockBean
    private EmailSenderService emailSenderService;

    /**
     * Method under test: {@link CustomerServiceImpl#registerCustomer(CustomerRegistrationData)}
     */
    @Test
    public void testRegisterCustomer() {
        when(customerRepository.save((Customer) any()))
                .thenReturn(new Customer("Jane", "Doe", "jane.doe@example.org", 1));
        doNothing().when(customerValidator).validateRegistrationData((CustomerRegistrationData) any());
        doNothing().when(emailSenderService).send((String) any(), (String) any(), (String) any(), (String) any());
        CustomerDto actualRegisterCustomerResult = customerServiceImpl.registerCustomer(new CustomerRegistrationData());
        assertNull(actualRegisterCustomerResult.getEmail());
        assertNull(actualRegisterCustomerResult.getLastName());
        assertNull(actualRegisterCustomerResult.getFirstName());
        verify(customerRepository).save((Customer) any());
        verify(customerValidator).validateRegistrationData((CustomerRegistrationData) any());
        verify(emailSenderService).send((String) any(), (String) any(), (String) any(), (String) any());
    }


    /**
     * Method under test: {@link CustomerServiceImpl#getAll()}
     */
    @Test
    public void testGetAll() {
        when(customerRepository.getAllByCreateDate()).thenReturn(new ArrayList<>());
        assertTrue(customerServiceImpl.getAll().isEmpty());
        verify(customerRepository).getAllByCreateDate();
    }

    /**
     * Method under test: {@link CustomerServiceImpl#getAll()}
     */
    @Test
    public void testGetAll2() {
        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer("Jane", "Doe", "jane.doe@example.org", 1));
        when(customerRepository.getAllByCreateDate()).thenReturn(customerList);
        List<CustomerDto> actualAll = customerServiceImpl.getAll();
        assertEquals(1, actualAll.size());
        CustomerDto getResult = actualAll.get(0);
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals("Doe", getResult.getLastName());
        assertEquals("Jane", getResult.getFirstName());
        verify(customerRepository).getAllByCreateDate();
    }

    /**
     * Method under test: {@link CustomerServiceImpl#getAll()}
     */
    @Test
    public void testGetAll3() {
        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer("Jane", "Doe", "jane.doe@example.org", 1));
        customerList.add(new Customer("Jane", "Doe", "jane.doe@example.org", 1));
        when(customerRepository.getAllByCreateDate()).thenReturn(customerList);
        List<CustomerDto> actualAll = customerServiceImpl.getAll();
        assertEquals(2, actualAll.size());
        CustomerDto getResult = actualAll.get(0);
        assertEquals("Doe", getResult.getLastName());
        CustomerDto getResult1 = actualAll.get(1);
        assertEquals("Doe", getResult1.getLastName());
        assertEquals("Jane", getResult1.getFirstName());
        assertEquals("jane.doe@example.org", getResult1.getEmail());
        assertEquals("Jane", getResult.getFirstName());
        assertEquals("jane.doe@example.org", getResult.getEmail());
        verify(customerRepository).getAllByCreateDate();
    }

    /**
     * Method under test: {@link CustomerServiceImpl#getAll()}
     */

    /**
     * Method under test: {@link CustomerServiceImpl#getAll()}
     */
    @Test
    public void testGetAll5() {
        Customer customer = mock(Customer.class);
        when(customer.getEmail()).thenReturn("jane.doe@example.org");
        when(customer.getFirstName()).thenReturn("Jane");
        when(customer.getLastName()).thenReturn("Doe");

        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerRepository.getAllByCreateDate()).thenReturn(customerList);
        List<CustomerDto> actualAll = customerServiceImpl.getAll();
        assertEquals(1, actualAll.size());
        CustomerDto getResult = actualAll.get(0);
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals("Doe", getResult.getLastName());
        assertEquals("Jane", getResult.getFirstName());
        verify(customerRepository).getAllByCreateDate();
        verify(customer).getEmail();
        verify(customer).getFirstName();
        verify(customer).getLastName();
    }

    /**
     * Method under test: {@link CustomerServiceImpl#getNames()}
     */
    @Test
    public void testGetNames() {
        ArrayList<CustomerFullName> customerFullNameList = new ArrayList<>();
        when(customerRepository.getNames()).thenReturn(customerFullNameList);
        List<CustomerFullName> actualNames = customerServiceImpl.getNames();
        assertSame(customerFullNameList, actualNames);
        assertTrue(actualNames.isEmpty());
        verify(customerRepository).getNames();
    }
}

