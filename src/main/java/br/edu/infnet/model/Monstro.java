package br.edu.infnet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Entity
@JsonInclude(Include.NON_NULL)
public class Monstro {
	
	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private Integer pontosDeVida;
	private Integer forca;
	private Integer defesa;
	private Integer agilidade;
	private Integer qtdDadosDano;
	private Integer tamanhoDadosDano;

}
