package com.tech.zootech.customerservice.service.impl;

import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.exceptions.InvalidCustomerRegistrationDataException;
import com.tech.zootech.customerservice.service.CustomerValidator;
import org.springframework.stereotype.Service;

import static io.micrometer.common.util.StringUtils.isEmpty;
import static java.util.Objects.nonNull;

@Service
public class CustomerValidatorImpl implements CustomerValidator {
    @Override
    public void validateRegistrationData(CustomerRegistrationData customerData) {
        if (!isValidRegistrationData(customerData)) {
            throw new InvalidCustomerRegistrationDataException("Customer registration data is not valid! Please, check data again");
        }
    }

    private static boolean isValidRegistrationData(CustomerRegistrationData customerData) {
        return nonNull(customerData.getId()) && customerData.getId() > 0
                && nonNull(customerData.getEmail()) && !isEmpty(customerData.getEmail())
                && nonNull(customerData.getFirstName()) && nonNull(customerData.getLastName());
    }
}
