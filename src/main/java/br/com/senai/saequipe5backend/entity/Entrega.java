package br.com.senai.saequipe5backend.entity;

import java.time.LocalDate;

import br.com.senai.saequipe5backend.enums.Entregue;
import lombok.Data;

@Data
public class Entrega {

	private Integer id;
	private String enderecoCompleto;
	private LocalDate dataDeEntrega;
	private Entregue entregue;
	private String descricao;
	private Entregador entregador;
	
}
