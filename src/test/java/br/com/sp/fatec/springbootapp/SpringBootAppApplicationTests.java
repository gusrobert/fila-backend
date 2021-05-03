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
import br.com.sp.fatec.springbootapp.service.UsuarioService;

@SpringBootTest
@Transactional
@Rollback
class SpringBootAppApplicationTests {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private CredencialRepository credencialRepo;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
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
	
	@Test
	void testaLoginSenha() {
		Credencial credencial = credencialRepo.buscaCredencialPorLoginSenha("gustavo@email.com", "pass123");
		assertNotNull(credencial.getId());
	}
	
	@Test
	void testaServiceCriarUsuario() throws Exception {
		Usuario usuario = usuarioService.criarUsuario("Gustavo", "gustavo123@email.com", "gustavo123", "pass123", "admin");
		assertNotNull(usuario.getId());
		
	}
	
	@Test
	void testaBuscarLoginPerfil() {
		List<Usuario> listaUsuario = usuarioRepo.buscarUsuarioPorNomePerfil("admin");
		assertEquals(listaUsuario.size(), 2);
	}

}
