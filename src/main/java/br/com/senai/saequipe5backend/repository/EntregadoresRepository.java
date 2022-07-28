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

	@Query(value = "SELECT e FROM Entregador e WHERE Upper(e.nomeCompleto) LIKE Upper(:nome)")
	public List<Entregador> listarPor(@Param("nome") String nomeCompleto);
	
	@Query(value = "SELECT e FROM Entregador e WHERE e.id = :id")
	public Entregador buscarPor(@Param("id") Integer id);
	
	public Entregador findByUsuario(final Usuario usuario);
	
	public List<Entregador> findAllByOrderByIdAsc();
	
}
