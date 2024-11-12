# Mi Aplicación Spring Boot

## Descripción
Este proyecto es una aplicación Spring Boot que proporciona una API RESTful para eventos. Está construido con Spring Boot y utiliza Java.

## Requisitos Previos
- Java JDK 17 o superior
- Maven 3.6+
- MySQL 8.0+ (o la base de datos que uses)
- IDE de tu preferencia (IntelliJ IDEA, Eclipse, VS Code)

## Tecnologías
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL
- Lombok
- Maven
- Swagger/OpenAPI

## Configuración del Proyecto

### Base de Datos
1. Configurar las credenciales en `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mi_aplicacion
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

## Instalación y Ejecución

1. Clonar el repositorio:
```bash
git clone https://github.com/tu-usuario/tu-proyecto.git
cd tu-proyecto
```

2. Compilar el proyecto:
```bash
mvn clean install
```

3. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## Estructura del Proyecto
```
src/
├── main/
│   ├── java/
│   │   └── com/fmontiel/eventos/
│   │       └── Application.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
```

## Documentación API
La documentación de la API está disponible a través de Swagger UI en:
```
http://localhost:8080/swagger-ui.html
```

## Pruebas
Ejecutar las pruebas unitarias:
```bash
mvn test
```

Ejecutar las pruebas de integración:
```bash
mvn verify
```

## Despliegue
Para generar el archivo JAR para producción:
```bash
mvn package -Pprod
```

El archivo JAR se generará en la carpeta `target/`.

## Contribuir
1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## Licencia
Este proyecto está bajo la licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.

## Contacto
Tu Nombre - [@tutwitter](https://twitter.com/tutwitter) - email@ejemplo.com

Link del proyecto: [https://github.com/tu-usuario/tu-proyecto](https://github.com/tu-usuario/tu-proyecto)
