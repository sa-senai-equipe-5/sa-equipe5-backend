package br.com.senai.saequipe5backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senai.saequipe5backend.entity.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

	@Query(value = "SELECT u FROM Usuario e WHERE Upper(u.nomeCompleto) LIKE Upper(:nome)")
	public List<Usuario> listarPor(@Param("nome") String nomeCompleto);
	
	@Query(value = "SELECT u FROM Usuario e WHERE u.id = :id")
	public Usuario buscarPor(@Param("id") Integer id);
	
}
