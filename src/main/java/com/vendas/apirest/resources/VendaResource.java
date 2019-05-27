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

import com.vendas.apirest.model.Venda;
import com.vendas.apirest.repository.VendaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API Vendas")
@CrossOrigin(origins="*")
public class VendaResource {
	
	@Autowired
	VendaRepository vendaRepository;
	
	@GetMapping("/vendas")
	@ApiOperation(value="Retorna uma lista de vendas")
	public List<Venda> listarVendas(){
		return vendaRepository.findAll();
	}
	
	@GetMapping("/vendas/{id}")
	@ApiOperation(value="Retorna uma venda conforme o Id informado")
	public Venda listaVenda(@PathVariable(value="id") long id){
		return vendaRepository.findById(id);
	}
	
	@GetMapping("/vendas/vendedor/{id}")
	@ApiOperation(value="Retorna uma lista de vendas de um vendedor específico")
	public List<Venda> listaVendaPorVendedor(@PathVariable(value="id") long id){
		return vendaRepository.findByVendedor(id);
	}
	
	@GetMapping("/vendas/vendedor/{id}/{date1}_{date2}")
	@ApiOperation(value="Retorna uma lista de vendas de um vendedor específico conforme o período informado (formato de data: aaaa-mm-dd)")
	public List<Venda> listaVendaPorVendedorPeriodo(@PathVariable(value="id") long id, @PathVariable(value="date1") Date date1, @PathVariable(value="date2") Date date2){
		return vendaRepository.findByVendedor(id, date1, date2);
	}
	
	@PostMapping("/vendas")
	@ApiOperation(value="Salva uma venda")
	public Venda salvarVenda(@RequestBody Venda venda){
		return vendaRepository.save(venda);
	}
	
	@DeleteMapping("/vendas")
	@ApiOperation(value="Deleta uma venda")
	public void deletarVenda(@RequestBody Venda venda){
		vendaRepository.delete(venda);
	}
	
	@PutMapping("/vendas")
	@ApiOperation(value="Atualiza uma venda")
	public Venda atualizarVenda(@RequestBody Venda venda){
		return vendaRepository.save(venda);
	}
	
}
