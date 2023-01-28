package com.tech.zootech.customerservice.repository;

import com.tech.zootech.customerservice.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}