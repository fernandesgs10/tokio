package br.com.bancopan.api.web.rest;

import br.com.bancopan.api.consume.LoadDto;
import br.com.bancopan.api.message.ResponseMessage;
import br.com.bancopan.api.service.ClientService;
import br.com.bancopan.api.service.LendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/credito")
@Validated
public class LendController {

    @Autowired
    private LendService lendService;

    @Autowired
    ClientService clientService;

    @GetMapping("/{nome}/{valorPedido}")
    public ResponseEntity<?> credito(@PathVariable String nome, @PathVariable @Min(value=1, message="Valor incorreto") BigDecimal valorPedido) throws Exception {
         Optional<LoadDto> loadDtoOptional  = lendService.listEmprestimoDto(nome, valorPedido, clientService.listClientDto().get());
         ResponseMessage responseMessage = ResponseMessage.builder().build();
         responseMessage.setError(Boolean.FALSE);
         responseMessage.setDate(new Date());
         responseMessage.setData(loadDtoOptional.get());
         responseMessage.setMessage("Empréstimo processado com sucesso");
         log.info("Empréstimo processado com sucesso");
         return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

}