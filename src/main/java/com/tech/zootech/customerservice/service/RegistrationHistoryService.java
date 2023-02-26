package com.tech.zootech.customerservice.service;


import com.tech.zootech.customerservice.domain.dto.RegistrationHistoryDTO;

public interface RegistrationHistoryService {
    RegistrationHistoryDTO getByCustomerId(Long id);
}
