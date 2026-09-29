package com.apress.crm.customer.Application;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class CustRepoApp implements Repository<Customer, UUID> {

    Map<UUID, Customer> customers = new ConcurrentHashMap<> ();

    @Override 
    public Customer save(Customer entity) {
        if (entity.id() == null) {
            entity = new Customer(entity.name(), entity.email(), entity.phone());
        }
        customers.put(entity.id(), entity);
        return entity;
    }

    @Override 
    public Customer findById(UUID id) {
        return customers.get(id);
    }

    @Override 
    public Iterable<Customer> findAll() {
        return customers.values();
    }

    @Override 
    public void deleteById(UUID id) {
        customers.remove(id);
    }
}