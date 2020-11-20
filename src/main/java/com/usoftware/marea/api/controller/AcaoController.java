package com.usoftware.marea.api.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import com.usoftware.marea.api.repository.AcaoRepository;

@RestController
@RequestMapping("/api/acao")
public class AcaoController {
	
	@Autowired
	private AcaoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<AcaoModel>> findAll() {
		return  ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AcaoModel> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(repository.findById(id).get());
	}
	
	@PostMapping
	public ResponseEntity save(@RequestBody @Valid AcaoModel acaoModel) {
		
		AcaoModel acao = repository.save(acaoModel);	
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(acao.getAcao_id()).toUri();
		
		return ResponseEntity.created(location).build();		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") int id,
			@Valid @RequestBody AcaoModel acaoModel) {
		
		acaoModel.setAcao_id(id);
		repository.save(acaoModel);
		
		return ResponseEntity.ok().build();		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		
		repository.deleteById(id);	
		return ResponseEntity.noContent().build();
	}


}
