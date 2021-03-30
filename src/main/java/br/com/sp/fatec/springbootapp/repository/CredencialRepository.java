package br.com.sp.fatec.springbootapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sp.fatec.springbootapp.entity.Credencial;
import br.com.sp.fatec.springbootapp.entity.Perfil;

public interface CredencialRepository extends JpaRepository<Credencial, Long> {

}
