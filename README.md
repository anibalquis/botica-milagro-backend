# Botica Milagro Backend

**API backend desarrollada para la gestión integral de una botica**, enfocada en el inventario farmacéutico y la centralización de la lógica de negocio. El sistema expone servicios para la gestión de productos, control de stock, actualización de precios y categorización de medicamentos, garantizando consistencia transaccional y validación de datos.

## 🛠️ Tecnologías Utilizadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/apachemaven-C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)

## 🚀 Instalación y Ejecución

1. **Clona el repositorio:**

```bash
git clone https://github.com/anibalquis/botica-milagro-backend.git
```

2. **Navega a la carpeta del proyecto:**

```bash
cd botica-milagro-backend
```

3. **Actualización de configuración en `application.properties`:**

```properties
spring.application.name=botica-milagro

#1. Configuración para conexión a DB
spring.datasource.url=jdbc:mysql://localhost:3306/farm_milagro?createDatabaseIfNotExist=true
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

#2. Si no existe la BD que lo cree o Actualice
spring.jpa.hibernate.ddl-auto=update

#3. Ver sentencias "insert, selecta, ..."
spring.jpa.show-sql=true
```

> [!IMPORTANT]
> **Si su puerto de MySQL no es 3310, debe modificarlo en la URL de conexión.**

4. **Actualización de la clase principal en `farmMilagroApp`:**

```java
@SpringBootApplication
public class farmMilagroApp {
    public static void main(String[] args) {
        SpringApplication.run(farmMilagroApp.class, args);
    }
}
```