package com.example.api.service;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.exception.ExceptionDomain;
import com.example.api.repository.AddressRepository;
import com.example.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository repository;

    public void save(Address address) {
       repository.save(address);
    }

    public void remove(long id) {
       repository.deleteById(id);
    }

    public void update(Address customer) {
        if(customer.getId()==null)
            throw new ExceptionDomain("Address id notfound");
        repository.save(customer);
    }

    public List<Address> findAll() {
        return repository.findAll();
    }

    public Address findById(Long id) {
       return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new ExceptionDomain("Id not found"))).get();
    }
}