package br.com.bancopan.api.repository;

import br.com.bancopan.api.domain.Age;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgeRepository extends JpaRepository<Age, Long> {



}
