package com.tech.zootech.security.repo;

import com.tech.zootech.security.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID>, JpaSpecificationExecutor<Contact> {
    @Transactional
    @Modifying
    @Query("delete from Contact c where c.id = :id")
    void delete(UUID id);
}
