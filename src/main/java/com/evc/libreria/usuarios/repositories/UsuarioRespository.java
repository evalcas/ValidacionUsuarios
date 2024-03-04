package com.evc.libreria.usuarios.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.evc.libreria.usuarios.models.entity.Usuario;

public interface UsuarioRespository extends CrudRepository<Usuario, Long> {
	//tres formas de implementar busquedas personalizadas
	Optional<Usuario> findByEmail(String email);

    @Query("select u from Usuario u where u.email=?1")
    Optional<Usuario> buscarPorEmail(String email);

    boolean existsByEmail(String email);

}
