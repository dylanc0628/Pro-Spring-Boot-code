package com.apress.crm.management.repository;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import com.apress.crm.management.model.Communication;

@Component
public class CommunicationRepository implements Repository<Communication, UUID> {

    private final Map<UUID, Communication> communications = new ConcurrentHashMap<>();

    @Override
    public Communication save(Communication entity) {
        return entity;
    }

    @Override
    public Communication findById(UUID uuid) {
        return this.communications.get(uuid);
    }
    
    @Override
    public Iterable<Communication> findAll() {
        return this.communications.values();
    }

    @Override
    public void deleteById(UUID uuid) {
        this.communications.remove(uuid);
    }
    
    public Iterable<Communication> findAllByCustomerId(UUID customerId) {
        return this.communications.values().stream()
                .filter(communication -> communication.customerId().equals(customerId))
                .collect(Collectors.toList());
    }
}