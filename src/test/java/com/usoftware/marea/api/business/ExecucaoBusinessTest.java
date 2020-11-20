package com.usoftware.marea.api.business;

import static org.junit.Assert.assertEquals;

import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.usoftware.marea.api.exception.ResponseBusinessException;
import com.usoftware.marea.api.models.AcaoModel;
import com.usoftware.marea.api.repository.AcaoRepository;


@SpringBootTest
public class ExecucaoBusinessTest {
	
	@InjectMocks
	public ExecucaoBusiness execucaoBusiness;
	
	@Mock
	public AcaoRepository acaoRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddValueToAcaoWithIdExistente()  throws ResponseBusinessException {
		// GIVEN
		AcaoModel acao = new AcaoModel(1, "Beber sonhar", "Ação chamada no momento em que o bebe precisa senhar", true);	
		long expected = 1;
		// WHEN
		Mockito.when(acaoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(acao));
		long actual = execucaoBusiness.verifyAcaoExistente(acao.getAcao_id());
		
		// THEN
		assertEquals(expected, actual);
	}
}
