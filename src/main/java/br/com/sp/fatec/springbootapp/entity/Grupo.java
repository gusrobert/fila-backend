package br.com.sp.fatec.springbootapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Grupo {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@Column
	private String nome;
	
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "grupo_aluno", 
        joinColumns = { @JoinColumn(name = "id_grupo") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_aluno") } )
	private Set<Aluno> alunos;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="grupo")
	private Set<Apresentacao> apresentacao;

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

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}
	
		
	
}
