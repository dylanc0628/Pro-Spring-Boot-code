package com.apress.crm.management.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.apress.crm.management.model.Address;
import com.apress.crm.management.model.Communication;
import com.apress.crm.management.model.Company;
import com.apress.crm.management.model.Customer;
import com.apress.crm.management.model.CustomerDetailsDTO;
import com.apress.crm.management.repository.AddressRepository;
import com.apress.crm.management.repository.CommunicationRepository;
import com.apress.crm.management.repository.CompanyRepository;
import com.apress.crm.management.repository.CustomerRepository;

@Service
public class ManagementService {

    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;
    private final CommunicationRepository communicationRepository;

    public ManagementService(
            CustomerRepository customerRepository,
            CompanyRepository companyRepository,
            AddressRepository addressRepository,
            CommunicationRepository communicationRepository) {
        this.customerRepository = customerRepository;
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
        this.communicationRepository = communicationRepository;
    }

    public CustomerDetailsDTO createCustomerWithDetails(Customer customer, Company company, Address address, Communication communication) {
        return new CustomerDetailsDTO(customer, company, List.of(address), List.of(communication));
    }

    public CustomerDetailsDTO getCustomerDetails(UUID customerId) {
        Customer customer = this.customerRepository.findById(customerId);
        if (customer == null) {
            return null;
        }
        Company company = this.companyRepository.findById(customer.companyId());
        List<Address> addresses = StreamSupport.stream(this.addressRepository.findAllByCustomerId(customerId).spliterator(), false)
                .collect(Collectors.toList());
        List<Communication> communications = StreamSupport.stream(this.communicationRepository.findAllByCustomerId(customerId).spliterator(), false)
                .collect(Collectors.toList());
        return new CustomerDetailsDTO(customer, company, addresses, communications);
    }
}
