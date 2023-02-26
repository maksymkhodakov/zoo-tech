package com.tech.zootech.customerservice.domain.interfaces;

import java.util.List;

public interface ICustomer {
    Long getId();
    String getFirstName();
    String getLastName();
    String getEmail();
    List<? extends IRegistrationHistory> getRegistrationHistory();
}
