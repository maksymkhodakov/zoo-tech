package com.tech.zootech.customerservice.domain.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.domain.interfaces.ICustomer;
import org.junit.Ignore;
import org.junit.Test;

public class CustomerTest {
    /**
     * Method under test: {@link Customer#Customer(ICustomer)}
     */
    @Test
    public void testConstructor() {
        Customer actualCustomer = new Customer(new CustomerRegistrationData());
        assertNull(actualCustomer.getLastName());
        assertNull(actualCustomer.getId());
        assertNull(actualCustomer.getFirstName());
        assertNull(actualCustomer.getEmail());
    }

    /**
     * Method under test: {@link Customer#Customer(ICustomer)}
     */
    @Test
    public void testConstructor2() {
        Customer actualCustomer = new Customer(new Customer("Jane", "Doe", "jane.doe@example.org", 1));
        assertEquals("Doe", actualCustomer.getLastName());
        assertNull(actualCustomer.getId());
        assertEquals("Jane", actualCustomer.getFirstName());
        assertEquals("jane.doe@example.org", actualCustomer.getEmail());
    }
}
