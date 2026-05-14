package com.sinue.auth.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
// Esta clase maneja de forma global las excepciones que puedan ocurrir en los controladores. Por ejemplo, si un usuario intenta autenticarse con credenciales incorrectas, se lanzará una BadCredentialsException que será capturada aquí para devolver un mensaje de error adecuado al cliente. También captura cualquier otra excepción no manejada para evitar que el servidor devuelva errores sin formato.
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
// Aquí se pueden agregar métodos para manejar diferentes tipos de excepciones específicas. Por ejemplo, si se lanza una BadCredentialsException al intentar autenticarse con un JWT inválido, este método capturará esa excepción y devolverá una respuesta con un mensaje de error y un código de estado 401 Unauthorized.
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentials(BadCredentialsException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "Credenciales inválidas");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
    }

    // Si un usuario autenticado intenta acceder a un recurso para el que no tiene permisos, se lanzará una AccessDeniedException que será capturada aquí para devolver un mensaje de error adecuado al cliente con un código de estado 403 Forbidden.
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException ex) {
        Map<String, String> body = new HashMap<>();
        body.put("error", "No tienes permisos para realizar esta acción");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    // Este método captura cualquier RuntimeException que no haya sido manejada por otros métodos específicos. Por ejemplo, si en el servicio de denuncias se lanza una RuntimeException con un mensaje personalizado cuando no se encuentra una denuncia, este método capturará esa excepción y devolverá el mensaje al cliente con un código de estado 404 Not Found.
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {
        log.warn("Error de negocio: {}", ex.getMessage());
        Map<String, String> body = new HashMap<>();
        body.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // Manejo de errores de validación (por ejemplo, cuando falla un @Valid en un DTO)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> body = new HashMap<>();
        String errores = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        body.put("error", "Errores de validación: " + errores);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
// Aquí se pueden agregar más métodos para manejar otras excepciones específicas, como AccessDeniedException, MethodArgumentNotValidException, etc.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneric(Exception ex) {
        log.error("Error inesperado: ", ex);
        Map<String, String> body = new HashMap<>();
        body.put("error", "Ocurrió un error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}