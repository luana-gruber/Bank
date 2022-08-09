package com.accenture.academico.g3bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accenture.academico.g3bank.entity.Agencia;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Long>{

}
