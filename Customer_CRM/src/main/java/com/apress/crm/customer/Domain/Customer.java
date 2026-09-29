package com.apress.crm.customer.Domain;

import java.util.UUID;

public record Customer(UUID id, String name, String email, String phone) {
    public Customer(String name, String email, String phone) {
        this(UUID.randomeUUID(), name, email, phone);
    }
}