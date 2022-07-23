package br.com.senai.saequipe5backend.controller;

import java.net.URI;
import java.util.List;
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

import br.com.senai.saequipe5backend.entity.Entregador;
import br.com.senai.saequipe5backend.entity.Usuario;
import br.com.senai.saequipe5backend.service.EntregadorService;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private MapConverter mapConverter;
	
	@Autowired
	private EntregadorService service;
	
	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Map<String, Object> entregadorMap) {
		Entregador novoEntregador = mapper.convertValue(entregadorMap, Entregador.class);
		Entregador entregadorSalvo = service.inserir(novoEntregador);
		return ResponseEntity.created(URI.create("/entregadores/id/" + entregadorSalvo.getId())).build();
	}
	
	@PutMapping
	public ResponseEntity<?> alterar(@RequestBody Map<String, Object> entregadorMap) {
		Entregador entregadorSalvo = mapper.convertValue(entregadorMap, Entregador.class);
		Entregador entregadorAtualizado = service.alterar(entregadorSalvo);
		return ResponseEntity.ok(mapConverter.toJsonMap(entregadorAtualizado));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok(mapConverter.toJsonMap(service.buscarPor(id)));
	}
	
	@GetMapping("/usuario/{usuario}")
	public ResponseEntity<?> buscarPor(@PathVariable(name = "usuario") Usuario usuario) {
		return ResponseEntity.ok(mapConverter.toJsonMap(service.buscarPor(usuario)));
	}
	
	@GetMapping("/nome-completo/{nome-completo}")
	public ResponseEntity<?> listarPor(@PathVariable(name = "nome-completo") String nomeCompleto) {
		System.out.println(nomeCompleto);
		List<Entregador> lista = service.listarPor(nomeCompleto);
		System.out.println(lista.size());
		return ResponseEntity.ok(mapConverter.toJsonList(lista));
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> excluirPor(@PathVariable(name = "id") Integer id) {
		this.service.excluirPor(id);
		return ResponseEntity.noContent().build();
	}
	
}
