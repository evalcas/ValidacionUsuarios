package com.evc.libreria.usuarios.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nombre;
	
	@NotEmpty
    @Email
	@Column(unique = true)
	private String email;
	
	@NotBlank
	private String password;
}


/* mas ejemplos
	@NotBlank(message = "Los apellidos no pueden estar en blanco")
    @Size(max = 100, message = "Los apellidos deben tener como máximo 100 caracteres")
    private String apellidos;

    @NotBlank(message = "Los nombres no pueden estar en blanco")
    @Size(max = 100, message = "Los nombres deben tener como máximo 100 caracteres")
    private String nombres;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @NotBlank(message = "El email no puede estar en blanco")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "El DNI no puede estar en blanco")
    @Size(min = 8, max = 8, message = "El DNI debe tener exactamente 8 caracteres")
    private String dni;

    @NotBlank(message = "El número de seguro social no puede estar en blanco")
    @Pattern(regexp = "[0-9]{3}-[0-9]{2}-[0-9]{4}", message = "Formato de seguro social no válido")
    private String numeroSeguroSocial; 

*/