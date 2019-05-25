package com.vendas.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.apirest.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{

	Vendedor findById(long id);
	
}
