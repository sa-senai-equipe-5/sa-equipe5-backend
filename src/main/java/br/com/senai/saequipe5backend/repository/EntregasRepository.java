package br.com.senai.saequipe5backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senai.saequipe5backend.entity.Entrega;

@Repository
public interface EntregasRepository extends JpaRepository<Entrega, Integer> {

	@Query(value = "SELECT e FROM Entrega e WHERE Upper(e.descricao) LIKE Upper(:desc)")
	public List<Entrega> listarPor(@Param("desc") String descricao);
	
	@Query(value = "SELECT e FROM Entrega e WHERE e.id = :id")
	public Entrega buscarPor(@Param("id") Integer id);
	
}
