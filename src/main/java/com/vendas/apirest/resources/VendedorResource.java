package com.vendas.apirest.resources;

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
		return vendedorRepository.findAll();
	}
	
	@GetMapping("/vendedores/{id}")
	public Vendedor listaVendedor(@PathVariable(value="id") long id){
		return vendedorRepository.findById(id);
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
