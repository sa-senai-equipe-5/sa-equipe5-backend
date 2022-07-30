package br.com.senai.saequipe5backend.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Entregador")
@Table(name = "entregadores")
public class Entregador{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nm_completo")
	@NotEmpty(message = "O nome é obrigatório!")
	@Size(min = 2, max = 50, message = "O nome deve possuir entre 2 e 50 caracteres")
	private String nomeCompleto;
	
	@NotEmpty(message = "O cpf é obrigatório!")
	@Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)", message = "O CPF deve possuir o formato NNN.NNN.NNN-NN")
	@Column(name = "cpf")
	@Size(min = 14, max = 14, message = "O CPF deve possuir 14 caracteres")
	private String cpf;
	
	@NotNull(message = "a data de nascimento é obrigatória")
	@Column(name = "dt_nascimento")
	@Past(message = "A data de nascimento deve ser anterior à atual.")
	private LocalDate dataDeNascimento;
	
	@NotEmpty(message = "O RG é obrigatório")
	@Column(name = "rg")
	@Size(min = 10, max = 10, message = "O RG deve possuir 10 caracteres")
	@Pattern(regexp = "([0-9]{2}[\\.][0-9]{3}[\\.][0-9]{3})", message = "O RG deve possuir o formato NN.NNN.NNN")
	private String rg;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_usuario")
	@NotNull(message = "O usuário é obrigatório")
	private Usuario usuario;
	
}
