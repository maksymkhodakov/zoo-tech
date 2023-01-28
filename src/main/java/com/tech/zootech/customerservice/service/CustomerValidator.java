package com.tech.zootech.customerservice.service;


import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;

public interface CustomerValidator {
    void validateRegistrationData(CustomerRegistrationData customerData);
}
