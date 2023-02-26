package com.tech.zootech.customerservice.service.impl;

import com.tech.zootech.customerservice.domain.dto.RegistrationHistoryDTO;
import com.tech.zootech.customerservice.exceptions.RegistrationHistoryNotFoundException;
import com.tech.zootech.customerservice.repository.RegistrationHistoryRepository;
import com.tech.zootech.customerservice.service.RegistrationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationHistoryServiceImpl implements RegistrationHistoryService {
    private final RegistrationHistoryRepository registrationHistoryRepository;

    @Override
    public RegistrationHistoryDTO getByCustomerId(Long id) {
        return new RegistrationHistoryDTO(registrationHistoryRepository.findById(id)
                .orElseThrow(() -> new RegistrationHistoryNotFoundException("Registration history not found!")));
    }
}
