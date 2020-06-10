package com.example.api.web.rest;

import com.example.api.consume.CEPConsume;
import com.example.api.consume.CEPDto;
import com.example.api.message.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/cep")
@Validated
public class CepController {

    @GetMapping("/{cep}")
    public ResponseEntity<?> getCep(@PathVariable String cep) throws Exception {
        ResponseMessage responseMessage = ResponseMessage.builder().build();
        CEPDto cepResult = new CEPConsume().getCep(cep);
        responseMessage.setError(Boolean.FALSE);
        responseMessage.setDate(new Date());
        responseMessage.setData(cepResult);
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}