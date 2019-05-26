package com.vendas.apirest.resources;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vendas.apirest.model.Venda;
import com.vendas.apirest.repository.VendaRepository;

@RestController
@RequestMapping(value="/api")
public class VendaResource {
	
	@Autowired
	VendaRepository vendaRepository;
	
	@GetMapping("/vendas")
	public List<Venda> listarVendas(){
		return vendaRepository.findAll();
	}
	
	@GetMapping("/vendas/{id}")
	public Venda listaVenda(@PathVariable(value="id") long id){
		return vendaRepository.findById(id);
	}
	
	@GetMapping("/vendas/vendedor/{id}")
	public List<Venda> listaVendaPorVendedor(@PathVariable(value="id") long id){
		return vendaRepository.findByVendedor(id);
	}
	
	@GetMapping("/vendas/vendedor/{id}/{date1}_{date2}")
	public List<Venda> listaVendaPorVendedorPeriodo(@PathVariable(value="id") long id, @PathVariable(value="date1") Date date1, @PathVariable(value="date2") Date date2){
		return vendaRepository.findByVendedor(id, date1, date2);
	}
	
	@PostMapping("/vendas")
	public Venda salvarVenda(@RequestBody Venda venda){
		return vendaRepository.save(venda);
	}
	
	@DeleteMapping("/vendas")
	public void deletarVenda(@RequestBody Venda venda){
		vendaRepository.delete(venda);
	}
	
	@PutMapping("/vendas")
	public Venda atualizarVenda(@RequestBody Venda venda){
		return vendaRepository.save(venda);
	}
	
}
