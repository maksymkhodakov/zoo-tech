package com.tech.zootech.customerservice.service.impl;

import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.domain.entity.Customer;
import com.tech.zootech.customerservice.domain.entity.RegistrationHistory;
import com.tech.zootech.customerservice.domain.enums.RegistrationStatus;
import com.tech.zootech.customerservice.exceptions.InvalidCustomerRegistrationDataException;
import com.tech.zootech.customerservice.repository.RegistrationHistoryRepository;
import com.tech.zootech.customerservice.service.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.micrometer.common.util.StringUtils.isEmpty;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CustomerValidatorImpl implements CustomerValidator {
    private final RegistrationHistoryRepository registrationHistoryRepository;

    @Override
    public void validateRegistrationData(CustomerRegistrationData customerData) {
        if (!isValidRegistrationData(customerData)) {
            registrationHistoryRepository.save(new RegistrationHistory(RegistrationStatus.ERROR, new Customer(customerData)));
            throw new InvalidCustomerRegistrationDataException("Customer registration data is not valid! Please, check data again");
        }
    }

    private static boolean isValidRegistrationData(CustomerRegistrationData customerData) {
        return nonNull(customerData.getId()) && customerData.getId() > 0
                && nonNull(customerData.getEmail()) && !isEmpty(customerData.getEmail())
                && nonNull(customerData.getFirstName()) && nonNull(customerData.getLastName());
    }
}
