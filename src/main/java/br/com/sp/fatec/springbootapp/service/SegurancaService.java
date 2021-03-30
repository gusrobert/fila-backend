package br.com.sp.fatec.springbootapp.service;

import java.util.List;

import br.com.sp.fatec.springbootapp.entity.Grupo;
import br.com.sp.fatec.springbootapp.entity.Perfil;
import br.com.sp.fatec.springbootapp.entity.Usuario;

public interface SegurancaService {

	public Usuario criarUsuario(String nome, String email, String login, String senha, String perfil) throws Exception;
	
	public Perfil criarPerfil(String nome);
	
	public Grupo criarGrupo(String nome, List<String> loginAlunos, String nomePerfil) throws Exception;
	
}
