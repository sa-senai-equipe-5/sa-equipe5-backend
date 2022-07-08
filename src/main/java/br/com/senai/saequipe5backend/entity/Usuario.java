package br.com.senai.saequipe5backend.entity;

import br.com.senai.saequipe5backend.enums.Perfil;
import lombok.Data;

@Data
public class Usuario {

	private Integer id;
	private String nomeCompleto;
	private String login;
	private String senha;
	private Perfil perfil;
	
}
