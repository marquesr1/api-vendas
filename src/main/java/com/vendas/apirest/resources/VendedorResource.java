package com.vendas.apirest.resources;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API Vendas")
@CrossOrigin(origins="*")
public class VendedorResource {
	
	@Autowired
	VendedorRepository vendedorRepository; 
	
	@GetMapping("/vendedores")
	@ApiOperation(value="Retorna uma lista de vendedores com o total de todas suas vendas")
	public List<Vendedor> listarVendedores(){
		List<Vendedor> vendedores = vendedorRepository.findAll();
		for(Vendedor vendedor : vendedores) {
			vendedor.setVendas(vendedorRepository.totalVendas(vendedor.getId()));
			vendedorRepository.save(vendedor);
		}
		return vendedores;
	}
	
	@GetMapping("/vendedores/{date1}/{date2}")
	@ApiOperation(value="Retorna uma lista de vendedores com total de vendas no período informado (formato de data: aaaa-mm-dd)")
	public List<Vendedor> listarVendedores(@PathVariable(value="date1") Date date1, @PathVariable(value="date2") Date date2){
		List<Vendedor> vendedores = vendedorRepository.findAll();
		for(Vendedor vendedor : vendedores) {
			vendedor.setVendas(vendedorRepository.totalVendasPeriodo(vendedor.getId(), date1, date2));
		}
		return vendedores;
	}
	
	@GetMapping("/vendedores/{id}")
	@ApiOperation(value="Retorna um vendedor específico")
	public Vendedor listaVendedor(@PathVariable(value="id") long id){
		return vendedorRepository.findById(id);
	}
	
	@GetMapping("/vendedores/{id}/{date1}_{date2}")
	@ApiOperation(value="Retorna um vendedor específico com total de vendas no período informado (formato de data: aaaa-mm-dd)")
	public Vendedor listaVendedorPeriodo(@PathVariable(value="id") long id, @PathVariable(value="date1") Date date1, @PathVariable(value="date2") Date date2){
		Vendedor vendedor = vendedorRepository.findById(id);
		vendedor.setVendas(vendedorRepository.totalVendasPeriodo(id, date1, date2));
		return vendedor;
	}
		
	@PostMapping("/vendedores")
	@ApiOperation(value="Salva um vendedor")
	public Vendedor salvarVendedor(@RequestBody Vendedor vendedor) {
		return vendedorRepository.save(vendedor);
	}
	
	@DeleteMapping("/vendedores")
	@ApiOperation(value="Deleta um vendedor")
	public void deletarVendedor(@RequestBody Vendedor vendedor) {
		vendedorRepository.delete(vendedor);
	}
	
	@PutMapping("/vendedores")
	@ApiOperation(value="Atualiza um vendedor")
	public Vendedor atualizarVendedor(@RequestBody Vendedor vendedor) {
		return vendedorRepository.save(vendedor);
	}
	
}
