package br.com.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sp.fatec.springbootapp.entity.Credencial;

public interface CredencialRepository extends JpaRepository<Credencial, Long> {
	
	public Credencial findByLogin(String login);
	
	public List<Credencial> findByListaPerfilNomeContains(String nome);
	
	@Query("select c from Credencial c where c.login = ?1 and c.senha = ?2")
	public Credencial buscaCredencialPorLoginSenha(String login, String senha);

}
