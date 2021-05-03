package br.com.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sp.fatec.springbootapp.entity.Credencial;
import br.com.sp.fatec.springbootapp.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByCredencial(Credencial credencial);
	
	@Query("select u from Usuario u inner join u.credencial c inner join c.listaPerfil p where p.nome = ?1")
	public List<Usuario> buscarUsuarioPorNomePerfil(String nomePerfil);
	
}
