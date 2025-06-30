
# 🚀 Spring Boot Playground

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

> 📁 Este repositorio está diseñado como una colección de ejemplos prácticos para aprender a usar **Spring Boot 3 con Java 21**. Cada rama contiene un caso de uso específico con distintas dependencias y configuraciones.

---

## 📋 Menú

- [📚 ¿Qué es Spring Boot?](#qué-es-spring-boot)
- [📘 Sobre este repositorio](#sobre-este-repositorio)
- [🧪 ¿Qué encontrarás en cada rama?](#qué-encontrarás-en-cada-rama)
- [🧩 Lista de dependencias utilizadas](#lista-de-dependencias-utilizadas)
- [📂 Estructura de carpetas](#estructura-de-carpetas)
- [🛠️ Cómo usar este repositorio](#cómo-usar-este-repositorio)
- [📎 Recursos útiles](#recursos-útiles)
- [🧑‍💻 Autor](#autor)

---

## 📚 ¿Qué es Spring Boot?

> *"Spring Boot simplifica la creación de aplicaciones Java independientes y listas para producción."*

**Spring Boot** es un framework para construir aplicaciones backend en Java, que ofrece arranque rápido, autoconfiguración y un ecosistema amplio que facilita la integración con bases de datos, mensajería, seguridad y más.

🔗 Sitio oficial: [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)

---

## 📘 Sobre este repositorio

Este repositorio contiene varios ejemplos funcionales de cómo integrar **Spring Boot 3** con diferentes tecnologías y starters.  
Cada ejemplo se encuentra en una **rama distinta**, estructurado para facilitar el aprendizaje y servir como base para tus propios proyectos.

---

## 🧪 ¿Qué encontrarás en cada rama?

| Disponible | Rama                  | Descripción breve                                                 | Dependencias clave                                    |
|:----------:|-----------------------|-------------------------------------------------------------------|-------------------------------------------------------|
|     ✔️     | `base-spring-project` | Proyecto base usado como plantilla para otros                     |                                                       |
|     ✔️     | `with-rxjava`         | Usando RxJava para manejo de flujos reactivos en Java             | `spring-boot-starter-web`, `spring-boot-starter-json` |
|     ✔️     | `with-rxjava-h2`      | Usando RxJava y base de datos H2 embebido                         | `spring-boot-starter-data-jpa`, `h2`                  |
|     ✔️     | `with-reactor`        | Usando Reactor para manejo de flujos reactivos, nativo de spring. | `spring-kafka`                                        |
|     ✔️     | `with-reactor-h2`     | Usando Reactor y base de datos H2 embebido                        | `spring-boot-starter-security`, `jjwt`                |
|     🕐     | `with-scheduler`      | Tareas programadas con `@Scheduled`                               | `spring-boot-starter`                                 |

---

## 🧩 Lista de dependencias utilizadas

<details>
<summary>Haz clic para desplegar</summary>

- `spring-boot-starter-web` (para exponer APIs REST)
- `spring-boot-starter-data-jpa` (para persistencia con JPA)
- `spring-boot-starter-security` (para manejar autenticación y autorización)
- `spring-boot-starter-validation` (para validación de DTOs)
- `spring-kafka` (para integrar con Apache Kafka)
- `spring-boot-starter-test` (para pruebas unitarias e integración)
- `h2` (base de datos en memoria para desarrollo)
- `jjwt` (para manejo de JWT)
- `lombok` (para reducir boilerplate con anotaciones)
- `spring-boot-starter-actuator` (para exponer métricas y salud del app)

</details>

---

## 📂 Estructura de carpetas

```plaintext
📦 src
┣ 📂 main
┃ ┣ 📂 java
┃ ┃ ┗ 📦 com.example
┃ ┃     ┣ 📦 config
┃ ┃     ┣ 📦 controller
┃ ┃     ┣ 📦 service
┃ ┃     ┣ 📦 model
┃ ┃     ┣ 📦 repository
┃ ┣ 📂 resources
┃ ┃ ┣ 📄 application.properties
┣ 📂 test
┃ ┗ 📂 java
┃     ┗ 📦 com.example
┃         ┣ 📦 controller
┃         ┣ 📦 service
┃         ┣ 📦 repository
```

---

## 🛠️ Cómo usar este repositorio

```bash
# Clona el repositorio
git clone https://github.com/JulioPuma/crud-service-spring-boot.git

# Cambia a la rama de interés
git checkout with-rest

# Ejecuta el proyecto
./mvnw spring-boot:run
```

> ☝️ Asegúrate de tener Java 21 y Maven instalados, o usa el wrapper (`./mvnw`).

---

## 📎 Recursos útiles

- [📘 Documentación oficial de Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [🧰 Spring Initializr](https://start.spring.io/)
- [🔍 Spring Guides](https://spring.io/guides)
- [📖 Documentación Spring Security](https://docs.spring.io/spring-security/reference/index.html)
- [🐘 Spring Data JPA](https://spring.io/projects/spring-data-jpa)

---

## 🧑‍💻 Autor

**Julio Pumahuacre**  
Desarrollador Backend – Java & Microservicios  
[LinkedIn](https://www.linkedin.com/in/juliopuma/)

---

## ⭐ ¿Te sirvió este proyecto?

¡No olvides dejar una estrella ⭐ en el repositorio si te resultó útil!
