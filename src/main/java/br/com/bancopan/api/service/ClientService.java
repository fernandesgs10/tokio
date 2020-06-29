package br.com.bancopan.api.service;

import br.com.bancopan.api.consume.ClientConsume;
import br.com.bancopan.api.consume.ClientDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    public Optional<List<ClientDto>> listClientDto() throws Exception {
        return new ClientConsume().listClientDto();
    }
}
