package com.example.api.consume;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class CEPConsume {

    public CEPDto getCep(String cep) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CEPDto> response2 = restTemplate.getForEntity("https://viacep.com.br/ws/" + cep +"/json/", CEPDto.class);
        return response2.getBody();
    }
}
