# Estoque API

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-4169E1?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?logo=docker&logoColor=white)

API REST para gerenciamento de produtos e movimentações de estoque, com autenticação JWT, validação de dados, tratamento global de erros e ambiente completo em Docker.

## Funcionalidades

- Cadastro e autenticação de usuários
- Proteção de rotas com JWT
- Cadastro, consulta, atualização e exclusão de produtos
- Entrada e saída de estoque
- Validação de dados recebidos
- Tratamento padronizado de exceções
- Healthcheck com Spring Boot Actuator
- Persistência em PostgreSQL
- Inicialização completa com Docker Compose

## Tecnologias

- Java 21
- Spring Boot 4.1
- Spring Web MVC
- Spring Data JPA e Hibernate
- Spring Security
- Auth0 Java JWT
- PostgreSQL 17
- Maven
- Docker e Docker Compose
- Spring Boot Actuator

## Executar com Docker

### Pré-requisitos

- Docker Desktop instalado e em execução
- Portas `8080` e `5433` disponíveis

Clone o repositório, entre na pasta e execute:

```bash
docker compose up
```

O comando constrói a imagem da API, cria o banco e inicia todo o ambiente automaticamente.

Na primeira execução com o banco vazio, a API também cadastra oito produtos de demonstração. A carga não é repetida quando já existem produtos.

Serviços disponíveis:

| Serviço | Endereço |
|---|---|
| API | `http://localhost:8080` |
| Healthcheck | `http://localhost:8080/actuator/health` |
| PostgreSQL | `localhost:5433` |

Para encerrar:

```bash
docker compose down
```

O volume do PostgreSQL é preservado entre as execuções.

## Autenticação

### 1. Cadastrar usuário

```http
POST /auth/cadastro
Content-Type: application/json
```

```json
{
  "username": "helton",
  "senha": "12345678"
}
```

### 2. Fazer login

```http
POST /auth/login
Content-Type: application/json
```

```json
{
  "username": "helton",
  "senha": "12345678"
}
```

A resposta contém o token:

```json
{
  "token": "eyJ..."
}
```

Use o token nas rotas protegidas:

```http
Authorization: Bearer SEU_TOKEN
```

## Endpoints

### Autenticação

| Método | Rota | Descrição | Acesso |
|---|---|---|---|
| `POST` | `/auth/cadastro` | Cadastra um usuário | Público |
| `POST` | `/auth/login` | Autentica e gera um JWT | Público |

### Produtos

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/produtos` | Lista todos os produtos |
| `GET` | `/produtos/{id}` | Consulta um produto |
| `POST` | `/produtos` | Cadastra um produto |
| `PUT` | `/produtos/{id}` | Atualiza um produto |
| `PATCH` | `/produtos/{id}/entrada` | Adiciona unidades ao estoque |
| `PATCH` | `/produtos/{id}/saida` | Retira unidades do estoque |
| `DELETE` | `/produtos/{id}` | Exclui um produto |

Todas as rotas de produtos exigem autenticação.

### Exemplo de produto

```json
{
  "nome": "Teclado mecânico",
  "quantidade": 10,
  "preco": 249.90
}
```

### Exemplo de movimentação

```json
{
  "quantidade": 5
}
```

## Executar localmente

Configure as variáveis necessárias e execute:

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/estoque"
$env:DB_USERNAME="estoque_app"
$env:DB_PASSWORD="sua_senha"
$env:JWT_SECRET="sua-chave-jwt-grande-e-segura"
.\mvnw.cmd spring-boot:run
```

## Variáveis de ambiente

| Variável | Finalidade |
|---|---|
| `DB_URL` | URL JDBC do PostgreSQL |
| `DB_USERNAME` | Usuário do banco |
| `DB_PASSWORD` | Senha do banco |
| `JWT_SECRET` | Chave usada para assinar os tokens |

Os valores presentes no Compose são destinados somente ao desenvolvimento. Use segredos próprios em produção. O arquivo `.env` é ignorado pelo Git.

## Estrutura

```text
src/main/java/br/com/helton/estoque
├── controller   # Endpoints REST
├── dto          # Objetos de entrada e saída
├── entity       # Entidades JPA
├── exception    # Exceções e respostas de erro
├── repository   # Acesso ao banco
├── security     # JWT e Spring Security
└── service      # Regras de negócio
```

## Build

```powershell
.\mvnw.cmd clean package
```

O arquivo executável será gerado em `target/estoque-api-0.0.1-SNAPSHOT.jar`.

## Autor

Desenvolvido por [Helton França](https://github.com/HeltonFranca).
