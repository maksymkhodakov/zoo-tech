package com.tech.zootech.customerservice.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.exceptions.InvalidCustomerRegistrationDataException;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {CustomerValidatorImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerValidatorImplTest {
    @Autowired
    private CustomerValidatorImpl customerValidatorImpl;

    /**
     * Method under test: {@link CustomerValidatorImpl#validateRegistrationData(CustomerRegistrationData)}
     */
    @Test
    public void testValidateRegistrationData() {
        assertThrows(InvalidCustomerRegistrationDataException.class,
                () -> customerValidatorImpl.validateRegistrationData(new CustomerRegistrationData()));
    }

    /**
     * Method under test: {@link CustomerValidatorImpl#validateRegistrationData(CustomerRegistrationData)}
     */
    @Test
    public void testValidateRegistrationData2() {
        CustomerRegistrationData customerRegistrationData = new CustomerRegistrationData(123L, "Jane", "Doe",
                "jane.doe@example.org");

        customerValidatorImpl.validateRegistrationData(customerRegistrationData);
        assertEquals("jane.doe@example.org", customerRegistrationData.getEmail());
        assertEquals("Doe", customerRegistrationData.getLastName());
        assertEquals(123L, customerRegistrationData.getId().longValue());
        assertEquals("Jane", customerRegistrationData.getFirstName());
    }


    /**
     * Method under test: {@link CustomerValidatorImpl#validateRegistrationData(CustomerRegistrationData)}
     */
    @Test
    public void testValidateRegistrationData4() {
        CustomerRegistrationData customerRegistrationData = mock(CustomerRegistrationData.class);
        when(customerRegistrationData.getLastName()).thenReturn("Doe");
        when(customerRegistrationData.getFirstName()).thenReturn("Jane");
        when(customerRegistrationData.getEmail()).thenReturn("jane.doe@example.org");
        when(customerRegistrationData.getId()).thenReturn(123L);
        customerValidatorImpl.validateRegistrationData(customerRegistrationData);
        verify(customerRegistrationData, atLeast(1)).getId();
        verify(customerRegistrationData, atLeast(1)).getEmail();
        verify(customerRegistrationData).getFirstName();
        verify(customerRegistrationData).getLastName();
    }

    /**
     * Method under test: {@link CustomerValidatorImpl#validateRegistrationData(CustomerRegistrationData)}
     */
    @Test
    public void testValidateRegistrationData5() {
        CustomerRegistrationData customerRegistrationData = mock(CustomerRegistrationData.class);
        when(customerRegistrationData.getLastName())
                .thenThrow(new InvalidCustomerRegistrationDataException("An error occurred"));
        when(customerRegistrationData.getFirstName()).thenReturn("Jane");
        when(customerRegistrationData.getEmail()).thenReturn("jane.doe@example.org");
        when(customerRegistrationData.getId()).thenReturn(123L);
        assertThrows(InvalidCustomerRegistrationDataException.class,
                () -> customerValidatorImpl.validateRegistrationData(customerRegistrationData));
        verify(customerRegistrationData, atLeast(1)).getId();
        verify(customerRegistrationData, atLeast(1)).getEmail();
        verify(customerRegistrationData).getFirstName();
        verify(customerRegistrationData).getLastName();
    }

    /**
     * Method under test: {@link CustomerValidatorImpl#validateRegistrationData(CustomerRegistrationData)}
     */
    @Test
    public void testValidateRegistrationData6() {
        CustomerRegistrationData customerRegistrationData = mock(CustomerRegistrationData.class);
        when(customerRegistrationData.getLastName()).thenReturn("Doe");
        when(customerRegistrationData.getFirstName()).thenReturn("Jane");
        when(customerRegistrationData.getEmail()).thenReturn("");
        when(customerRegistrationData.getId()).thenReturn(123L);
        assertThrows(InvalidCustomerRegistrationDataException.class,
                () -> customerValidatorImpl.validateRegistrationData(customerRegistrationData));
        verify(customerRegistrationData, atLeast(1)).getId();
        verify(customerRegistrationData, atLeast(1)).getEmail();
    }

    /**
     * Method under test: {@link CustomerValidatorImpl#validateRegistrationData(CustomerRegistrationData)}
     */
    @Test
    public void testValidateRegistrationData7() {
        CustomerRegistrationData customerRegistrationData = mock(CustomerRegistrationData.class);
        when(customerRegistrationData.getLastName()).thenReturn("Doe");
        when(customerRegistrationData.getFirstName()).thenReturn("Jane");
        when(customerRegistrationData.getEmail()).thenReturn("jane.doe@example.org");
        when(customerRegistrationData.getId()).thenReturn(0L);
        assertThrows(InvalidCustomerRegistrationDataException.class,
                () -> customerValidatorImpl.validateRegistrationData(customerRegistrationData));
        verify(customerRegistrationData, atLeast(1)).getId();
    }
}

