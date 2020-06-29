package br.com.bancopan.api.service;

import br.com.bancopan.api.consume.ClientConsume;
import br.com.bancopan.api.consume.ClientDto;
import br.com.bancopan.api.consume.LoadDto;
import br.com.bancopan.api.domain.Age;
import br.com.bancopan.api.domain.Salary;
import br.com.bancopan.api.exception.ExceptionDomain;
import br.com.bancopan.api.repository.AgeRepository;
import br.com.bancopan.api.repository.SalaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LendService {

    @Autowired
    AgeRepository ageRepository;

    @Autowired
    SalaryRepository salaryRepository;

    public Optional<LoadDto> listEmprestimoDto(String nome, BigDecimal valorPedido, List<ClientDto> listClientDto) throws Exception {
        ClientDto clientDto = listClientDto.stream()
                .filter(x -> nome.equals(x.getNome()))
                .findAny()
                .orElse(null);

        if(clientDto == null)
            throw new ExceptionDomain("Nome não encontrado");

        List<Age> listAgeResult = new ArrayList<>();
        List<Age> listAge = ageRepository.findAll();
        Age ageResult;
        listAge.stream()
             .forEach(i -> {
                if (i.getAge() > clientDto.getIdade() )
                    listAgeResult.add(i);
              });

        if(!listAgeResult.isEmpty())
            ageResult = listAge.get(listAgeResult.size());
        else
            ageResult = listAge.get(0);

        System.out.println(ageResult.getAge() + "-" + ageResult.getPercent());

        Double valorEmprestado = null;
        if(valorPedido.doubleValue() < clientDto.getSalario().doubleValue())
            valorEmprestado = ageResult.getPercent() * valorPedido.doubleValue() / 100;
        else
            valorEmprestado = ageResult.getPercent() * clientDto.getSalario().doubleValue() / 100;

        Double valorEmprestadoResult = (valorPedido.doubleValue() > valorEmprestado) ? valorEmprestado : valorPedido.doubleValue();
        Optional<Salary> salary = salaryRepository.getSalaryBetweenSalary(clientDto.getSalario());

        Double valorParcelaResult = salary.isPresent() ? valorEmprestadoResult.doubleValue() * salary.get().getPercent() / 100 : valorEmprestadoResult.doubleValue() * 45 / 100;
        Double parcela = valorEmprestadoResult / valorParcelaResult;
        Double calculate = valorParcelaResult * parcela.intValue();

        Integer resultParcelas;
        Double parseResult = 0.0;
        BigDecimal test2[];
        if(calculate < valorEmprestadoResult) {
            BigDecimal b = new BigDecimal(valorEmprestadoResult - calculate).setScale(2, RoundingMode.UP);
            parseResult = b.doubleValue();
            resultParcelas = parcela.intValue() + 1;
            test2 = new BigDecimal[]{BigDecimal.valueOf(valorParcelaResult), BigDecimal.valueOf(parseResult)};
        }
        else {
            resultParcelas = parcela.intValue();
            test2 = new BigDecimal[]{BigDecimal.valueOf(valorParcelaResult)};
        }

        Double parcelaResult2 = parcela ;
        LoadDto loadDto = LoadDto.builder()
                         .nome(nome)
                         .valorPedido(valorPedido)
                         .salario(clientDto.getSalario())
                         .valorParcela(test2)
                         .valorEmprestado(BigDecimal.valueOf(valorEmprestadoResult))
                         .quantidadeParcelas(resultParcelas).build();

        Optional loadDtoOptional = Optional.of(loadDto);

        return loadDtoOptional;
    }
}
