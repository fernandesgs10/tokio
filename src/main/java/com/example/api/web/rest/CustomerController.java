package com.example.api.web.rest;

import com.example.api.domain.Customer;
import com.example.api.message.ResponseMessage;
import com.example.api.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/customers")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService service;

    public ResponseEntity<?> findAll() {
        ResponseMessage responseMessage = ResponseMessage.builder().build();
        responseMessage.setError(Boolean.FALSE);
        responseMessage.setDate(new Date());
        responseMessage.setListData(service.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        ResponseMessage responseMessage = ResponseMessage.builder().build();
        Customer customer = service.findById(id);
        responseMessage.setError(Boolean.FALSE);
        responseMessage.setDate(new Date());
        responseMessage.setData(customer);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody Customer customer) {
         service.save(customer);
         ResponseMessage responseMessage = ResponseMessage.builder().build();
         responseMessage.setError(Boolean.FALSE);
         responseMessage.setDate(new Date());
         responseMessage.setMessage("Customer save with sucess");
         log.info("save with sucess");
         return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Customer customer) {
        service.update(customer);
        ResponseMessage responseMessage = ResponseMessage.builder().build();
        responseMessage.setError(Boolean.FALSE);
        responseMessage.setDate(new Date());
        responseMessage.setMessage("Customer update with sucess");
        log.info("update with sucess");
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        service.remove(id);
        ResponseMessage responseMessage = ResponseMessage.builder().build();
        responseMessage.setError(Boolean.FALSE);
        responseMessage.setDate(new Date());
        responseMessage.setMessage("Customer remove with sucess");
        log.info("remove with sucess");
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}