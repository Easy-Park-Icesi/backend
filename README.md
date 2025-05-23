# EasyPark ICESI - Backend

Este repositorio contiene el backend del proyecto **EasyPark ICESI**, desarrollado con Java y Spring Boot. El backend permite gestionar zonas de parqueo y sincronizar datos con Firebase Realtime Database.

---

## ⚠️ Importante: Configuración Firebase

> ⚠️ **El archivo `firebase-config.json` no está incluido en este repositorio.**

Este archivo contiene credenciales sensibles de la cuenta de servicio de Firebase y ha sido **excluido por seguridad** mediante `.gitignore`.

### 🔧 ¿Qué hacer?

Debes crear el archivo en la siguiente ruta:

```
src/main/resources/
```

## 🚀 Cómo levantar el proyecto

Clona este repositorio.

Asegúrate de tener Java 17+ y Maven instalados.

Coloca tu archivo firebase-config.json como se explicó.

Ejecuta:

```bash
./mvnw spring-boot:run
```


## 🔗 Endpoints útiles

### ✅ Alternar estado de parqueadero (Postman)

```http
POST http://localhost:8080/api/zonas/a/parqueaderos/pk1/toggle
```
Este endpoint cambia el estado (ocupado/libre) del parqueadero pk1 en la zona a.

## ⚠️ Importante: Configuración de CORS

Para que el backend permita solicitudes CORS correctamente, es necesario especificar desde qué orígenes se aceptarán las peticiones. Esto es fundamental cuando el frontend y el backend se ejecutan en diferentes puertos o dominios, ya que los navegadores bloquean por seguridad las solicitudes entre orígenes distintos si no están permitidas explícitamente.

Para configurar el origen permitido, modifica el archivo:

### src/main/com.easyparkicesi/resource/application.properties

Y actualiza la propiedad `frontend.url` con la URL donde se ejecuta tu frontend. Por ejemplo:

```properties
frontend.url=http://localhost:3000
```
Guarda los cambios y reinicia el backend para que la configuración surta efecto.
