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
import javax.persistence.OneToOne;

@Entity
public class Credencial {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id;
	
	@Column(unique = true)
	private String login;
	
	@Column
	private String senha;
	
	@Column
	private Boolean sn_bloqueado = Boolean.FALSE;
	
	@Column
	private Boolean sn_excluido = Boolean.FALSE;
	
	@Column
	private Boolean sn_online = Boolean.FALSE;
	
	@ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "credencial_perfil",
        joinColumns = { @JoinColumn(name = "id_credencial") },
        inverseJoinColumns = { @JoinColumn(name = "id_perfil") } )
	private Set<Perfil> listaPerfil;
	
	@OneToOne(mappedBy="credencial")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getSn_bloqueado() {
		return sn_bloqueado;
	}

	public void setSn_bloqueado(Boolean sn_bloqueado) {
		this.sn_bloqueado = sn_bloqueado;
	}

	public Boolean getSn_excluido() {
		return sn_excluido;
	}

	public void setSn_excluido(Boolean sn_excluido) {
		this.sn_excluido = sn_excluido;
	}

	public Boolean getSn_online() {
		return sn_online;
	}

	public void setSn_online(Boolean sn_online) {
		this.sn_online = sn_online;
	}

	public Set<Perfil> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(Set<Perfil> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
