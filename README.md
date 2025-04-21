# restassured-helper

A **DSL (Domain-Specific Language)** for writing clear and expressive API tests in **Portuguese**, using [Rest Assured](https://rest-assured.io/) and [WireMock](http://wiremock.org/).  
Designed to improve readability and reduce boilerplate in automated tests.

---

## 🚀 Purpose

This library allows developers to write fluent and concise REST API assertions using natural Portuguese-like syntax, ideal for Brazilian teams aiming for readable and collaborative test code.

---

## ✅ Example usage

```java
realizandoPost(paraPath(WHATEVER_URL)
    .comEntrada("whatever"))
    .comStatus(200)
    .contem("preferencias.notificacoes[0]", Matchers.equalTo("email"))
    .contemNaString(noCampo("nome").aDescricao("Ralf Drehmer"))
    .contemIntegerExato(noCampo("idade").oInteiro(29))
    .contemBoolean(noCampo("ativo").oBoolean(true));
