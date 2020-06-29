package br.com.bancopan.api.repository;//package br.com.bancopan.api.repository;

import br.com.bancopan.api.domain.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {

    @Query(value = "from Salary t where :salary BETWEEN initSalary AND finishSalary")
    Optional<Salary> getSalaryBetweenSalary(@Param("salary") BigDecimal salary);


}
