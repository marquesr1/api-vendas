package com.vendas.apirest.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vendas.apirest.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{

	Venda findById(long id);
	
	@Query(value= "SELECT * FROM venda WHERE vendedor_id = ?1", nativeQuery = true)
	List<Venda> findByVendedor(long id);
		
	@Query(value= "SELECT * FROM venda WHERE vendedor_id = ?1 AND data BETWEEN ?2 AND ?3", nativeQuery = true)
	List<Venda> findByVendedor(long id, Date data1, Date data2);
	
}
