package com.evc.libreria.usuarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.evc.libreria.usuarios.models.entity.Usuario;
import com.evc.libreria.usuarios.repositories.UsuarioRespository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRespository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}

	@Override
	@Transactional
	public Usuario guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void eliminar(Long id) {
		usuarioRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Optional<Usuario> porEmail(String email) {
		//return usuarioRepository.findByEmail(email); varias formas de implementar la busqueda
		return usuarioRepository.buscarPorEmail(email);
	}

	@Override
	public boolean existePorEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
}
