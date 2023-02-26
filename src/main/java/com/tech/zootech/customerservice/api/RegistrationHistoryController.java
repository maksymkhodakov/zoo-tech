package com.tech.zootech.customerservice.api;

import com.tech.zootech.customerservice.domain.dto.RegistrationHistoryDTO;
import com.tech.zootech.customerservice.service.RegistrationHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration-history")
@RequiredArgsConstructor
public class RegistrationHistoryController {
    private final RegistrationHistoryService registrationHistoryService;
    @GetMapping("/customer/{id}")
    public ResponseEntity<RegistrationHistoryDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(registrationHistoryService.getByCustomerId(id));
    }
}
