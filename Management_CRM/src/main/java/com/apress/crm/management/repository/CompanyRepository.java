package com.apress.crm.management.repository;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.apress.crm.management.model.Company;

@Component
public class CompanyRepository implements Repository<Company, UUID> {
    private final Map<UUID, Company> companies = new ConcurrentHashMap<>();

    @Override
    public Company save(Company entity) {
        return entity;
    }

    @Override
    public Company findById(UUID uuid) {
        return this.companies.get(uuid);
    }

    @Override
    public Iterable<Company> findAll() {
        return this.companies.values();
    }

    @Override
    public void deleteById(UUID uuid) {
        this.companies.remove(uuid);
    }
}