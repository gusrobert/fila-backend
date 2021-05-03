package br.com.sp.fatec.springbootapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.sp.fatec.springbootapp.entity.Perfil;
import br.com.sp.fatec.springbootapp.service.PerfilService;

@RestController
@RequestMapping(value = "perfil")
@CrossOrigin
public class PerfilController {
	
	@Autowired
	private PerfilService perfilService;
	
	@JsonView(View.Perfil.class)
	@GetMapping(value = "/{id}")
	public Perfil buscarPorId(@PathVariable("id") Long id) {
		return perfilService.buscarPorId(id);
	}
	
	@PostMapping(value = "/cadastrar")
	public ResponseEntity<Perfil> inserir(@RequestBody Perfil perfil, UriComponentsBuilder uriComponentsBuilder) {
		Perfil perfilRef = perfilService.inserir(perfil);
		HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.setLocation(
	        uriComponentsBuilder.path(
	            "/perfil/" + perfilRef.getId()).build().toUri());
	    return new ResponseEntity<Perfil>(perfilRef, responseHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<Void> editar(@RequestBody Perfil perfil, @PathVariable("id") Long idPerfil) {
		perfilService.editar(perfil, idPerfil);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		perfilService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
