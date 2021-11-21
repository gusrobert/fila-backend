package br.com.sp.fatec.springbootapp.service;

import br.com.sp.fatec.springbootapp.entity.Perfil;

public interface PerfilService {
	
	public Perfil criarPerfil(String nome);
	
	public Perfil buscarPorId(Long id);
	
	public Perfil inserir(Perfil nomePerfil) throws Exception;
	
	public void editar(Perfil perfil, Long idPerfil);
	
	public void deletar(Long deletar);

}
