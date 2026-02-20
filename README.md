# 🏗️ Arquitetura da API

A API foi estruturada utilizando uma arquitetura em camadas, com separação clara de responsabilidades:

## 🔹 Controller
 Responsável por expor os endpoints da API e tratar requisições e respostas HTTP.

## 🔹 DTO (Data Transfer Object)
 Controlar os dados que entram e saem da API
 Evitar exposição direta das entidades
 Garantir maior segurança e desacoplamento entre camadas

## 🔹 Service
 Camada responsável pelas regras de negócio e orquestração das operações do sistema.

## 🔹 Repository
 Responsável pela comunicação com o banco de dados.

## 🔹 Entity
 Representa o modelo de dados persistido no banco.

# 🎯 Justificativa da Arquitetura

Essa abordagem foi escolhida porque:

 🔹 Promove **baixo acoplamento**
 🔹 Melhora a **organização e legibilidade do código**
 🔹 Segue boas práticas

# 📦 Banco de Dados

O banco de dados utilizado no projeto é o **MySQL**, e o script de criação das tabelas está disponível no arquivo **`schema.sql`**, localizado na raiz do projeto.

## 🔹 Por que MySQL?

A escolha do MySQL foi feita considerando:
 - ✅ Fácil configuração e execução em ambiente local  
 - ✅ Compatibilidade total com Spring Boot e JPA/Hibernate  

## 🔹 Por que utilizar o `schema.sql`?

A estrutura do banco foi definida manualmente por meio do arquivo **`schema.sql`** para manter a estrutura versionada junto ao código e facilitar a avaliação e execução do projeto.

# 📑 Documentação da API (Swagger)

A API conta com documentação interativa gerada por meio do Swagger (OpenAPI).

O Swagger permite:

 📌 Visualizar todos os endpoints disponíveis  
 📌 Testar requisições diretamente pelo navegador  
 📌 Consultar modelos de request e response  
 📌 Facilitar a integração com outros sistemas  
