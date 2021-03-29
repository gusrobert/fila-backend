package br.com.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sp.fatec.springbootapp.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
