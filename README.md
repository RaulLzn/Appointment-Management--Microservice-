# Appointment Management Microservice - SAFI UPB

Este proyecto es el **Microservicio de Gestión de Citas** desarrollado como parte del sistema modular SAFI (Servicios Académicos y Psicosociales Integrados) para la Universidad Pontificia Bolivariana (UPB). El microservicio permite gestionar las citas entre estudiantes y responsables, integrando otros microservicios para obtener la información relacionada de dependencias, estudiantes, responsables y más.

## Características

- **Gestión de Citas:** Almacena información completa de citas como fechas, razones y estados.
- **Integración con otros microservicios:** Utiliza `Feign` para obtener información de entidades relacionadas como `Student`, `Responsible` y `Dependency` desde otros microservicios.
- **Sincronización de datos:** Permite la sincronización de los IDs (Primary Keys) de entidades externas que se manejan en este microservicio.
- **Almacenamiento distribuido:** Solo se almacenan los IDs de entidades relacionadas, mientras que el resto de la información se consulta en tiempo real cuando sea necesario.
- **Spring Boot:** Basado en Spring Boot para el manejo de la API REST.
- **Seguridad:** Implementación de seguridad con Spring Security (puede desactivarse para las pruebas iniciales).

## Estructura del Proyecto

El proyecto sigue una estructura basada en buenas prácticas de arquitectura de microservicios:

```plaintext
AppointmentManagement
│
├── src/main/java/upb/safi/AppointmentManagement
│   ├── controller/         # Controladores REST
│   ├── service/            # Lógica de negocio y sincronización de datos
│   ├── repository/         # Repositorios JPA para interactuar con la base de datos
│   ├── model/              
│   │   ├── entity/         # Entidades de la base de datos
│   │   ├── dto/            # Data Transfer Objects (DTOs)
│   │   └── domain/         # Clases de dominio (enumeraciones, constantes)
│   ├── config/             # Configuración de Spring, Feign y Cache
│   └── client/             # Clientes Feign para interactuar con otros microservicios
│
├── build.gradle            # Archivo de configuración de Gradle
└── README.md               # Este archivo
```

## Dependencias

Este proyecto utiliza las siguientes dependencias:

- **Spring Boot**
- **Spring Data JPA**: Para la persistencia de datos.
- **PostgreSQL Driver**: Para conectarse a la base de datos PostgreSQL.
- **Spring Security**: Para gestionar la autenticación y autorización.
- **Spring Cloud OpenFeign**: Para la integración con otros microservicios.
- **Resilience4J**: Para manejar la resiliencia y tolerancia a fallos.
- **Lombok**: Para reducir el código boilerplate.
- **JUnit**: Para realizar pruebas unitarias.
- **Mockito**: Para realizar pruebas unitarias con simulación de datos.

## Base de Datos

El microservicio solo almacena de manera completa la información de las citas (Appointment). El resto de la información de entidades como `Student`, `Responsible`, `Dependency` solo almacena la **Primary Key (ID)** y se sincroniza con otros microservicios cuando sea necesario.

### Esquema de la tabla `Appointment`

```sql
CREATE TABLE appointment (
  appointmentId BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  responsibleId BIGINT NOT NULL,
  dependencyId BIGINT NOT NULL,
  studentId BIGINT NOT NULL,
  requestTimestamp TIMESTAMP,
  appointmentTimestamp TIMESTAMP,
  mode VARCHAR(50),
  status VARCHAR(50),
  reason VARCHAR(300)
);
```

### Otras tablas de referencia:

- `Responsible`
- `Student`
- `Dependency`
- Estas tablas solo almacenan los IDs en este microservicio.

## Endpoints de la API

### Crear una cita

**POST** `/api/appointments`

```json
{
  "responsibleId": 1,
  "dependencyId": 1,
  "studentId": 1,
  "requestTimestamp": "2024-10-01T10:00:00",
  "appointmentTimestamp": "2024-10-01T11:00:00",
  "mode": "Presencial",
  "status": "Agendada",
  "reason": "Consulta académica"
}
```

### Obtener una cita

**GET** `/api/appointments/{appointmentId}`

### Sincronizar IDs de entidades

- **POST** `/api/appointments/sync-responsible`
- **POST** `/api/appointments/sync-student`
- **POST** `/api/appointments/sync-dependency`

Estos endpoints permiten sincronizar las Primary Keys de las entidades gestionadas en otros microservicios.

## Instalación y Ejecución

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/usuario/AppointmentManagement.git
   cd AppointmentManagement
   ```

2. **Configurar la base de datos PostgreSQL**:
   Ajusta las credenciales en el archivo `application.yml` para conectar tu base de datos PostgreSQL.

3. **Compilar y ejecutar**:

   ```bash
   ./gradlew bootRun
   ```

4. **Acceder a la API**:

   El microservicio estará disponible en `http://localhost:8080`.

## Pruebas

Este proyecto incluye pruebas unitarias y de integración utilizando JUnit y Mockito. Ejecuta las pruebas con:

```bash
./gradlew test
```

Para realizar pruebas con Postman, usa los endpoints descritos en la sección anterior.

## Contribuciones

Si deseas contribuir a este proyecto, por favor abre un pull request o contacta al autor del proyecto.
