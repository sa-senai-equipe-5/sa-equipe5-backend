package br.com.senai.saequipe5backend.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.senai.saequipe5backend.entity.Entrega;
import br.com.senai.saequipe5backend.service.EntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private MapConverter mapConverter;
	
	@Autowired
	private EntregaService service;
	
	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Map<String, Object> entregaMap) {
		Entrega novaEntrega = mapper.convertValue(entregaMap, Entrega.class);
		Entrega entregaSalva = service.inserir(novaEntrega);
		return ResponseEntity.created(URI.create("/entregas/id/" + entregaSalva.getId())).build();
	}
	
	@PutMapping
	public ResponseEntity<?> alterar(@RequestBody Map<String, Object> entregaMap) {
		Entrega entregaSalva = mapper.convertValue(entregaMap, Entrega.class);
		Entrega entregaAtualizada = service.alterar(entregaSalva);
		return ResponseEntity.ok(mapConverter.toJsonMap(entregaAtualizada));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok(mapConverter.toJsonMap(service.buscarPor(id)));
	}
	
	@GetMapping("/endereco/{endereco}")
	public ResponseEntity<?> listarPor(@PathVariable(name = "endereco") String endereco) {
		return ResponseEntity.ok(mapConverter.toJsonList(service.listarPor(endereco)));
	}
	
	@GetMapping("/last")
	public ResponseEntity<?> buscarMaisRecente() {
		return ResponseEntity.ok(mapConverter.toJsonMap(service.buscarMaisRecente()));
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> excluirPor(@PathVariable(name = "id") Integer id) {
		this.service.excluirPor(id);
		return ResponseEntity.noContent().build();
	}
	
}
