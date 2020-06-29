package br.com.bancopan.api.consume;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ClientDto implements Serializable {

    @JsonProperty("Nome")
    private String nome;

    @JsonProperty("Idade")
    private Integer idade;

    @JsonProperty("Salario")
    private BigDecimal salario;
}
