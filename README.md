# ARSW - Conexión con ChatGPT mediante API  y validación de Prompt
**Autor:** Nicolás Prieto Vargas  

---

Este proyecto es una aplicación en Spring Boot que permite enviar preguntas a la API de ChatGPT solo si la pregunta tiene sentido o relevancia. Filtra saludos y frases simples, y solo hace la llamada a OpenAI si el mensaje pasa la validación.

---

## 🔹 Conexión con validación

## 🚀 Flujo de la Aplicación

1. **El cliente (Postman, frontend, etc.) hace un POST al endpoint `/chat`** con un JSON que contiene el prompt.
2. **El controlador (`ChatController`) recibe el mensaje** y lo envía al validador.
3. **El validador (`PromptValidatorAdapter`) revisa si el prompt es una frase vacía o un saludo genérico**.
4. Si **el prompt no es válido**, se devuelve un mensaje fijo:  
   `Por favor, escribe una pregunta más específica o relevante.`
5. Si **el prompt es válido**, se envía a OpenAI mediante el adaptador `ChatGptAdapter`.
6. **El adaptador hace una llamada HTTP a la API de ChatGPT**, pasando el prompt como contenido.
7. **Se procesa la respuesta de OpenAI** y se devuelve al cliente.

---

## 🧠 Descripción de Componentes

### ChatGptApplication
- Clase principal con `main()`, arranca toda la aplicación Spring Boot.

### ChatController
- Controlador REST que expone el endpoint `/chat`.
- Recibe un mapa con el `prompt`.
- Usa el `PromptValidatorAdapter` para decidir si se continúa.
- Si es válido, delega la llamada a `ChatGptAdapter`.

### PromptValidatorAdapter
- Componente que contiene una lista de frases "vacías" o simples como "hola", "buenos días", etc.
- Verifica si el `prompt` está vacío o contiene solo esas frases.
- Si el mensaje **pasa**, permite continuar la ejecución.
- Si **no pasa**, devuelve un mensaje preventivo sin llamar a la API.


### IAiAdapter
- Interfaz simple con un solo método: `generateResponse(String input)`.
- Permite desacoplar la lógica de validación de la lógica de consumo de API.


### ChatGptAdapter
- Implementa `IAiAdapter`.
- Usa `HttpClient` de Java para construir una solicitud HTTP hacia la API de OpenAI.
- Construye un cuerpo en formato JSON con el modelo y los mensajes.
- Envía la solicitud con el token (`apiToken`) y endpoint configurado en `application.yml`.
- Procesa la respuesta:
  - Si hay error en la respuesta, lo informa.
  - Si no hay errores, extrae el contenido generado por ChatGPT y lo devuelve.

---

## 📷 Capturas de pantalla

### ❌ Pregunta no válida

![image](https://github.com/user-attachments/assets/0c0886d5-94b7-48e5-917d-4183cad0fb6b)

### ✅ Pregunta válida

![image](https://github.com/user-attachments/assets/5fb32325-da40-41f4-808c-428a62f5b2f3)


