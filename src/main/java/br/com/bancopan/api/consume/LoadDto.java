package br.com.bancopan.api.consume;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class LoadDto {

    private String nome;
    private BigDecimal salario;
    private BigDecimal[] valorParcela;
    private BigDecimal valorPedido;
    private BigDecimal valorEmprestado;
    private Integer quantidadeParcelas;


}
