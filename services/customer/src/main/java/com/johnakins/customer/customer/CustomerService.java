package com.johnakins.customer.customer;

import com.johnakins.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository repository;
  private final CustomerMapper mapper;

  public String createCustomer(CustomerRequest request) {
    var customer = this.repository.save(mapper.toCustomer(request));
    return customer.getId();
  }

  public void updateCustomer(String id, CustomerUpdateRequest request) {
    var customer = this.repository.findById(id)
        .orElseThrow(() -> new CustomerNotFoundException(
            String.format("Cannot update customer:: No customer found with the provided ID: %s", id)
        ));
    mergeCustomer(customer, request);
    this.repository.save(customer);
  }

  private void mergeCustomer(Customer customer, CustomerUpdateRequest request) {
    if (StringUtils.isNotBlank(request.firstname())) {
      customer.setFirstname(request.firstname());
    }
    if (StringUtils.isNotBlank(request.lastname())) {
      customer.setLastname(request.lastname());
    }
    if (StringUtils.isNotBlank(request.email())) {
      customer.setEmail(request.email());
    }
    if (request.address() != null) {
      mergeAddress(customer, request.address());
    }
  }

  private void mergeAddress(Customer customer, Address requestAddress) {
      Address address = customer.getAddress();

//    if (address == null) {
//      customer.setAddress(requestAddress);
//      return;
//    }

    if (StringUtils.isNotBlank(requestAddress.getStreet())) {
      address.setStreet(requestAddress.getStreet());
    }
    if (StringUtils.isNotBlank(requestAddress.getHouseNumber())) {
        address.setHouseNumber(requestAddress.getHouseNumber());
    }
    if (StringUtils.isNotBlank(requestAddress.getZipCode())) {
        address.setZipCode(requestAddress.getZipCode());
    }
  }



  public List<CustomerResponse> findAllCustomers() {
    return  this.repository.findAll()
        .stream()
        .map(this.mapper::fromCustomer)
        .collect(Collectors.toList());
  }

  public CustomerResponse findById(String id) {
    return this.repository.findById(id)
        .map(mapper::fromCustomer)
        .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id)));
  }

  public boolean existsById(String id) {
    return this.repository.findById(id)
        .isPresent();
  }

  public void deleteCustomer(String id) {
    this.repository.deleteById(id);
  }
}
