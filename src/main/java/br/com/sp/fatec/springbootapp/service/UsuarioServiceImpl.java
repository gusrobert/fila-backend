package br.com.sp.fatec.springbootapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

	@Autowired
	private PasswordEncoder passEncoder;

	@Transactional
	public Usuario criarUsuario(String email, String login, String senha, String nomePerfil) throws Exception {
		
		Perfil perfil = perfilRepo.findByNome(nomePerfil);
		
		if(perfil != null) {
			Credencial credencial = credencialRepo.findByLogin(login);
			
			if(credencial == null ) {
				
				credencial = new Credencial();
				credencial.setLogin(login);
				credencial.setSenha(passEncoder.encode(senha));
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
	
	@PreAuthorize("hasRole('ADMIN')")
	public List<Usuario> buscarTodosUsuarios() {
		return usuarioRepo.findAll();
	}
	
	@PreAuthorize("isAuthenticated()")
	public Usuario buscarPorId(Long id) {
		Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
		if(usuarioOp != null && !usuarioOp.isEmpty()) {
			return usuarioOp.get();
		}
		throw new RuntimeException("Usuário não encontrado");
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	public void deletar(Long id) {
		Usuario usuario = buscarPorId(id);
		usuario.getCredencial().setSnExcluido(true);
		
		try {
			usuarioRepo.save(usuario);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("O registro está sendo usado no sistema");
		}
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Credencial credencial = credencialRepo.findByLogin(username);	
		
		if(credencial == null) {
			throw new UsernameNotFoundException("Usuário " + username + " não encontrado!");
		}
		
		
		return User.builder().username(username).password(credencial.getSenha())
				.authorities(credencial.getListaPerfil().stream()
                .map(Perfil::getNome).collect(Collectors.toList())
                .toArray(new String[credencial.getListaPerfil().size()]))
				.build();
	}	
	
}
