package br.com.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sp.fatec.springbootapp.entity.Credencial;

public interface CredencialRepository extends JpaRepository<Credencial, Long> {
	
	public Credencial findByLogin(String login);
	
	public List<Credencial> findByListaPerfilNomeContains(String nome);

}
