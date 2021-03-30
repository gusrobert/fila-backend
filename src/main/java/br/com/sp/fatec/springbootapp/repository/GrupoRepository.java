package br.com.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sp.fatec.springbootapp.entity.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
	
	public Grupo findByNome(String nome);

}
