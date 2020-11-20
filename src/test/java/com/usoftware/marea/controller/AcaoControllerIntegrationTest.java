package com.usoftware.marea.controller;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AcaoControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	@DisplayName("Deve listar todas ações e retornar status 200")
	public void shouldListAllAcoes() throws Exception {
		
		mvc.perform(get("/api/acao")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@DisplayName("Deve retornar uma ação pelo ID e com status 200")
	public void shouldFindAcaoById() throws Exception {
		
		mvc.perform(get("/api/acao/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string("{\"acao_id\":1,\"nome\":\"Bebe beber\",\"descricao\":\"Ação chamada no momento em que o bebe precisa beber\",\"ativo\":true\"}"));
	}
	
	@Test
	@DisplayName("Deve salvar uma ação, retornar status 201 e Location no Header")
	public void shouldSaveAcao() throws Exception {
		
		mvc.perform(post("/api/acao")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"nome\":\"Bebe dormir\",\"descricao\":\"Ação chamada no momento em que o bebe precisa dormir\",\"ativo\":false\"}"))
			.andDo(print())
			.andExpect(status().isCreated())
			.andExpect(header().exists("Location"));
	}
	
	@Test
	@DisplayName("Deve atualizar uma ação pelo id e retornar status 200")
	public void shouldUpdateAcao() throws Exception {
		
		mvc.perform(put("/api/acao/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Bebe dormir\",\"descricao\":\"Ação chamada no momento em que o bebe precisa beber\",\"ativo\":true\"}"))
				.andDo(print())
				.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("Deve deletar uma ação pelo id e retornar status 204")
	public void shouldDeleteAcaoById() throws Exception {
		
		mvc.perform(delete("/api/acao/5")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	@DisplayName("NÃ£o deve deletar uma ação associada a produto e retornar status 500")
	public void shouldNotDeleteAcaoByIdWithConstraints() throws Exception {
		
		mvc.perform(delete("/api/acao/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError());
	}
	
}
