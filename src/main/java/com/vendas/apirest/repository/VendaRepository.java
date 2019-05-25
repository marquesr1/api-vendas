package com.vendas.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.apirest.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{

}
