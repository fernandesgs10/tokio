package com.example.api.service;

import com.example.api.domain.Customer;
import com.example.api.exception.ExceptionDomain;
import com.example.api.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public void save(Customer customer) {
       repository.save(customer);
    }

    public void remove(long id) {
       repository.deleteById(id);
    }

    public void update(Customer customer) {
        if(customer.getId()==null)
            throw new ExceptionDomain("Customer id notfound");
        repository.save(customer);
    }

    public List<Customer> findAll() {
        return repository.findAllByOrderByNameAsc();
    }

    public Customer findById(Long id) {
       return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new ExceptionDomain("Id not found"))).get();
    }
}