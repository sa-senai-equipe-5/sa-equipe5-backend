package br.com.senai.saequipe5backend.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.saequipe5backend.entity.Entrega;
import br.com.senai.saequipe5backend.exception.RegistroNaoEncontradoException;
import br.com.senai.saequipe5backend.repository.EntregasRepository;

@Service
@Validated
public class EntregaService {

	@Autowired
	private EntregasRepository repository;
	
	public Entrega inserir(@Valid @NotNull(message = "O entrega não pode ser nulo!") Entrega novaEntrega) {
		Preconditions.checkArgument(novaEntrega.getId() == null, "O id deve ser nulo");
		Entrega entregaSalva = repository.save(novaEntrega);
		return entregaSalva;
	}

	public Entrega alterar(@Valid @NotNull(message = "O entrega não pode ser nulo") Entrega entregaSalva) {
		Preconditions.checkArgument(entregaSalva.getId() != null, "O id é obrigatório");
		Entrega entregaAtualizada = repository.save(entregaSalva);
		return entregaAtualizada;
	}

	public Entrega buscarPor(@NotNull(message = "O id é obrigatório") Integer id) {
		Entrega entregaEncontrada = repository.buscarPor(id);
		if (entregaEncontrada == null) {
			throw new RegistroNaoEncontradoException("Nenhuma entrega encontrada");
		}
		return entregaEncontrada;
	}

	public List<Entrega> listarPor(@NotEmpty(message = "A descrição não pode ser nula") String endereco) {
		return repository.listarPor("%" + endereco + "%");
	}

	public void excluirPor(@NotNull(message = "O id é obrigatório") Integer id) {
		this.repository.deleteById(id);
	}
	
}
