package com.tech.zootech.customerservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.zootech.customerservice.domain.dto.DownloadFileDTO;
import com.tech.zootech.customerservice.domain.dto.RegistrationHistoryDTO;
import com.tech.zootech.customerservice.domain.entity.RegistrationHistory;
import com.tech.zootech.customerservice.exceptions.ImpossibleFileExtractionException;
import com.tech.zootech.customerservice.exceptions.RegistrationHistoryNotFoundException;
import com.tech.zootech.customerservice.repository.RegistrationHistoryRepository;
import com.tech.zootech.customerservice.service.RegistrationHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationHistoryServiceImpl implements RegistrationHistoryService {
    public static final String CONTENT_TYPE = "application/json";
    private final RegistrationHistoryRepository registrationHistoryRepository;
    private final ObjectMapper objectMapper;

    @Override
    public RegistrationHistoryDTO getByCustomerId(Long id) {
        return new RegistrationHistoryDTO(registrationHistoryRepository.findById(id)
                .orElseThrow(() -> new RegistrationHistoryNotFoundException("Registration history not found!")));
    }

    @Override
    public DownloadFileDTO downloadFile(Long id) {
        final RegistrationHistory history = registrationHistoryRepository.findById(id)
                .orElseThrow(() -> new RegistrationHistoryNotFoundException("Registration history not found!"));
        return getFile(history);
    }

    private DownloadFileDTO getFile(RegistrationHistory history) {
        try (var outputStream = new ByteArrayOutputStream()) {
            objectMapper.writeValue(outputStream, history);
            return DownloadFileDTO.create(CONTENT_TYPE, String.valueOf(history.getId()), new ByteArrayResource(outputStream.toByteArray()));
        } catch (IOException e) {
            log.info("Cannot download file", e);
            throw new ImpossibleFileExtractionException("Cannot download a file");
        }
    }
}
