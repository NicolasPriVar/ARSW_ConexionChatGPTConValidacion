# ARSW - Conexión con ChatGPT mediante API  
**Autor:** Nicolás Prieto Vargas  

---

## 📌 Descripción general

Este proyecto permite realizar una conexión entre una aplicación Java de consola y la API de OpenAI (ChatGPT), simulando una conversación entre el usuario y el modelo de lenguaje. Se desarrollaron **dos versiones** del proyecto:

1. **Conexión normal** (conversación directa).
2. **Conexión con validación del prompt** (mejora del mensaje antes de enviarlo).

---

## 🔹 Conexión normal

**Ubicación:** raíz del proyecto.

Este proyecto en Java crea una **aplicación de consola simple** que permite al usuario conversar directamente con ChatGPT usando la API de OpenAI. A través de un ciclo continuo, el usuario escribe preguntas y recibe respuestas generadas por el modelo.  
La conversación continúa hasta que el usuario escribe `"SALIR"`, momento en el cual el programa finaliza.

### 🧱 Estructura:
- `App.java`: gestiona la interacción con el usuario.
- `ChatGptAdapter.java`: realiza la conexión con la API de OpenAI.
- `IAiAdapter.java`: interfaz que permite intercambiar adaptadores de IA.

---

## 🔸 Conexión con validación del prompt

**Ubicación:** carpeta `conexionChatGPTConValidacion/`.

Esta versión permite al usuario escribir un mensaje (prompt), y antes de enviarlo a ChatGPT, el sistema lo evalúa para verificar si tiene sentido. Si no es claro, sugiere una versión mejorada. El usuario puede elegir entre su versión original o la sugerida, y luego se envía el mensaje a ChatGPT, mostrando la respuesta en consola.

### 🧱 Estructura:
- `App.java`: gestiona la interacción con el usuario y el flujo de validación.
- `ChatGptAdapter.java`: realiza la conexión con la API de OpenAI.
- `PromptValidatorAdapter.java`: evalúa y mejora el prompt del usuario.
- `IAiAdapter.java`: interfaz común para adaptadores de IA.

---

## 🧩 Patrón de diseño utilizado: Adapter + Interfaz

Se aplica el patrón de diseño **Adapter** con una interfaz (`IAiAdapter`) para desacoplar la lógica de negocio de la implementación concreta del proveedor de IA.  
Esto permite:
- Reutilizar el código fácilmente con otros servicios (como Gemini o Claude).
- Mejorar la escalabilidad y el mantenimiento.
- Cumplir principios SOLID como **inversión de dependencias**.

Cada implementación del adaptador puede conectarse a un proveedor diferente simplemente implementando la interfaz definida.

---
