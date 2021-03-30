package br.com.sp.fatec.springbootapp.entity;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Fila {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="professor")
	private Professor professor;
	
	@Column(name="hora_inicio")
	private LocalDateTime horaInicio;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="fila")
	private Set<Apresentacao> apresentacoes;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalDateTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Set<Apresentacao> getApresentacoes() {
		return apresentacoes;
	}

	public void setApresentacoes(Set<Apresentacao> apresentacoes) {
		this.apresentacoes = apresentacoes;
	}
}
