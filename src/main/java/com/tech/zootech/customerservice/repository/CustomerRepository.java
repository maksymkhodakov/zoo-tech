package com.tech.zootech.customerservice.repository;

import com.tech.zootech.customerservice.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select * from customers c order by c.created desc", nativeQuery = true)
    List<Customer> getAllByCreateDate();
}