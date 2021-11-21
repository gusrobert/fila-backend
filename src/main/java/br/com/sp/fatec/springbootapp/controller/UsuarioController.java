package br.com.sp.fatec.springbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sp.fatec.springbootapp.DTO.UsuarioDTO;
import br.com.sp.fatec.springbootapp.entity.Usuario;
import br.com.sp.fatec.springbootapp.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping
	public List<Usuario> buscarTodos() {
		return usuarioService.buscarTodosUsuarios();
	}
	
	@GetMapping(value = "/{id}")
	public Usuario buscarPorId(@PathVariable("id") Long id) {
		return usuarioService.buscarPorId(id);
	}

	@PostMapping(value = "/cadastrar")
	public ResponseEntity<Usuario> cadastrarNovoUsuario(@RequestBody UsuarioDTO usuarioDTO,
			UriComponentsBuilder uriComponentsBuilder) throws Exception {
		Usuario usuario = usuarioService.criarUsuario(usuarioDTO.getEmail(), usuarioDTO.getLogin(), usuarioDTO.getSenha(), usuarioDTO.getPerfil());

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(
			uriComponentsBuilder.path(
				"/usuario/" + usuario.getId()).build().toUri());
		return new ResponseEntity<Usuario>(usuario, responseHeaders, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/deletar/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		usuarioService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
