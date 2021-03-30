package br.com.sp.fatec.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sp.fatec.springbootapp.entity.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
