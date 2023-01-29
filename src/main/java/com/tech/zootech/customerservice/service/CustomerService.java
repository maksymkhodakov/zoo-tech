package com.tech.zootech.customerservice.service;

import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.domain.dto.CustomerDto;
import com.tech.zootech.customerservice.domain.dto.CustomerFullName;

import java.util.List;

public interface CustomerService {
    CustomerDto registerCustomer(CustomerRegistrationData customerData);

    List<CustomerDto> getAll();

    List<CustomerFullName> getNames();
}
