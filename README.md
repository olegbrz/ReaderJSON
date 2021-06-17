# Práctica: patrón cadena de mando aplicado a lectura de JSON
  
*Oleg Brezitskyy*

*Ingeniería del Software Avanzada*

*3º Ingeniería de la Salud*

## Guión

En una aplicación se usa un archivo en formato Json (https://www.json.org/json-en.html) en el que están indicados los datos que tienen que estar inicialmente en la base de datos de la aplicación cuando arranca.

Los datos que se guardaban en la versión inicial eran identificadores de los medicamentos, de ingredientes activos, de inhaladores y de las diferentes posologías, así como la información necesaria sobre las presentaciones de los distintos medicamentos (que incluyen referencias al medicamento, al principio activo, al inhalador, la dosis y la posología) y sobre los pasos de los manuales de usuario de los inhaladores (que incluyen referencias al inhalador al que corresponden, el título, el texto y la imagen que se mostrarán).

En el nivel superior del archivo Json tenemos un objeto (en el sentido Json) que contiene cada una de las categorías de la información almacenada. Cada categoría tiene su propio nombre ("medicines", "activeIngredients", ...) y una lista (en el sentido Json) con los valores correspondientes a esa categoría. Cada uno de los valores se almacena como un objeto (en el sentido Json) con una entrada por cada campo del elemento de esa categoría. Por ejemplo, para los objetos de la categoría "medicines", habrá una única entrada con el nombre "name" y para las objetos de la categoría "rescueMedicinePresentations" habrá cuatro entradas, con los nombres, respectivamente, "medicineRef", "activeIngredientRef", "inhalerRef", "dose".

Para leer el contenido del archivo Json usaremos la biblioteca Gson (https://github.com/google/gson) y la clase JsonReader. En el enlace anterior hay una guía de usuario, pero no cubre la parte de JsonReader.

La biblioteca se puede usar a través de Maven, copiando en la sección de dependencias del archivo pom.xml la dependencia que hay en la página https://search.maven.org/artifact/com.google.code.gson/gson/2.8.6/jar. También se puede usar en un proyecto Java normal copiando el archivo gson-2.8.6.jar de la página https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.6/ o de https://search.maven.org/artifact/com.google.code.gson/gson/2.8.6/jar y añadiéndolo al Build Path del proyecto (también podéis añadir al proyecto el archivo gson-2.8.6-javadoc.jar para tener acceso a la documentación).

En la clase JsonReader están disponibles los métodos beginObject(), endObject(), beginArray(), endArray(), hasNext(), nextName(), nextString(), etc., que permiten leer el contenido del archivo (ver ejemplo en la página de la referencia de Jsonreader).

## Estructura JSON

```json
{
    "medicines": [{"name": ""}],
    "activeIngredients": [],
    "physiotherapies": [],
    "inhalers": [],
    "posologies": [{"description":  ""}],
    "medicinePresentations": [],
    "rescueMedicinePresentations": [],
    "userManualPhysioSteps": [],
    "userManualSteps": []
}
```

## Patrones solución

- [x] Compuesto
- [x] **Cadena de mando**

## Memoria

Para preservar ambas versiones del proyecto, se crearon dos paquetes:

- `initial`: contiene las clases `DatabaseJSonReader` y `GsonDatabaseClient` originales.
- `solution`: contiene las clases anteriores adaptadas
    - `readers`: contiene la implementación con el patrón cadena de mando de las clases lectoras de "medicines" y "rescueMedicinePresentations", así como la clase `ChainElement` de la que heredan.

La estructura del código es similar a la original, solo que se separa en diferentes readers, uno para cada key en el diccionario. Los métodos de las clases originales fueron importados a las clases herederas de `ChainElement`.

La clase `ChainElement` contiene el campo `allowedKey`, la cual especifica si ese reader específico es capaz de leer el valor del diccionario propuesto o no. Para comprobar eso, se creó el método `canRead()`. El campo `successor` se refiere al siguiente elemento en la cadena (se proporciona también un setter para este campo).

Toda esa estructura se envuelve en el método `handleRead()`, el cual, dado un `JSONReader` y una `String` que especifica la key del diccionario a leer, puede tener 3 comportamientos diferentes:

1. Si el reader puede leer el valor, se devuelve el `StringBuffer` de la función `read()` del reader correspondiente.
2. Si el reader no puede leer y hay un sucesor (siguiente elemento en la cadena), le pasa esos valores por la función `hanldeRead()`.
3. En caso contrario, devuelve null, que se comprueba en la clase cliente, y se ignora.

Para crear una cadena de mando, simplemente se instancia un reader específico y se le pasa un `null` en el constructor, indicando que no tiene sucesor, los elementos siguientes se crean encadenando los readers anteriores como parámetros en sus constructores. Para aplicar la cadena de mando, se usa el último elemento añadido, ya que este sería el primero.

Para demostrar el funcionamiento y abstraer un poco el concepto de cadena, se creó la clase `ChainOfResponsability`, que es una versión simplificada de `ChainElement`, y solo contiene el campo que guarda el primer elemento en la cadena, y la función "iniciadora" de la cadena.

**Cabe mencionar que solamente se han implementado dos readers, los mismos que en la implementación original.**

**La dos clases cliente (`initial` y `solution`) se ejecutaron para comprobar el funcionamiento, y sus salidas fueron comprobados con el `output diff` del IDE IntelliJ, las salidas eran idénticas independientemente del orden de la cadena, lo que indica que la implementación seguramente es correcta.**

Para más comprensión se recomienda ver el diagrama UML a continuación.

## UML

![uml](/resources/uml.png)