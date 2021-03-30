package br.com.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sp.fatec.springbootapp.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
	
	public Perfil findByNome(String nome);
	
	@Query("select p from perfil p where lower(p.nome) = 'alunos'")
	public List<Perfil> findAllAlunos();

}
