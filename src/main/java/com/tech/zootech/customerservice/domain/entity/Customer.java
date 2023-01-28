package com.tech.zootech.customerservice.domain.entity;

import com.tech.zootech.customerservice.domain.interfaces.ICustomer;
import com.tech.zootech.security.domain.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "customers")
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer extends AbstractEntity implements ICustomer {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    public Customer(ICustomer iCustomer) {
        this.id = iCustomer.getId();
        this.firstName = iCustomer.getFirstName();
        this.lastName = iCustomer.getLastName();
        this.email = iCustomer.getEmail();
    }
}
