package com.apress.crm.customer.Controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apress.crm.customer.Domain.Customer;
import com.apress.crm.customer.Interface.Repository;

@RestController 
@RequestMapping("/api/v1/customers")
public class CustomerController {
    
    private final Repository<Customer, UUID> repository;

    public CustomerController(Repository<Customer, UUID> repository) {
        this.repository = repository;
    }

    @GetMapping 
    Iterable<Customer> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Customer findById(@PathVariable UUID id) {
        return repository.findById(id);
    }

    @PostMapping 
    Customer save(@RequestBody Customer customer) {
        return repository.save(customer);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}