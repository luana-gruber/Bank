package com.accenture.academico.g3bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.accenture.academico.g3bank.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	 @Query(value="SELECT * FROM cliente c where c.cpf_cliente= :cpf", nativeQuery = true)
	 Optional<Cliente> findByCpf(String cpf);
	 
}

