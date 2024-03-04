package com.evc.libreria.usuarios.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evc.libreria.usuarios.models.entity.Usuario;
import com.evc.libreria.usuarios.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios") // para el acceso utilizar localhost:8001/api/usuarios
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	@GetMapping
	public List<Usuario> listar(){
		return usuarioService.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> detalle(@PathVariable Long id){
 		Optional<Usuario> usuarioOptional = usuarioService.buscarPorId(id);
 		if(usuarioOptional.isPresent()) {
 			return ResponseEntity.ok(usuarioOptional.get());
 		}
 		return ResponseEntity.notFound().build();		
	}
	
	@PostMapping
	public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result){
		if(result.hasErrors()) {
			return validar(result);
		}
		
		//if(!usuario.getEmail().isEmpty() && usuarioService.porEmail(usuario.getEmail()).isPresent()) {
		if(!usuario.getEmail().isEmpty() && usuarioService.existePorEmail(usuario.getEmail())) {
			return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "Correo registrado por otro usuario"));
		}
		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuario));
		
	}
	
	@PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {
		
		if(result.hasErrors()) {
        	return validar(result);
        }
		
		Optional<Usuario> o = usuarioService.buscarPorId(id);
        if (o.isPresent()) {
            Usuario usuarioDb = o.get();           
            if (!usuario.getEmail().isEmpty() &&
                    !usuario.getEmail().equalsIgnoreCase(usuarioDb.getEmail()) &&
                    usuarioService.porEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("mensaje", "Ya existe un usuario con ese correo electronico!"));
            }
            usuarioDb.setNombre(usuario.getNombre());
            usuarioDb.setEmail(usuario.getEmail());
            usuarioDb.setPassword(usuario.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuarioDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Usuario> o = usuarioService.buscarPorId(id);
        if (o.isPresent()) {
            usuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
