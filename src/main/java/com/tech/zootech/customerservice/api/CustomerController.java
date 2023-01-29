package com.tech.zootech.customerservice.api;

import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.domain.dto.CustomerDto;
import com.tech.zootech.customerservice.domain.dto.CustomerFullName;
import com.tech.zootech.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerDto> registerCustomer(@Valid @RequestBody CustomerRegistrationData customerData) {
        return ResponseEntity.ok(customerService.registerCustomer(customerData));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/all/names")
    public ResponseEntity<List<CustomerFullName>> getNames() {
        return ResponseEntity.ok(customerService.getNames());
    }
}
