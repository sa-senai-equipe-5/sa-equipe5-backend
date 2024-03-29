package br.com.senai.saequipe5backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senai.saequipe5backend.entity.Entregador;
import br.com.senai.saequipe5backend.entity.Usuario;

@Repository
public interface EntregadoresRepository extends JpaRepository<Entregador, Integer>{

	@Query(value = "SELECT e FROM Entregador e JOIN FETCH e.usuario WHERE Upper(e.nomeCompleto) LIKE Upper(:nome) ORDER BY e.id ASC")
	public List<Entregador> listarPor(@Param("nome") String nomeCompleto);
	
	@Query(value = "SELECT e FROM Entregador e JOIN FETCH e.usuario WHERE e.id = :id")
	public Entregador buscarPor(@Param("id") Integer id);
	
	@Query(value = "SELECT e FROM Entregador e JOIN FETCH e.usuario WHERE e.usuario = :usuario")
	public Entregador buscarPor(@Param("usuario") Usuario usuario);
	
	@Query(value = "SELECT e FROM Entregador e JOIN FETCH e.usuario WHERE e.cpf = :cpf")
	public Entregador buscarPorCpf(@Param("cpf") String cpf);
	
	@Query(value = "SELECT e FROM Entregador e JOIN FETCH e.usuario WHERE e.rg = :rg")
	public Entregador buscarPorRg(@Param("rg") String rg);
	
	@Query(value = "SELECT e FROM Entregador e JOIN FETCH e.usuario ORDER BY e.id ASC")
	public List<Entregador> buscarTodos();
	
}
