package com.apress.crm.management.repository;

@Component
public class AddressRepository implements Repository<Address, UUID> {
    private final Map<UUID, Address> addresses = new ConcurrentHashMap<>();

    @Override
    public Address save(Address entity) {
        return entity;
    }

    @Override
    public Address findById(UUID uuid) {
        return this.addresses.get(uuid);
    }

    @Override
    public Iterable<Address> findAll() {
        return this.addresses.values();
    }

    @Override
    public void deleteById(UUID uuid) {
        this.addresses.remove(uuid);
    }

    public Iterable<Address> findAllByCustomerId(UUID customerId) {
        return this.addresses.values().stream()
                .filter(address -> address.customerId().equals(customerId))
                .collect(Collectors.toList());
    }
}