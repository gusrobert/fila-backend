package br.com.sp.fatec.springbootapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.sp.fatec.springbootapp.controller.View;

@Entity
public class Perfil {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@JsonView(View.Perfil.class)
	@Column
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
