package com.usoftware.marea.api.business;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.usoftware.marea.api.exception.ResponseBusinessException;
import com.usoftware.marea.api.models.AcaoModel;
import com.usoftware.marea.api.models.ExecucaoModel;
import com.usoftware.marea.api.repository.AcaoRepository;
import com.usoftware.marea.api.repository.ExecucaoRepository;

@Component
public class ExecucaoBusiness {

	@Value("${rest.exception.business.not-contains-acao}")
	private String notContainsAcao;
	
	@Autowired
	public AcaoRepository acaoRepository;
	
	public ExecucaoModel applyBusiness(ExecucaoModel execucao) throws ResponseBusinessException {
		
		verifyAcaoExistente(execucao.getAcao().getAcao_id());
		
		return execucao;
	}
	
	protected long verifyAcaoExistente(long id) throws ResponseBusinessException {
		
		AcaoModel acao = acaoRepository.findById(id).get();
		
		if (acao == null || !acao.getAtivo()) {
			throw new ResponseBusinessException(notContainsAcao);
		}
		
		return acao.getAcao_id();
	}
}
