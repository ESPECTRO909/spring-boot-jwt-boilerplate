package com.sinue.auth.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.sinue.auth.model.Usuario;
import com.sinue.auth.repository.UsuarioRepository;

import java.util.List;
// Esta clase es la encargada de cargar los detalles del usuario desde la base de datos para que Spring Security pueda autenticarlo. Implementa UserDetailsService y su método loadUserByUsername, que busca un usuario por su username y devuelve un objeto UserDetails con su información (username, password y roles). Si el usuario no se encuentra, lanza una excepción UsernameNotFoundException.
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name()))
        );
    }
}