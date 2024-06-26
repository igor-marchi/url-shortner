# Projeto Encurtador de URL

Este projeto é uma aplicação Spring Boot que fornece uma API REST para criar e redirecionar URLs encurtadas. As URLs são armazenadas em um banco de dados MongoDB.

## Pré-requisitos

- Java 21
- Maven
- Docker
- Git
- MongoDB

## Clonando o projeto

Para clonar o projeto, execute o seguinte comando no terminal:

```bash
git clone https://github.com/igor-marchi/url-shortner.git
```

## Subindo o banco de dados (MongoDB)

Para subir o banco de dados, navegue até a pasta docker no diretório do projeto e execute o seguinte comando:

```bash
cd docker
docker-compose up
```

## Sobre o MongoDB

MongoDB é um banco de dados NoSQL orientado a documentos. Isso significa que, em vez de armazenar dados em tabelas como é feito em um banco de dados relacional tradicional, o MongoDB armazena dados em documentos BSON, que são uma representação binária de JSON. Isso torna o MongoDB extremamente flexível e adaptável a muitas situações de uso.

No contexto deste projeto, usamos o MongoDB para armazenar as URLs que são encurtadas. Cada URL é armazenada como um documento separado no MongoDB. Quando uma URL é encurtada, um novo documento é criado no MongoDB com a URL original e o ID encurtado. Quando uma URL encurtada é acessada, o serviço consulta o MongoDB pelo ID encurtado para encontrar a URL original.

Para executar o MongoDB localmente para desenvolvimento, usamos o Docker. O Docker permite que você execute o MongoDB em um contêiner isolado, o que facilita a configuração e a limpeza. As instruções para iniciar o MongoDB com Docker estão incluídas na seção "Subindo o banco de dados (MongoDB)" deste README.

Para mais informações sobre o MongoDB, você pode visitar a [documentação oficial do MongoDB](https://docs.mongodb.com/).

## Executando o projeto

Para executar o projeto, navegue até o diretório do projeto e execute o seguinte comando:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## Endpoints

- `POST /shorten-url`: Cria uma URL encurtada.
- `GET /{id}`: Redireciona para a URL original.

Para mais detalhes sobre os endpoints, consulte os arquivos `UrlController.java`.

## Arquivo request.http

O arquivo `request.http` é um arquivo de teste de API que contém exemplos de solicitações HTTP que podem ser feitas para a API do projeto. Ele é útil para testar rapidamente as funcionalidades da API sem a necessidade de um cliente externo, como o Postman.

Aqui estão as solicitações HTTP presentes no arquivo:

1. **Criar URL Encurtada**

   Este é um exemplo de uma solicitação POST para criar uma URL encurtada. A URL da solicitação é `http://localhost:8080/shorten-url`.

   ```http

   ### Create Shortened URL

   POST http://localhost:8080/shorten-url
   Content-Type: application/json

   {
   "url": "https://www.example.com"
   }
   ```

2. **Redirecionar para URL Original**

   Este é um exemplo de uma solicitação GET para redirecionar para a URL original. A URL da solicitação é `http://localhost:8080/{id}`. O `{id}` é um placeholder que deve ser substituído pelo id da URL encurtada que você deseja redirecionar.

   ```http

   ### Redirect to Original URL

   GET http://localhost:8080/{id}
   ```

Para executar essas solicitações no Visual Studio Code, você pode instalar a extensão "REST Client" e abrir o arquivo `request.http`. Clique no link "Send Request" que aparece acima de cada solicitação.

<p align="center">Feito com amor por <a href="https://www.linkedin.com/in/igor-marchi/">Igor Marchi</a> ❤️</p>
