# Práctica JSON

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