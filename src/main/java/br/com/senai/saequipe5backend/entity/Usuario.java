package br.com.senai.saequipe5backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.senai.saequipe5backend.enums.Perfil;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id")
	private Integer id;
	@Column(name = "nm_completo")
	@NotEmpty(message = "O nome é obrigatório!")
	private String nomeCompleto;
	@Column(name = "login")
	@NotEmpty(message = "O login não pode ser nulo!")
	private String login;
	@Column(name = "senha")
	// testar vazio
	@NotEmpty(message = "A senha não pode ser nula!")
	@NotBlank(message = "A senha não pode conter espaços!")
	@Pattern(regexp = "^[A-Za-z0-9_-]*$", message = "A senha deve conter letras E números")
	private String senha;
	@NotNull(message = "O perfil não pode ser nulo!")
	@Column(name = "perfil")
	private Perfil perfil;

}
