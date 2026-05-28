package com.sinue.auth.dto.requets;
import java.time.LocalDate;

public class AuthRequestDTO {
    private String nombre;
    private String username;
    private String password;
    private String correo;
    private LocalDate fechaNacimiento;

    public AuthRequestDTO() {}

    public String getNombre(){
        return nombre;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public String getCorreo(){
        return correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }       
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}

