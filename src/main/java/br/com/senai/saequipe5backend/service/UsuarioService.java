package br.com.senai.saequipe5backend.service;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.senai.saequipe5backend.entity.Usuario;
import br.com.senai.saequipe5backend.exception.RegistroNaoEncontradoException;
import br.com.senai.saequipe5backend.repository.UsuariosRepository;

@Service
@Validated
public class UsuarioService {

	@Autowired
	private UsuariosRepository repository;
	
	public Usuario buscarPor(@NotNull(message = "O login é obrigatório") String login) {
		Usuario usuarioEncontrado = repository.buscarPor(login);
		if (usuarioEncontrado == null) {
			throw new RegistroNaoEncontradoException("Login ou senha inválidos");
		}
		return usuarioEncontrado;
	}
	
}
