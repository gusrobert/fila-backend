package br.com.sp.fatec.springbootapp.service;

import br.com.sp.fatec.springbootapp.entity.Usuario;

public interface UsuarioService {
	
	public Usuario criarUsuario(String nome, String email, String login, String senha, String perfil) throws Exception;
	
	public Usuario buscarPorId(Long id);
	
	public void deletar(Long id);

}
