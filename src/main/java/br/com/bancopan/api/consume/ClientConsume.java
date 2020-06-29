package br.com.bancopan.api.consume;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

public class ClientConsume {

    public Optional<List<ClientDto>> listClientDto() throws Exception {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<ClientDto>> clintDtoResponse =
                    restTemplate.exchange("http://www.mocky.io/v2/5e5ec624310000b738afd85a",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<ClientDto>>() {
                            });

        List<ClientDto> listClientDto = clintDtoResponse.getBody();
        Optional<List<ClientDto>> listClientDtoResult = Optional.of(listClientDto);
        return listClientDtoResult;
    }
}
