package br.com.sp.fatec.springbootapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.sp.fatec.springbootapp.entity.Usuario;

public interface UsuarioService extends UserDetailsService {
	
	public Usuario criarUsuario(String email, String login, String senha, String perfil) throws Exception;
	
	public List<Usuario> buscarTodosUsuarios();
	
	public Usuario buscarPorId(Long id);
	
	public void deletar(Long id);

}
