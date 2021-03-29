package br.com.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sp.fatec.springbootapp.entity.Fila;

public interface FilaRepository extends JpaRepository<Fila, Long> {

}
