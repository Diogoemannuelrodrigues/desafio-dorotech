# Product Application

Este é um projeto de exemplo de uma aplicação CRUD (Create, Read, Update, Delete) utilizando Java 17 e Spring Boot.

## Requisitos

- Java 17
- Maven 3.6.3 ou superior

## Tecnologias Utilizadas

- Spring Boot 3.2.5
- MongoDB
- AMQP (Advanced Message Queuing Protocol)
- Lombok
- Docker

## Installation

1 - Clone o repositório:
```bash
git clone https://github.com/Diogoemannuelrodrigues/desafio-dorotech.git
```
2 - Navegue até o diretório do projeto:
```bash
cd seu-repositorio
```
3 - Compile o projeto utilizando Maven:
```bash
mvn clean install
```

## Executando a Aplicação
Para iniciar a aplicação, execute o seguinte comando:

```bash
mvn spring-boot:run
```
A aplicação estará disponível em http://localhost:8080.



## Endpoints
Aqui estão alguns dos principais endpoints da aplicação:

- GET /products - Retorna todos os produtos
- GET /products/{id} - Retorna um produto específico por ID
- POST /products - Cria um novo produto
- PUT /products/{id} - Atualiza um produto existente
- DELETE /products/{id} - Exclui um produto

## Contributing

- Faça um fork do projeto
- Crie uma branch para sua feature (git checkout -b feature/fooBar)
- Faça o commit das suas alterações (git commit -m 'Add some fooBar')
- Faça o push para a branch (git push origin feature/fooBar)
- Abra um Pull Request
