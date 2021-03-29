package br.com.sp.fatec.springbootapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import br.com.sp.fatec.springbootapp.entity.Usuario;
import br.com.sp.fatec.springbootapp.repository.UsuarioRepository;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testaInsercao() {
		Usuario usuario = new Usuario();
		usuario.setEmail("gustavo123@email.com");
		usuario.setSenha("pass123");
		usuarioRepo.save(usuario);
		assertNotNull(usuario.getId());
	}

}
