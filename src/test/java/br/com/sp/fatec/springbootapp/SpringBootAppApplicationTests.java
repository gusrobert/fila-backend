package br.com.sp.fatec.springbootapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
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
	
	@BeforeAll
	static void init(@Autowired JdbcTemplate jdbcTemplate) {
		jdbcTemplate.update(
				"insert into credencial (login, senha, sn_bloqueado, sn_excluido, sn_online) values ('admin', 'pass123', false, false, false)");
		jdbcTemplate.update(
				"insert into usuario (email, credencial) values (?, ?)",
				"gustavo@email.com", 1L);
		jdbcTemplate.update(
				"insert into perfil (nome) values ('admin')");
		jdbcTemplate.update(
				"insert into credencial_perfil values (1L, 1L)");
	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testaCredencial() {
		Credencial credencial = credencialRepo.findById(1L).get();
		assertEquals("admin", credencial.getLogin());
	}
	
	@Test
	void testaUsuario() {
		Credencial credencial = credencialRepo.findById(1L).get();
		Usuario u = usuarioRepo.findByCredencial(credencial);
		assertEquals("gustavo@email.com", u.getEmail());
	}
	
	@Test
	void testaServiceCriarUsuario() throws Exception {
		Usuario usuario = usuarioService.criarUsuario("gustavorobert@email.com", "gustavo.robert", "pass123", "admin");

		assertNotNull(usuario);
	}
	
	@Test
	void testaLoginSenha() {
		Credencial credencial = credencialRepo.buscaCredencialPorLoginSenha("admin", "pass123");
		assertNotNull(credencial.getId());
	}
	
	@Test
	void testaBuscaCredencial() {
		List<Credencial> credenciais = credencialRepo.findByListaPerfilNomeContains("admin");
		assertFalse(credenciais.isEmpty());  
	}	
	
//	
//	@Test
//	void testaBuscarLoginPerfil() {
//		List<Usuario> listaUsuario = usuarioRepo.buscarUsuarioPorNomePerfil("admin");
//		assertEquals(listaUsuario.size(), 1);
//	}

}
