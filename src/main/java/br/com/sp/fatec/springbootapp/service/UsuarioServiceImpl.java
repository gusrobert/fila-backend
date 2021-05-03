package br.com.sp.fatec.springbootapp.service;

import java.util.HashSet;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.sp.fatec.springbootapp.entity.Credencial;
import br.com.sp.fatec.springbootapp.entity.Perfil;
import br.com.sp.fatec.springbootapp.entity.Usuario;
import br.com.sp.fatec.springbootapp.repository.CredencialRepository;
import br.com.sp.fatec.springbootapp.repository.PerfilRepository;
import br.com.sp.fatec.springbootapp.repository.UsuarioRepository;
import br.com.sp.fatec.springbootapp.service.exceptions.DataIntegrityException;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	CredencialRepository credencialRepo;
	
	@Autowired
	PerfilRepository perfilRepo;
	
	@Autowired
	UsuarioRepository usuarioRepo;

	@Transactional
	public Usuario criarUsuario(String nome, String email, String login, String senha, String nomePerfil) throws Exception {
		
		Perfil perfil = perfilRepo.findByNome(nomePerfil);
		
		if(perfil != null) {
			Credencial credencial = credencialRepo.findByLogin(login);
			
			if(credencial == null ) {
				
				credencial = new Credencial();
				credencial.setLogin(login);
				credencial.setSenha(senha);
				credencial.setListaPerfil(new HashSet<Perfil>());
				credencial.getListaPerfil().add(perfil);
				credencialRepo.save(credencial);
			}
			
			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setCredencial(credencial);
			usuarioRepo.save(usuario);
			return usuario;
		}
		
		throw new Exception("Perfil não encontrado");
	}
	
	public Usuario buscarPorId(Long id) {
		Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
		if(usuarioOp != null) {
			return usuarioOp.get();
		}
		throw new RuntimeException("Usuário não encontrado");
	}
	
	public void deletar(Long id) {
		Usuario usuario = buscarPorId(id);
		usuario.getCredencial().setSnExcluido(true);
		
		try {
			usuarioRepo.save(usuario);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("O registro está sendo usado no sistema");
		}
		
	}
	
	
}
