**Examen Cash**

Tecnologías usadas:

-   Java 8
    
-   SpringBoot (MVC y JPA)
    
-   Hibernate
    
-   Base de Datos MySQL.
    
-   Repositorio Git para la entrega del código (se recomienda Bitbucket o Github)
    

  

Repositorio Git: https://github.com/mauricio-figueroa/backend-cash

  
  

Base URL:[https://pacific-eyrie-20091.herokuapp.com/](https://pacific-eyrie-20091.herokuapp.com/)

  
  
**Apis:**

  

 - USER:

  

**GET- user**

[https://pacific-eyrie-20091.herokuapp.com/](https://pacific-eyrie-20091.herokuapp.com/)users/1

  

**POST -user**

[https://pacific-eyrie-20091.herokuapp.com/](https://pacific-eyrie-20091.herokuapp.com/)users

  

{

"first_name":"Gabriel",

"last_name":"Fernandez",

"email":"gabriel.fernandez@gmail.com"

}

  

**DELETE-user**

  

[https://pacific-eyrie-20091.herokuapp.com/](https://pacific-eyrie-20091.herokuapp.com/)users/12

  
  
  

 - LOANS:

  

[https://pacific-eyrie-20091.herokuapp.com/loans?limit=0&offset=0&user_id=1](https://pacific-eyrie-20091.herokuapp.com/loans?limit=0&offset=0&user_id=1)

  

https://pacific-eyrie-20091.herokuapp.com/loans?limit=0&offset=0

__________________________________________________________________________
Para levantar la aplicacion localmente con una base vacia, descargar el proyecto, importarlo en un ide como maven project, tener instalado una base de datos mysql, cambiar los parametros de conexion a la base (en application.properties) y crear un esquema cash_backend (create schema cash_backend)

spring.datasource.url=jdbc:mysql://localhost:3306/cash_backend  
spring.datasource.username=root  
spring.datasource.password=root


Para cargarla con algunos datos iniciales para probar 

https://pacific-eyrie-20091.herokuapp.com/init-exam
