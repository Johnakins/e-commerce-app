package com.johnakins.customer.customer;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService service;

  @PostMapping
  public ResponseEntity<String> createCustomer(
      @RequestBody @Valid CustomerRequest request
  ) {
    return ResponseEntity.ok(this.service.createCustomer(request));
  }

  @PatchMapping("/{customer-id}")
  public ResponseEntity<Void> updateCustomer(
      @PathVariable("customer-id") String customerId,
      @RequestBody @Valid CustomerUpdateRequest request
  ) {
    this.service.updateCustomer(customerId,request);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<CustomerResponse>> findAll() {
    return ResponseEntity.ok(this.service.findAllCustomers());
  }

  @GetMapping("/exists/{customer-id}")
  public ResponseEntity<Boolean> existsById(
      @PathVariable("customer-id") String customerId
  ) {
    return ResponseEntity.ok(this.service.existsById(customerId));
  }

  @GetMapping("/{customer-id}")
  public ResponseEntity<CustomerResponse> findById(
      @PathVariable("customer-id") String customerId
  ) {
    return ResponseEntity.ok(this.service.findById(customerId));
  }

  @DeleteMapping("/{customer-id}")
  public ResponseEntity<Void> delete(
      @PathVariable("customer-id") String customerId
  ) {
    this.service.deleteCustomer(customerId);
    return ResponseEntity.accepted().build();
  }

}
