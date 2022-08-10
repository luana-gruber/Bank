package com.accenture.academico.g3bank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.accenture.academico.g3bank.entity.Conta;
import com.accenture.academico.g3bank.entity.Extrato;

public interface ExtratoRepository extends JpaRepository<Extrato, Long>{
	 List<Extrato> findByConta(Conta conta);
}