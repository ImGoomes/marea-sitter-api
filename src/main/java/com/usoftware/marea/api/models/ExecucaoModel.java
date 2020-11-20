package com.usoftware.marea.api.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "TB_EXECUCAO")
public class ExecucaoModel {
	private long execucao_id;
	public AcaoModel acao;
	public Date data_execucao;
	
	public ExecucaoModel() {
	}
	
	public ExecucaoModel(long execucao_id, AcaoModel acao, Date data_execucao) {
		super();
		this.execucao_id = execucao_id;
		this.acao = acao;
		this.data_execucao = data_execucao;
	}

	@Id
	@Column(name = "EXECUCAO_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXECUCAO_SEQ")
	@SequenceGenerator(name = "EXECUCAO_SEQ", sequenceName = "EXECUCAO_SEQ", allocationSize = 1)
	public long getExecucao_id() {
		return execucao_id;
	}
	public void setExecucao_id(long execucao_id) {
		this.execucao_id = execucao_id;
	}
	
	@ManyToOne()
	@JoinColumn(name = "ACAO_ID", nullable = false)
	public AcaoModel getAcao() {
		return acao;
	}
	public void setAcao(AcaoModel acao) {
		this.acao = acao;
	}
	
	@Column(name = "DATA_EXECUCAO")
	@NotNull(message = "Data obrigatória")
	public Date getData_execucao() {
		return data_execucao;
	}
	public void setData_execucao(Date data_execucao) {
		this.data_execucao = data_execucao;
	}
}
