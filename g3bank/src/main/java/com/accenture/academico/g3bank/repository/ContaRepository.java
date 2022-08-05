package com.accenture.academico.g3bank.repository;

import com.accenture.academico.g3bank.entity.Conta;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer>{

}
