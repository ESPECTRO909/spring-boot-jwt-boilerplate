package com.sinue.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sinue.auth.model.Usuario;

import java.util.Optional;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findFirstByUsername(String username);

    Optional<Usuario>findByFechaNacimiento(LocalDate fechaNacimiento);

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByUsername(String username);

    Page<Usuario> findAll(Pageable pageable);


}
