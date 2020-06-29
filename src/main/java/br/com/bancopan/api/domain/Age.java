package br.com.bancopan.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Table(name = "AGE")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Age implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "percent not found")
    private Byte age;

    @NotBlank(message = "percent not found")
    private Byte percent;
}
