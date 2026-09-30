package com.apress.crm.management.model;

import java.util.UUID;

public record Communication(
    UUID communicationId,
    UUID customerId,
    String communicationType,
    String communicationValue
) {}
