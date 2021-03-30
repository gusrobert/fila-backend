package br.com.sp.fatec.springbootapp.service;

import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sp.fatec.springbootapp.entity.Credencial;
import br.com.sp.fatec.springbootapp.entity.Grupo;
import br.com.sp.fatec.springbootapp.entity.Perfil;
import br.com.sp.fatec.springbootapp.entity.Usuario;
import br.com.sp.fatec.springbootapp.repository.CredencialRepository;
import br.com.sp.fatec.springbootapp.repository.GrupoRepository;
import br.com.sp.fatec.springbootapp.repository.PerfilRepository;
import br.com.sp.fatec.springbootapp.repository.UsuarioRepository;

@Service("segurancaService")
public class SegurancaServiceImpl implements SegurancaService {
	
	@Autowired
	CredencialRepository credencialRepo;
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Autowired
	PerfilRepository perfilRepo;
	
	@Autowired
	GrupoRepository grupoRepo;
	
	@Transactional
	public Usuario criarUsuario(String nome, String email, String login, String senha, String nomePerfil) throws Exception {
		
		Perfil perfil = perfilRepo.findByNome(nomePerfil);
		
		if(perfil != null) {
			Credencial credencial = credencialRepo.findByLogin(login);
			
			if(credencial == null ) {
				
				credencial = new Credencial();
				credencial.setLogin(login);
				credencial.setSenha(senha);
				credencial.setListaPerfil(new HashSet<Perfil>());
				credencial.getListaPerfil().add(perfil);
			}
			
			Usuario usuario = new Usuario();
			usuario.setEmail(email);
			usuario.setCredencial(credencial);
			usuarioRepo.save(usuario);
			return usuario;
		}
		
		throw new Exception("Perfil não encontrado");
	}
	
	@Transactional
	public Perfil criarPerfil(String nome) {
		Perfil perfil = perfilRepo.findByNome(nome);
		
		if(perfil == null) {
			perfil = new Perfil();
			perfil.setNome(nome);
		}
		
		return perfil;
	}
	
	@Transactional
	public Grupo criarGrupo(String nome, List<String> loginAlunos, String nomePerfil) throws Exception {
		
		Grupo grupo = grupoRepo.findByNome(nome);
		
		
		if(grupo == null ) {
			Perfil perfil = perfilRepo.findByNome(nomePerfil);

			if(perfil != null) {
				grupo = new Grupo();
				grupo.setNome(nome);
			}
			
			throw new Exception("Perfil não encontrado");
		}
		
		return grupo;
		
	}

}
