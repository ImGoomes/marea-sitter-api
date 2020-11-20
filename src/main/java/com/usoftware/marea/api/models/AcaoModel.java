package com.usoftware.marea.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_ACAO")
public class AcaoModel {
	
	private long acao_id;
	private String nome;
	private String descricao;
	private Boolean ativo;
	
	public AcaoModel() {
	}
	
	public AcaoModel(long acao_id, String nome, String descricao, Boolean ativo) {
		super();
		this.acao_id = acao_id;
		this.nome = nome;
		this.descricao = descricao;
		this.ativo = ativo;
	}
	
	@Id
	@Column(name = "ACAO_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACAO_SEQ")
	@SequenceGenerator(name = "ACAO_SEQ", sequenceName = "ACAO_SEQ", allocationSize = 1)
	public long getAcao_id() {
		return acao_id;
	}
	public void setAcao_id(long acao_id) {
		this.acao_id = acao_id;
	}
	
	@Column(name = "NOME")
	@NotNull(message = "Nome obrigatório")
	@Size(min = 2, max = 40, message = "NOME deve ser entre 2 e 40 caracteres")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "DESCRICAO")
	@NotNull(message = "Descricao obrigatório")
	@Size(min = 2, max = 100, message = "Descricao deve ser entre 2 e 100 caracteres")
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Column(name = "ATIVO")
	@NotNull(message = "Ativo obrigatório")
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	

}
