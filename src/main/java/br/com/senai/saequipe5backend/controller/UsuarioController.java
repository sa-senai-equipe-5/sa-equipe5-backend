package br.com.senai.saequipe5backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.saequipe5backend.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private MapConverter mapConverter;
	
	@Autowired
	private UsuarioService service;
	
	@GetMapping("/login/{login}")
	public ResponseEntity<?> logar(@PathVariable(name = "login") String login) {
		return ResponseEntity.ok(mapConverter.toJsonMap(service.buscarPor(login)));
	}
	
}
