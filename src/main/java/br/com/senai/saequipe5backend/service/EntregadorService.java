package br.com.senai.saequipe5backend.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.saequipe5backend.entity.Entregador;
import br.com.senai.saequipe5backend.exception.RegistroNaoEncontradoException;
import br.com.senai.saequipe5backend.repository.EntregadoresRepository;

@Service
@Validated
public class EntregadorService {

	@Autowired
	private EntregadoresRepository repository;

	public Entregador inserir(@Valid @NotNull(message = "O entregador não pode ser nulo!") Entregador novoEntregador) {
		Preconditions.checkArgument(novoEntregador.getId() == null, "O id deve ser nulo");
		Entregador entregadorSalva = repository.save(novoEntregador);
		return entregadorSalva;
	}

	public Entregador alterar(@Valid @NotNull(message = "O entregador não pode ser nulo") Entregador entregadorSalvo) {
		Preconditions.checkArgument(entregadorSalvo.getId() != null, "O id é obrigatório");
		Entregador entregadorAtualizado = repository.save(entregadorSalvo);
		return entregadorAtualizado;
	}

	public Entregador buscarPor(@NotNull(message = "O id é obrigatório") Integer id) {
		Entregador entregadorEncontrado = repository.buscarPor(id);
		if (entregadorEncontrado == null) {
			throw new RegistroNaoEncontradoException("Nenhum entregador encontrado");
		}
		return entregadorEncontrado;
	}

	public List<Entregador> listarPor(@NotEmpty(message = "O nome não pode ser nulo") String nomeCompleto) {
		return repository.listarPor("%" + nomeCompleto + "%");
	}

	public void excluirPor(@NotNull(message = "O id é obrigatório") Integer id) {
		this.repository.deleteById(id);
	}

}
