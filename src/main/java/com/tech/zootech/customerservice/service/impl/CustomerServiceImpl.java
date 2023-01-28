package com.tech.zootech.customerservice.service.impl;

import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.domain.dto.CustomerDto;
import com.tech.zootech.customerservice.domain.entity.Customer;
import com.tech.zootech.customerservice.repository.CustomerRepository;
import com.tech.zootech.customerservice.service.CustomerService;
import com.tech.zootech.customerservice.service.CustomerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;

    @Override
    public CustomerDto registerCustomer(CustomerRegistrationData customerData) {
        customerValidator.validateRegistrationData(customerData);
        log.info("Customer with id: {} has been validated!", customerData.getId());
        customerRepository.save(new Customer(customerData));
        log.info("Customer with id: {} has been saved to db!", customerData.getId());
        return new CustomerDto(customerData);
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerRepository.getAllByCreateDate()
                .stream()
                .map(CustomerDto::new)
                .toList();
    }
}
