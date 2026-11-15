
# Sistema de Gestion de Citas Odontologicas


Este proyecto consiste en una aplicacion de consola desarrollada en Java para gestionar las citas de una clinica odontologica. Permite registrar pacientes, odontologos y citas, así como consultar, modificar, cancelar y eliminar citas.

El sistema utiliza diferentes estructuras de datos de Java Collections Framework para organizar la informacion y facilitar las operaciones de busqueda, ordenamiento y atencion de citas segun su prioridad.


## Tecnologias Utilizadas

- Java 21
- Apache Maven
- Java Collections Framework
- Visual Studio Code

## Estructuras de datos utilzadas


- **ArrayList**: Almacenar el listado general de citas.

- **HashSet**: Evitar especialidades duplicadas.

- **HashMap**: Buscar pacientes, odontologos y citas mediante sus identificadores.

- **TreeMap**: Organizar las citas cronologicamente segun su fecha y hora.

- **PriorityQueue**: Gestionar las citas pendientes según su nivel de prioridad.

Tambien se utilizan Comparator, equals() y hashCode() para establecer criterios de ordenamiento e identificacion de objetos.

## Funcionalidades

- Registrar pacientes y odontologos.

- Registrar citas odontologicas.

- Consultar citas por su codigo.

- Listar todas las citas registradas.

- Consultar las especialidades disponibles.

- Listar citas ordenadas cronologicamente.

- Consultar y atender citas segun su prioridad.

- Modificar, cancelar y eliminar citas.

- Consultar estadisticas generales del sistema.

- Validar datos y evitar registros duplicados.
## Ejecucion del proyecto

Clonar el repositorio

```bash
  git clone https://github.com/BPEINADOM/GestionCitasOdontologicas_ED.git
```

Entrar en el directorio del proyecto

```bash
  cd GestionCitasOdontologicas_ED/gestionodontologica
```

Compilar el proyecto

```bash
  mvn clean compile
```

Ejecutar la clase principal Main.java para ejecutar el menu del sistema

```bash
  javac Main.java
  java Main
```

