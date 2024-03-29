package br.com.senai.saequipe5backend.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.saequipe5backend.entity.Entregador;
import br.com.senai.saequipe5backend.entity.Usuario;
import br.com.senai.saequipe5backend.exception.RegistroNaoEncontradoException;
import br.com.senai.saequipe5backend.repository.EntregadoresRepository;

@Service
@Validated
public class EntregadorService {

	@Autowired
	private EntregadoresRepository repository;
	
	public Entregador inserir(@Valid @NotNull(message = "O entregador não pode ser nulo!") Entregador novoEntregador) {
		Preconditions.checkArgument(novoEntregador.getId() == null, "O id deve ser nulo");
		this.isValido(novoEntregador);
		Entregador entregadorSalvo = repository.save(novoEntregador);
		return entregadorSalvo;
	}

	public Entregador alterar(@Valid @NotNull(message = "O entregador não pode ser nulo") Entregador entregadorSalvo) {
		Preconditions.checkArgument(entregadorSalvo.getId() != null, "O id é obrigatório");
		this.isValido(entregadorSalvo);
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
	
	public Entregador buscarPor(@NotNull(message = "O usuário é obrigatório") Usuario usuario) {
		Entregador entregadorEncontrado = repository.buscarPor(usuario);
		if (entregadorEncontrado == null) {
			throw new RegistroNaoEncontradoException("Nenhum entregador encontrado");
		}
		return entregadorEncontrado;
	}
	
	public Entregador buscarPor(@NotNull(message = "O CPF é obrigatório") String cpf) {
		Entregador entregador = this.repository.buscarPorCpf(cpf);
		return entregador;
	}

	public List<Entregador> listarPor(@NotEmpty(message = "O nome não pode ser nulo") String nomeCompleto) {
		return repository.listarPor("%" + nomeCompleto + "%");
	}

	public List<Entregador> listarTodos() {
		return repository.buscarTodos();
	}
	
	public void excluirPor(@NotNull(message = "O id é obrigatório") Integer id) {
		this.repository.deleteById(id);
	}
	
	private void isUnico(Entregador entregador) {
		Entregador entregadorEncontrado = this.repository.buscarPorCpf(entregador.getCpf());
		if (entregadorEncontrado != null) {
		Preconditions.checkArgument(entregadorEncontrado.getId() == entregador.getId(), "O CPF deve ser único");
		}
		entregadorEncontrado = this.repository.buscarPorRg(entregador.getRg());
		if (entregadorEncontrado != null) {
		Preconditions.checkArgument(entregadorEncontrado.getId() == entregador.getId(), "O RG deve ser único");
		}
	}
	
	private void isMaiorDeIdade(Entregador entregador) {
		LocalDate data = entregador.getDataDeNascimento();
		LocalDate dataAtual = LocalDate.now();
		Integer ano = data.getYear();
		Preconditions.checkArgument(ano < dataAtual.getYear() - 18 || (ano == dataAtual.getYear() - 18 && data.getDayOfYear() <= dataAtual.getDayOfYear()), "O entregador deve ter mais de 18 anos");		
	}
	
	private void isValido(Entregador entregador) {
		this.isUnico(entregador);
		this.isMaiorDeIdade(entregador);
	}

}
