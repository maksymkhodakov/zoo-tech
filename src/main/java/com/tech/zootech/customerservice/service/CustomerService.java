package com.tech.zootech.customerservice.service;


import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.domain.dto.CustomerDto;

public interface CustomerService {
    CustomerDto registerCustomer(CustomerRegistrationData customerData);
}
