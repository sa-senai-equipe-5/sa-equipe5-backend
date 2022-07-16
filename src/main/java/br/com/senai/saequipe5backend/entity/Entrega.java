package br.com.senai.saequipe5backend.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.senai.saequipe5backend.enums.Entregue;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "Entrega")
@Table(name = "entregas")
public class Entrega {

	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "O endereço é obrigatório!")
	@Column(name = "endereco")
	@Size(min = 2, max = 500, message = "O endereço deve possuir entre 2 e 500 caracteres")
	private String enderecoCompleto;
	
	@NotNull(message = "A data de entrega é obrigatória!")
	@Column(name = "dt_entrega")
	private LocalDate dataDeEntrega;
	
	@NotNull(message = "O campo Entregue é obrigatório")
	@Column(name = "entregue")
	private Entregue entregue;
	
	@NotEmpty(message = "A descrição é obrigatória!")
	@Column(name = "descricao")
	@Size(min = 10, max = 1500, message = "A descrição deve possuir entre 10 e 1500 caracteres")
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "O entregador é obrigatório")
	@JoinColumn(name = "id_entregador")
	private Entregador entregador;
	
}
