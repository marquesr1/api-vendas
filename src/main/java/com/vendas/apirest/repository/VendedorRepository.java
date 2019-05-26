package com.vendas.apirest.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vendas.apirest.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{

	Vendedor findById(long id);
	
	@Query(value= "SELECT COUNT(*) FROM venda WHERE vendedor_id = ?1", nativeQuery = true)
	long totalVendas(long id);
	
	@Query(value= "SELECT COUNT(*) FROM venda WHERE vendedor_id = ?1 AND data BETWEEN ?2 AND ?3", nativeQuery = true)
	long totalVendasPeriodo(long id, Date data1, Date data2);
	
}
