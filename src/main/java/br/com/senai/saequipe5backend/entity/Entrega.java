package br.com.senai.saequipe5backend.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	@Min(value = 2, message = "O endereço deve possuir no mínimo 2 caracteres")
	@Max(value = 500, message = "O endereço deve possuir no máximo 500 caracteres")
	private String enderecoCompleto;
	@NotNull(message = "A data de entrega é obrigatória!")
	@Column(name = "dt_entrega")
	private LocalDate dataDeEntrega;
	@NotNull(message = "O campo Entregue é obrigatório")
	@Column(name = "entregue")
	private Entregue entregue;
	@NotEmpty(message = "A descrição é obrigatória!")
	@Column(name = "descricao")
	@Min(value = 10, message = "A descrição deve possuir no mínimo 10 caracteres")
	@Max(value = 1500, message = "A descrição deve possuir no máximo 1500 caracteres")
	private String descricao;
	@NotNull(message = "O entregador é obrigatório")
	@Column(name = "id_entregador")
	private Entregador entregador;
	
}
