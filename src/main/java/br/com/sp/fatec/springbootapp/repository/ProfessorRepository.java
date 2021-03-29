package br.com.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sp.fatec.springbootapp.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}
