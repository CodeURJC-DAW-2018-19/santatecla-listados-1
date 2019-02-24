# santatecla-listados-1
## PHASE 0

### APP Name
AULA VIRTUAL - MOODDLE

### Group members

| Name and Surname     |Mail		   | Github        |
|:--------------------:|:-----------:|:--------------:|
| Hector Mediero de la Morena| h.mediero.2016@alumnos.urjc.es | HectorM15|
| Ana Álvarez Ortiz| a.alvarezo.2016@alumnos.urjc.es   | ana11alvarez   |
| Aitana Cerezo Camino  | a.cerezoc.2016@alumnos.urjc.es|Aitanaceca    |
| Sandra Cañadas Gómez | s.canadas.2016@alumnos.urjc.es    |   sandra1012     |
| Ernesto Baltasar Nve Obono | eb.nve@alumnos.urjc.es    |   nveobono     |

### Auxiliary tools
#### TRELLO
* To organize the tasks and assign them we have used the trello tool:
 [Trello grupo 9](https://trello.com/b/kwE4oDnN/grupo10-daw).
* For the development of the application we have used : WEBSTROM
* The requirements document: https://docs.google.com/document/d/1eq-zIDgxicw_UoEpP-LEa3dN-9cM59FqMHtBGM7VUPw/edit?usp=sharing


### Bibliography

1. [Github](https://github.com)

2. [Best practices GitHub](https://github.com/trein/dev-best-practices/wiki/Git-Commit-Best-Practices)

3. [Good commit messages](https://github.com/erlang/otp/wiki/writing-good-commit-messages)

4. [Trello](https://trello.com)

5. [New tolls,forums and features Github](https://github.com/blog/2256-a-whole-new-github-universe-announcing-new-tools-forums-and-features)

## PHASE 1
### Screenshots

Login: It is a view, where the user's login is required. There are three login options: through Facebook, twitter or Google, by using email and password or in guest / visitor mode. Depending on the type of user, it takes us to the corresponding main view type.

![login](https://user-images.githubusercontent.com/43374601/52230735-7398d480-28b8-11e9-8590-f9adfdcc4a20.png)

Teacher Started View: In this view, the topics and concepts appear, and the option to delete or add them, which only appear if you have logged in as a teacher. There is also a button to see a diagram with progress, another to return to start and another to close the session. We also add the option to search, to access a topic or concept faster.

![started view teacher](https://user-images.githubusercontent.com/43374601/52230739-74316b00-28b8-11e9-88d6-fd4172591725.jpeg)

Student / Visitor Started View: In this view, we only have the reading mode of the topics and concepts because the session has been logged as a student or visitor. In the started view of the student or visitor mode, we also find the start button, the diagrams button, the log out button and the search option.

![started view student](https://user-images.githubusercontent.com/43374601/52230738-74316b00-28b8-11e9-973d-a311afc0213f.jpeg)

Teacher Concept View: In this view we have two tables, one with already corrected items, where the correct or incorrect option is already checked and there is also the option to delete. In the second table we have uncorrected answers where there are two options per answer to mark if it is correct or incorrect. Below the first table, we have a button to add items, where a pop up appears. And in the upper right corner we have the option of log out.

![concept teacher](https://user-images.githubusercontent.com/43374601/52182541-76cf8a00-27fe-11e9-8787-7a096d00e4ce.png)
![concept teacher 2](https://user-images.githubusercontent.com/43374601/52182542-7a631100-27fe-11e9-8198-3d5305f27f13.png)

Student / Visitor Concept View: In this view we have a table with two parts: corrected questions and questions to be corrected. The corrected questions appear with a tick or with a cross depending on whether the question is correct or not. Below is an icon of a sum, to add an item, and the button of diagrams, where we have used a modal to have a pop up where we can see the progress once we have data. Finally, in the upper right corner, we have the option of log out.

![concept student](https://user-images.githubusercontent.com/43374601/52182546-7cc56b00-27fe-11e9-9dbd-a3468ad1c9f1.png)

Questions: It is a view to add questions, where in each type of question there is a generate button to create the dialogue of the question of that type.

![questions](https://user-images.githubusercontent.com/43374601/52182549-7fc05b80-27fe-11e9-9697-4bb71a6574d5.png)

## Navigation diagrams

![diagrama de navegacion](https://user-images.githubusercontent.com/43374601/52230742-772c5b80-28b8-11e9-94e1-6429b913092a.jpeg)

## Bibliography
1. [Página principal]( https://www.creative-tim.com/product/material-dashboard)
2. [Demo](https://demos.creative-tim.com/material-dashboard/examples/dashboard.html)
3. [Repositorio GitHub para descargar](https://github.com/creativetimofficial/material-dashboard)
4. [Documentación:](https://demos.creative-tim.com/material-dashboard/docs/2.1/components/breadcrumb.html)
5. [Echarts](https://ecomfe.github.io/echarts-doc/public/en/index.html)

## PHASE 2
### Navigation diagrams
Se actualizarán las capturas de pantalla de las páginas principales de la aplicación. En caso de que haya cambiado la navegación, se deberá actualizar el diagrama de navegación.
### Development environment
To begin with we had to decide what environment we wanted to use.
In our case it was INTELLIJ.
The configuration of the environment was not very laborious, since it has a spring initializer, which creates the project with the dependencies that you mark (mustache, security ....).
Once this was done we decided that the first step was to connect to the database, download MYSQL Workbench to put it to work and MYSQLServer to manage a server on localhost.
The sentences introduced in our case can be found in the aplication.properties:

spring.jpa.hibernate.ddl-auto = create-drop
spring.datasource.url = jdbc: mysql: // localhost: 3306 / daw_group9? useSSL = false & useLegacyDatetimeCode = false & serverTimezone = UTC
spring.datasource.username = Group9
spring.datasource.password = URJCDAWGrupo9
server.port = 8443
server.ssl.key-store = classpath: keystore.jks
server.ssl.key-store-password = password
server.ssl.key-password = secret

spring.http.multipart.max-file-size = 10MB
spring.http.multipart.max-request-size = 10MB

Once connected to the environment we had to familiarize ourselves with the tags @ Entity, @ ManyToOne .... And so we finally managed to create a DB according to our requirements.
### Entity diagram
![diagrama de clases bbdd](https://user-images.githubusercontent.com/46897050/53304966-bdebf080-387b-11e9-8026-2ebd933f7413.jpg)

The database is composed of the following entities:Answer, Concept, Question, Item, User and Lesson.  Each of the entities has a series of field, as we can see in the photo

Association relations:
- Answer with Question

Composition relations:
- Lesson with Concept
- User with Answer
- Concept with Question
- Concept with Item

### Class diagram and templates
 Se creará un diagrama de clases de la aplicación. No se incluirán ni atributos ni métodos en las clases. Se mostrarán las relaciones entre las clases (asociación, composición o herencia) y se diferenciará claramente qué clases son @Controller, @Service, Repository u otro tipo de clases. Para ello se puede usar un código de colores, una distribución de las clases por partes u otro mecanismo. En este diagrama también se incluirán los ficheros que contienen los templates y se indicará con qué @Controller se relacionan.
### Bibliography

## PHASE 3
### API REST Documentation
 La documentación pública de cada uno de los endpoints de la API REST. Por cada tipo de recurso se deberá indicar el formato de la URL, las operaciones soportadas, el formato de la información de entrada y de salida, los códigos de estado, etc. Para los formatos de entrada (en el body) y salida se usarán ejemplos de los datos de las peticiones en JSON.
### Class diagram updates
Se actualizará el diagrama de clases y templates incluyendo las nuevas clases @RestController. Se prestará especial atención a que los servicios compartidos entre los @Controller y los @RestController aparezcan correctamente reflejados en el diagrama.

### Dockerized application execution instructions
Instrucciones de ejecución usando el docker-compose.yml.

### Preparation of development environment
Se indicarán cómo instalar y configurar el entorno de desarrollo para poder compilar y ejecutar la aplicación dockerizada.

### Bibliography

## PHASE 4
### Preparation of development environment
 Se añadirá a las instrucciones cómo instalar y configurar el entorno de desarrollo para poder compilar y ejecutar la aplicación SPA con Angular.
### Class diagram and templates
 Reflejará las clases y templates del código Angular. El diagrama deberá diferenciar claramente qué elementos son componentes y cómo se relacionan entre sí (relaciones padre-hijo). También se deberán incluir los servicios y el resto de clases auxiliares.
### Bibliography
