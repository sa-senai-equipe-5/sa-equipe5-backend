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

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.senai.saequipe5backend.entity.Usuario;
import br.com.senai.saequipe5backend.service.UsuarioService;

public class UsuarioController {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private MapConverter mapConverter;
	
	@Autowired
	private UsuarioService service;
	
	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Map<String, Object> usuarioMap) {
		Usuario novoUsuario = mapper.convertValue(usuarioMap, Usuario.class);
		Usuario usuarioSalvo = service.inserir(novoUsuario);
		return ResponseEntity.created(URI.create("/usuarioes/id/" + usuarioSalvo.getId())).build();
	}
	
	@PutMapping
	public ResponseEntity<?> alterar(@RequestBody Map<String, Object> usuarioMap) {
		Usuario usuarioSalvo = mapper.convertValue(usuarioMap, Usuario.class);
		Usuario usuarioAtualizado = service.alterar(usuarioSalvo);
		return ResponseEntity.ok(mapConverter.toJsonMap(usuarioAtualizado));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(@PathVariable(name = "id") Integer id) {
		return ResponseEntity.ok(mapConverter.toJsonMap(service.buscarPor(id)));
	}
	
	@GetMapping("/nome-completo/{nome-completo}")
	public ResponseEntity<?> listarPor(@PathVariable(name = "nome-completo") String nomeCompleto) {
		return ResponseEntity.ok(mapConverter.toJsonList(service.listarPor(nomeCompleto)));
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> excluirPor(@PathVariable(name = "id") Integer id) {
		this.service.excluirPor(id);
		return ResponseEntity.noContent().build();
	}
	
}
