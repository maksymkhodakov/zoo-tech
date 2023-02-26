package com.tech.zootech.customerservice.domain.entity;

import com.tech.zootech.customerservice.domain.enums.RegistrationStatus;
import com.tech.zootech.security.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registration_histories")
public class RegistrationHistory extends AbstractEntity {
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private RegistrationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Customer customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RegistrationHistory that = (RegistrationHistory) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
