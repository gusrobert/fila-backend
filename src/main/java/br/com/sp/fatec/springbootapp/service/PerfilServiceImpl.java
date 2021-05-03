package br.com.sp.fatec.springbootapp.service;

import java.util.Optional;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.sp.fatec.springbootapp.entity.Perfil;
import br.com.sp.fatec.springbootapp.repository.PerfilRepository;
import br.com.sp.fatec.springbootapp.service.exceptions.DataIntegrityException;
import br.com.sp.fatec.springbootapp.service.exceptions.ObjectNotFoundException;

@Service("perfilService")
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	PerfilRepository perfilRepo;

	@Transactional
	public Perfil criarPerfil(String nome) {
		Perfil perfil = perfilRepo.findByNome(nome);

		if (perfil == null) {
			perfil = new Perfil();
			perfil.setNome(nome);
			perfilRepo.save(perfil);
		}

		return perfil;
	}

	public Perfil buscarPorId(Long id) {
		Optional<Perfil> obj = perfilRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Perfil não encontrado!"));
	}
	
	public Perfil inserir(Perfil perfil) {
		Perfil perfilRef = perfilRepo.findByNome(perfil.getNome());
		
		if(perfilRef != null) {
			throw new DataIntegrityException("Já existe um registro cadastrado com este nome");
		}
		
		validarNome(perfil.getNome());
		
		perfil.setNome(perfil.getNome());
		return perfilRepo.save(perfil);
	}
	
	private void validarNome(String nome) {
		Pattern regex = Pattern.compile("[$&+, :;=\\\\?@#|/'<>.^*()%!-]");
		
		if (regex.matcher(nome).find()) {
			throw new DataIntegrityException("Nome não pode conter caracteres especiais");
		} 
	}
	
	public void editar(Perfil perfil, Long idPerfil) {
		Perfil perfilRef = buscarPorId(idPerfil);
		
		perfilRef.setNome(perfil.getNome());
		
		perfilRepo.save(perfilRef);
	}

	public void deletar(Long id) {
		buscarPorId(id);
		try {
			perfilRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("O registro está relacionado com outra entidade");
		}

	}
}
