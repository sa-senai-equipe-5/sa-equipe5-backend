package br.com.senai.saequipe5backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senai.saequipe5backend.entity.Entregador;

@Repository
public interface EntregadoresRepository extends JpaRepository<Entregador, Integer>{

}
