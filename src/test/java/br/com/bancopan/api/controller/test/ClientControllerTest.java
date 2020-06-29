package br.com.bancopan.api.controller.test;

import br.com.bancopan.api.consume.ClientDto;
import br.com.bancopan.api.service.ClientService;
import br.com.bancopan.api.web.rest.ClientController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @MockBean
    ClientService clientService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testListClientResponseOK() throws Exception {
        List<ClientDto> listClient = new ArrayList<>();
        ClientDto clientDto = new ClientDto();
        clientDto.setIdade(42);
        clientDto.setNome("Fernandes");
        clientDto.setSalario(BigDecimal.valueOf(20000.00));
        listClient.add(clientDto);
        Optional<List<ClientDto>> listClientDto = Optional.of(listClient);
        when(clientService.listClientDto()).thenReturn(listClientDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/cliente"))
              .andDo(print()).andExpect(status().is(200))
              .andExpect(jsonPath("$.error").value(Boolean.FALSE));
    }

    @Test
    public void testlistClientResponseNotFound() throws Exception {
        mockMvc.perform(get("/client2"))
         .andExpect(status().isNotFound());
    }
}