package br.com.sp.fatec.springbootapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sp.fatec.springbootapp.entity.Usuario;
import br.com.sp.fatec.springbootapp.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping(value = "/{id}")
	public Usuario buscarPorId(@PathVariable("id") Long id) {
		return usuarioService.buscarPorId(id);
	}
	
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
