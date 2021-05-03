package br.com.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sp.fatec.springbootapp.entity.Apresentacao;

public interface ApresentacaoRepository extends JpaRepository<Apresentacao, Long> {
	
	@Query("select a from Apresentacao a inner join a.fila f where f.id = ?1")
	public List<Apresentacao> buscarApresentacaoPorIdFila(Long idFila);

}
