package br.com.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sp.fatec.springbootapp.entity.Apresentacao;

public interface ApresentacaoRepository extends JpaRepository<Apresentacao, Long> {

}
