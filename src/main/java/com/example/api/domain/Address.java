package com.example.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "ADDRESS")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Address não preenchido")
    private String endereco;

    @NotBlank(message = "Estado não preenchido")
    private String estado;

    @NotBlank(message = "Complemento não preenchido")
    private String complemento;

    @NotBlank(message = "Localidade não preenchido")
    private String localidade;

    @NotBlank(message = "UF não preenchido")
    private String uf;

    @NotBlank(message = "Cep não preenchido")
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

}
