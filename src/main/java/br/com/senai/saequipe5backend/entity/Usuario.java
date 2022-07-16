package br.com.senai.saequipe5backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@Size(min = 2, max = 50, message = "O nome deve possuir entre 2 e 50 caracteres")
	private String nomeCompleto;
	
	@Column(name = "login")
	@NotEmpty(message = "O login não pode ser nulo!")
	@Size(min = 2, max = 20, message = "O login deve possuir entre 2 e 20 caracteres")
	private String login;
	
	@Column(name = "senha")
	@NotEmpty(message = "A senha não pode ser nula!")
	@NotBlank(message = "A senha não pode conter espaços!")
	@Pattern(regexp = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]+)$", message = "A senha deve conter letras e números")
	@Size(min = 2, max = 10, message = "A senha deve possuir entre 2 e 10 caracteres")
	private String senha;
	
	@NotNull(message = "O perfil não pode ser nulo!")
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil")
	private Perfil perfil;

}
