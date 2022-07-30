package br.com.senai.saequipe5backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senai.saequipe5backend.entity.Usuario;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.login = :login")
	public Usuario buscarPor(@Param("login") String login);
	
}
