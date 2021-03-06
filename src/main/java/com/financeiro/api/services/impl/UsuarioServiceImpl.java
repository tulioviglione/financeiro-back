package com.financeiro.api.services.impl;

import java.util.Locale;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.financeiro.api.enteties.Usuario;
import com.financeiro.api.enums.PerfilEnum;
import com.financeiro.api.enums.SituacaoUsuarioEnum;
import com.financeiro.api.exceptions.BusinessException;
import com.financeiro.api.repositories.UsuarioRepository;
import com.financeiro.api.services.UsuarioService;
import com.financeiro.api.utils.PasswordUtils;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public boolean isEmailExist(String email) {
		log.debug("verificando se ja existe e-mail informado cadastrado");
		Optional<Usuario> user = usuarioRepository.findByEmail(email);
		return (user.isPresent())?user.get() != null:user.isPresent();
	}

	@Override
	public boolean isLoginExist(String login) {
		log.debug("verificando se ja existe login informado cadastrado");
		Optional<Usuario> user = usuarioRepository.findByLogin(login);
		return (user.isPresent())?user.get() != null:user.isPresent();
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
		log.debug("pesquisa usuário por e-mail");
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Optional<Usuario> findByLogin(String login) {
		log.debug("pesquisa usuário por login");
		return usuarioRepository.findByLogin(login);
	}

	@Override
	public Usuario cadastraNovoUsuario(Usuario usuario) throws BusinessException {
		log.debug("cadastra novo usuario no sistema");
		usuario.setSituacao(SituacaoUsuarioEnum.BLOQUEADO);
		usuario.setPerfil(PerfilEnum.USER);
		usuario.setSenha(PasswordUtils.gerarBCrypt(usuario.getSenha()));
		try {
			return usuarioRepository.save(usuario);
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException(messageSource.getMessage("login.cadastrado", null, Locale.getDefault()), e);
		}
	}

	@Override
	public Usuario persisteUsuario(Usuario usuario) {
		log.debug("Persiste informações do usuário");
		return usuarioRepository.save(usuario);
	}

	@Override
	public Page<Usuario> buscaTodosUsuarios(PageRequest pageRequest) {
		return this.usuarioRepository.findAll(pageRequest);
	}

}