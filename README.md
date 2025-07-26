# Category-API

Este proyecto ha sido desarrollado con **Spring Boot** y ha sido **Dockerizado** para su despliegue en ambientes de desarrollo o prueba. Proporciona una API RESTful para gestionar operaciones CRUD sobre un modelo de **categorías**.

## Ruta base de la API

```
http://localhost:8081/api/categories
```

> Nota: El puerto puede variar dependiendo de la configuración local.

---

##  Tecnologías utilizadas

- Java 17
- Spring Boot
- MySQL
- Docker
- Maven

---

## Requisitos previos

- Docker instalado
- Acceso a una terminal
- Conexión a Internet para descargar imágenes de Docker

---

## Configuración de la red Docker

Ambos contenedores (base de datos y aplicación) deben estar conectados a la misma red para comunicarse correctamente.

```bash
docker network create test-network
```

### Inicializar contenedor MySQL

Antes de levantar la aplicación, se debe ejecutar un contenedor de MySQL:

```bash
docker run --name test-db -e MYSQL_ROOT_PASSWORD=admin123 -e MYSQL_DATABASE=test -p 3306:3306 -d --network test-network mysql
```

**Datos importantes:**

- **Usuario:** root
- **Contraseña:** admin123
- **Base de datos:** test
- **Contenedor:** test-db
- **Puerto local:** 3306

---

### Descargar e iniciar la API

La imagen se encuentra publicada en Docker Hub:

```
https://hub.docker.com/r/antonio215/api-categories-crud
```

Ejecuta el siguiente comando para iniciar la API:

```bash
docker run -d --name c-app-categoria --network test-network -p 8082:8003 -e PORT=8003 -e DB_HOST=test-db:3306 antonio215/api-categories-crud
```

---

## Endpoints principales

| Método | Endpoint                    | Descripción               |
|--------|-----------------------------|---------------------------|
| GET    | `/api/categories`           | Listar categorías         |
| POST   | `/api/categories`           | Crear nueva categoría     |
| PUT    | `/api/categories/{id}`      | Actualizar categoría      |
| DELETE | `/api/categories/{id}`      | Eliminar categoría        |

---

##  Probar con Postman 

```bash
http://localhost:8081/api/categories
```
