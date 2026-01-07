package com.johnakins.customer.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerUpdateRequest(
    String firstname,
    String lastname,
    @Email
    String email,
    Address address
) {

}
