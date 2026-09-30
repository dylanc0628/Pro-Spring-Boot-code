package com.apress.crm.management.model;

import java.util.UUID;

public record Customer(
    UUID customerId,
    String firstName,
    String lastName,
    String jobTitle,
    String email,
    String phone,
    UUID companyId
) {}
