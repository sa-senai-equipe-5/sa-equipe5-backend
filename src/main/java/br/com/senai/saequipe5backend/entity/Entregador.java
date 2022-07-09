package br.com.senai.saequipe5backend.entity;

import java.time.LocalDate;

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
import javax.validation.constraints.Pattern;

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
	private String nomeCompleto;
	@NotEmpty(message = "O cpf é obrigatório!")
	@Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)", message = "O cpf é inválido")
	@Column(name = "cpf")
	private String cpf;
	@NotEmpty(message = "a data de nascimento é obrigatória")
	@Column(name = "dt_nascimento")
	private LocalDate dataDeNascimento;
	@NotEmpty(message = "O RG é obrigatório")
	@Column(name = "rg")
	private String rg;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario")
	@NotNull(message = "O usuário é obrigatório")
	private Usuario usuario;
	
}
