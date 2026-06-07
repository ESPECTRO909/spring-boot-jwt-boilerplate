# Spring Boot JWT Auth Boilerplate 

Una plantilla base lista para producción orientada a la creación de APIs REST seguras. Este proyecto elimina la necesidad de configurar manualmente la seguridad desde cero, proporcionando un sistema de autenticación robusto, manejo de roles y gestión de usuarios.

##  Tecnologías

* **Java**  (17)
* **Spring Boot** 3.x
* **Spring Security**
* **JSON Web Tokens (JWT)**
* **Spring Data JPA / Hibernate**
* **MySQL / PostgreSQL** 

##  Características principales

* Registro e inicio de sesión de usuarios.
* Autenticación sin estado mediante tokens JWT.
* Control de Acceso Basado en Roles (RBAC) con roles `ADMIN` y `USER`.
* Manejo global de excepciones (`GlobalExceptionHandler`).
* Separación limpia de capas (Controladores, Servicios, Repositorios, DTOs).

## Instalación y Configuración

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/tu-usuario/spring-boot-jwt-boilerplate.git](https://github.com/tu-usuario/spring-boot-jwt-boilerplate.git)
   cd spring-boot-jwt-boilerplate
