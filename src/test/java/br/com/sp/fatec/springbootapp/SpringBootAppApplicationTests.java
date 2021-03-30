package br.com.sp.fatec.springbootapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.fatec.springbootapp.entity.Credencial;
import br.com.sp.fatec.springbootapp.entity.Usuario;
import br.com.sp.fatec.springbootapp.repository.CredencialRepository;
import br.com.sp.fatec.springbootapp.repository.UsuarioRepository;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private CredencialRepository credencialRepo;
	
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testaInsercao() {
		Credencial credencial = credencialRepo.findById(1L).get();
		assertNotNull(credencial.getId());
	}
	
	@Test
	void testaUsuario() {
		Credencial credencial = credencialRepo.findById(1L).get();
		Usuario u = usuarioRepo.findByCredencial(credencial);
		assertEquals("gustavo@email.com", u.getEmail());
	}

	
	@Test
	void testaCredencial() {
		List<Credencial> credenciais = credencialRepo.findByListaPerfilNomeContains("admin");
		assertFalse(credenciais.isEmpty());  
	}

}
