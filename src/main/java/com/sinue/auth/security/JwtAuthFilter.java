package com.sinue.auth.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
// Este filtro se ejecuta en cada petición y se encarga de verificar si el cliente envió un JWT válido en el header Authorization. Si el token es correcto, extrae el username, carga los detalles del usuario desde la base de datos y registra la autenticación en el contexto de Spring Security para que el resto de la aplicación pueda identificar al usuario autenticado.
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Leer el header Authorization
        String authHeader = request.getHeader("Authorization");

        // 2. Si no hay header o no empieza con "Bearer ", dejar pasar sin autenticar
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extraer el token (quitar el prefijo "Bearer ")
        String token = authHeader.substring(7);

        // 4. Validar el token y autenticar al usuario
        if (jwtService.isTokenValid(token)) {
            String username = jwtService.extractUsername(token);

            // Solo autenticar si aún no hay autenticación en el contexto
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // 5. Cargar los datos del usuario desde la BD
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // 6. Crear el objeto de autenticación con sus roles
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,               // credenciales (null porque ya validamos el JWT)
                                userDetails.getAuthorities()
                        );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 7. Registrar la autenticación en el contexto de Spring Security
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 8. Continuar con el siguiente filtro
        filterChain.doFilter(request, response);
    }
}
