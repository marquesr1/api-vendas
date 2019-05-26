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

import com.vendas.apirest.model.Vendedor;
import com.vendas.apirest.repository.VendedorRepository;

@RestController
@RequestMapping(value="/api")
public class VendedorResource {
	
	@Autowired
	VendedorRepository vendedorRepository; 
	
	@GetMapping("/vendedores")
	public List<Vendedor> listarVendedores(){
		List<Vendedor> vendedores = vendedorRepository.findAll();
		for(Vendedor vendedor : vendedores) {
			vendedor.setVendas(vendedorRepository.totalVendas(vendedor.getId()));
			vendedorRepository.save(vendedor);
		}
		return vendedores;
	}
	
	@GetMapping("/vendedores/{date1}/{date2}")
	public List<Vendedor> listarVendedores(@PathVariable(value="date1") Date date1, @PathVariable(value="date2") Date date2){
		List<Vendedor> vendedores = vendedorRepository.findAll();
		for(Vendedor vendedor : vendedores) {
			vendedor.setVendas(vendedorRepository.totalVendasPeriodo(vendedor.getId(), date1, date2));
		}
		return vendedores;
	}
	
	@GetMapping("/vendedores/{id}")
	public Vendedor listaVendedor(@PathVariable(value="id") long id){
		return vendedorRepository.findById(id);
	}
	
	@GetMapping("/vendedores/{id}/{date1}_{date2}")
	public Vendedor listaVendedorPeriodo(@PathVariable(value="id") long id, @PathVariable(value="date1") Date date1, @PathVariable(value="date2") Date date2){
		Vendedor vendedor = vendedorRepository.findById(id);
		vendedor.setVendas(vendedorRepository.totalVendasPeriodo(id, date1, date2));
		return vendedor;
	}
		
	@PostMapping("/vendedores")
	public Vendedor salvarVendedor(@RequestBody Vendedor vendedor) {
		return vendedorRepository.save(vendedor);
	}
	
	@DeleteMapping("/vendedores")
	public void deletarVendedor(@RequestBody Vendedor vendedor) {
		vendedorRepository.delete(vendedor);
	}
	
	@PutMapping("/vendedores")
	public Vendedor atualizarVendedor(@RequestBody Vendedor vendedor) {
		return vendedorRepository.save(vendedor);
	}
	
}
