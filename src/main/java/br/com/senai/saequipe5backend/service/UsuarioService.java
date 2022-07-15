package br.com.senai.saequipe5backend.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.saequipe5backend.entity.Usuario;
import br.com.senai.saequipe5backend.exception.RegistroNaoEncontradoException;
import br.com.senai.saequipe5backend.repository.UsuariosRepository;

@Service
@Validated
public class UsuarioService {

	@Autowired
	private UsuariosRepository repository;
	
	public Usuario inserir(@Valid @NotNull(message = "O usuario não pode ser nulo!") Usuario novoUsuario) {
		Preconditions.checkArgument(novoUsuario.getId() == null, "O id deve ser nulo");
		Usuario usuarioSalva = repository.save(novoUsuario);
		return usuarioSalva;
	}

	public Usuario alterar(@Valid @NotNull(message = "O usuario não pode ser nulo") Usuario usuarioSalvo) {
		Preconditions.checkArgument(usuarioSalvo.getId() != null, "O id é obrigatório");
		Usuario usuarioAtualizado = repository.save(usuarioSalvo);
		return usuarioAtualizado;
	}

	public Usuario buscarPor(@NotNull(message = "O id é obrigatório") Integer id) {
		Usuario usuarioEncontrado = repository.buscarPor(id);
		if (usuarioEncontrado == null) {
			throw new RegistroNaoEncontradoException("Nenhum usuario encontrado");
		}
		return usuarioEncontrado;
	}

	public List<Usuario> listarPor(@NotEmpty(message = "O nome não pode ser nulo") String nomeCompleto) {
		return repository.listarPor("%" + nomeCompleto + "%");
	}

	public void excluirPor(@NotNull(message = "O id é obrigatório") Integer id) {
		this.repository.deleteById(id);
	}
	
}
