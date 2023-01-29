package com.tech.zootech.customerservice.exceptions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class InvalidCustomerRegistrationDataExceptionTest {
    /**
     * Method under test: {@link InvalidCustomerRegistrationDataException#InvalidCustomerRegistrationDataException(String)}
     */
    @Test
    public void testConstructor() {
        InvalidCustomerRegistrationDataException actualInvalidCustomerRegistrationDataException = new InvalidCustomerRegistrationDataException(
                "An error occurred");
        assertNull(actualInvalidCustomerRegistrationDataException.getCause());
        assertEquals(0, actualInvalidCustomerRegistrationDataException.getSuppressed().length);
        assertEquals("An error occurred", actualInvalidCustomerRegistrationDataException.getMessage());
        assertEquals("An error occurred", actualInvalidCustomerRegistrationDataException.getLocalizedMessage());
    }
}

