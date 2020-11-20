package com.usoftware.marea.api.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.usoftware.marea.api.models.AcaoModel;
import com.usoftware.marea.api.models.ExecucaoModel;
import com.usoftware.marea.api.repository.AcaoRepository;
import com.usoftware.marea.api.repository.ExecucaoRepository;

@RestController
@RequestMapping("/api/execucao")
public class ExecucaoController {

	@Autowired
	private ExecucaoRepository execucaoRepository;
	@Autowired
	private AcaoRepository acaoRepository;

	@GetMapping
	public ResponseEntity<List<ExecucaoModel>> findAll(Model model) {
		return ResponseEntity.ok(execucaoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ExecucaoModel> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(execucaoRepository.findById(id).get());
	}

	@PostMapping
	public ResponseEntity save(@RequestBody ExecucaoModel execucaoModel) {

		// Busca a ação
		AcaoModel acao = acaoRepository.findById(execucaoModel.getAcao().getAcao_id()).get();

		// Verifica se a ação está ativa
		if (acao == null || !acao.getAtivo()) {
			return new ResponseEntity<>("Ação enviada não existe ou está inativa", HttpStatus.BAD_REQUEST);
		}

		// Salva a execução
		execucaoModel.setData_execucao(new Date());
		execucaoModel.setAcao(acao);
		ExecucaoModel execucao = execucaoRepository.save(execucaoModel);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(execucao.getExecucao_id()).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") int id, @RequestBody ExecucaoModel execucaoModel) {

		// Busca a ação
		AcaoModel acao = acaoRepository.findById(execucaoModel.getAcao().getAcao_id()).get();

		// Verifica se a ação está ativa
		if (acao == null || !acao.getAtivo()) {
			return new ResponseEntity<>("Ação enviada não existe ou está inativa", HttpStatus.BAD_REQUEST);
		}

		execucaoModel.setExecucao_id(id);
		execucaoRepository.save(execucaoModel);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		execucaoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
