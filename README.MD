# AppSalon
![Captura de Pantalla 1](/screenshots/login.png)
AppSalon es una aplicación web desarrollada en Java utilizando Jakarta Server Faces (JSF) y JPA Hibernate para la interacción con la base de datos Postgres. Esta aplicación está diseñada para la gestión eficiente de un salón de belleza, proporcionando funcionalidades específicas tanto para clientes como para administradores.

## Funcionalidades

### COMO CLIENTE:

- **Crear Usuario e Iniciar Sesión**: Los clientes pueden registrarse y acceder a la aplicación para gestionar sus reservas.
  
- **Realizar Reservas Personalizadas**: Los clientes pueden crear reservas seleccionando servicios, fecha y hora según su conveniencia.

### COMO ADMINISTRADOR:

1. **Ver Servicios Reservados**: Los administradores pueden visualizar los servicios reservados, con la capacidad de filtrar por fecha para una mejor organización.

2. **Marcar Servicios como Completados**: Permite a los administradores dar por terminado un servicio una vez que se ha completado satisfactoriamente.

3. **Gestión de Servicios**: Los administradores pueden agregar y actualizar servicios, asegurando que la lista de servicios esté siempre actualizada.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal.
- **Jakarta Server Faces (JSF)**: Framework de Java para el desarrollo de interfaces de usuario web.
- **JPA Hibernate**: Tecnología de persistencia para el mapeo objeto-relacional.
- **Postgres**: Sistema de gestión de base de datos relacional.

## Seguridad

La aplicación implementa medidas básicas de seguridad, como el uso de un filtro que evita el acceso a páginas directamente mediante la URL. Además, las contraseñas se almacenan de forma segura utilizando el algoritmo de hash SHA-256 para garantizar la privacidad de los usuarios.

## Capturas de Pantalla


![Captura de Pantalla 2](/screenshots/admin.png)

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo [LICENSE](/LICENSE) para más detalles.
