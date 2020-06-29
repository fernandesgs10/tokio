package br.com.bancopan.api.web.rest;

import br.com.bancopan.api.message.ResponseMessage;
import br.com.bancopan.api.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/cliente")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<?> listClient() throws Exception {
        ResponseMessage responseMessage = ResponseMessage.builder().build();
        responseMessage.setError(Boolean.FALSE);
        responseMessage.setDate(new Date());
        responseMessage.setDatas(clientService.listClientDto().get());
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}