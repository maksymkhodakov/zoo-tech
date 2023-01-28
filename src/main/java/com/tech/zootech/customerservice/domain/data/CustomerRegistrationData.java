package com.tech.zootech.customerservice.domain.data;

import com.tech.zootech.customerservice.domain.interfaces.ICustomer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistrationData implements ICustomer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    public CustomerRegistrationData(ICustomer iCustomer) {
        this.id = iCustomer.getId();
        this.firstName = iCustomer.getFirstName();
        this.lastName = iCustomer.getLastName();
        this.email = iCustomer.getEmail();
    }
}
