package com.usoftware.marea.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ExecucaoControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	@DisplayName("Deve listar todas execuções e retornar status 200")
	public void shouldListAllExecucoes() throws Exception {
		
		mvc.perform(get("/api/execucao")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@DisplayName("Deve retornar uma execução pelo ID e com status 200")
	public void shouldFindExecucaoById() throws Exception {
		
		mvc.perform(get("/api/execucao/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string("{\"acao_id\":1,\"data_execucao\":\"2020-11-20 04:05\"}"));
	}
	
	@Test
	@DisplayName("Deve salvar uma execução, retornar status 201 e Location no Header")
	public void shouldSaveExecucao() throws Exception {
		
		mvc.perform(post("/api/execucao")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"acao_id\":1,\"data_execucao\":\"2020-11-20 04:05\"}"))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(header().exists("Location"));
	}
	
	@Test
	@DisplayName("Deve atualizar uma execução pelo id e retornar status 200")
	public void shouldUpdateExecucao() throws Exception {
		
		mvc.perform(put("/api/execucao/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"acao_id\":1,\"data_execucao\":\"2020-11-20 04:05\"}"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Deve deletar uma execução pelo id e retornar status 204")
	public void shouldDeleteExecucaoById() throws Exception {
		
		mvc.perform(delete("/api/execucao/5")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	@DisplayName("NÃ£o deve deletar uma execução associada a produto e retornar status 500")
	public void shouldNotDeleteExecucaoByIdWithConstraints() throws Exception {
		
		mvc.perform(delete("/api/execucao/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());
	}
	
}
