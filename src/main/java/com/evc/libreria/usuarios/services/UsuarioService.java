package com.evc.libreria.usuarios.services;

import java.util.List;
import java.util.Optional;

import com.evc.libreria.usuarios.models.entity.Usuario;

public interface UsuarioService {
	List<Usuario> listar();
	Optional<Usuario> buscarPorId(Long id);
	Usuario guardar(Usuario usuario);
	void eliminar(Long id);
	
	Optional<Usuario> porEmail(String email);
    boolean existePorEmail(String email);

}
