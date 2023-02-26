package com.tech.zootech.customerservice.repository;

import com.tech.zootech.customerservice.domain.entity.RegistrationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationHistoryRepository extends JpaRepository<RegistrationHistory, Long> {
}
