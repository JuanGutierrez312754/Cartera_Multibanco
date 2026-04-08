# Cartera Multibanco

## Descripción
Sistema backend desarrollado en Spring Boot que permite gestionar usuarios, cartera, cuentas bancarias y realizar transferencias entre cuentas.

## Tecnologías
- Java
- Spring Boot
- MySQL
- JDBC
- Swagger

## Funcionalidades
- CRUD de usuarios
- Gestión de cartera
- Gestión de cuentas bancarias
- Transferencias entre cuentas

## Endpoints principales

### Usuarios
GET /usuarios  
POST /usuarios  
PUT /usuarios/{cedula}  
DELETE /usuarios/{cedula}  

### Cartera
GET /cartera/{cedula}  
POST /cartera  
PUT /cartera/{cedula}  

### Cuentas
GET /cuentas/{cedula}  
POST /cuentas  
PUT /cuentas/{cuenta}  
DELETE /cuentas/{cuenta}  

### Transferencias
POST /transferencias  

## Swagger
http://localhost:8080/swagger-ui.html

## Base de datos
Importar el archivo:
cartera_multibanco.sql