package com.tech.zootech.customerservice.service.impl;

import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.domain.dto.CustomerDto;
import com.tech.zootech.customerservice.domain.dto.CustomerFullName;
import com.tech.zootech.customerservice.domain.entity.Customer;
import com.tech.zootech.customerservice.domain.entity.RegistrationHistory;
import com.tech.zootech.customerservice.domain.enums.RegistrationStatus;
import com.tech.zootech.customerservice.repository.CustomerRepository;
import com.tech.zootech.customerservice.repository.RegistrationHistoryRepository;
import com.tech.zootech.customerservice.service.CustomerService;
import com.tech.zootech.customerservice.service.CustomerValidator;
import com.tech.zootech.customerservice.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final RegistrationHistoryRepository registrationHistoryRepository;
    private final CustomerRepository customerRepository;
    private final CustomerValidator customerValidator;
    private final EmailSenderService emailSenderService;

    @Override
    public CustomerDto registerCustomer(CustomerRegistrationData customerData) {
        customerValidator.validateRegistrationData(customerData);
        log.info("Customer with id: {} has been validated!", customerData.getId());
        final var customer = new Customer(customerData);
        customerRepository.save(customer);
        registrationHistoryRepository.save(new RegistrationHistory(RegistrationStatus.SUCCESS, customer));
        log.info("Customer with id: {} has been saved to db!", customerData.getId());
        emailSenderService.send(EmailSenderServiceImpl.serverRedirectEmail, customerData.getEmail(), "Welcome email!", "Welcome dear {}!. Happy discovering!");
        return new CustomerDto(customerData);
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerRepository.getAllByCreateDate()
                .stream()
                .map(CustomerDto::new)
                .toList();
    }

    @Override
    public List<CustomerFullName> getNames() {
        return customerRepository.getNames();
    }
}
