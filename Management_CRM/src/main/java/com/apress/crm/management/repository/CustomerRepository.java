package com.apress.crm.management.repository;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.apress.crm.management.model.Customer;

@Component
public class CustomerRepository implements Repository<Customer, UUID> {
    private final Map<UUID, Customer> customers = new ConcurrentHashMap<>();

    @Override
    public Customer save(Customer entity) {
        return entity;
    }

    @Override
    public Customer findById(UUID uuid) {
        return this.customers.get(uuid);
    }

    @Override
    public Iterable<Customer> findAll() {
        return this.customers.values();
    }

    @Override
    public void deleteById(UUID uuid) {
        this.customers.remove(uuid);
    }
}
