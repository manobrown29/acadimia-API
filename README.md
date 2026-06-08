# 🏋️ Academia API — Gestão de Alunos

API RESTful para gerenciamento de alunos de academias, desenvolvida com **Java** e **Spring Boot**.

---

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias](#tecnologias)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Como Usar](#como-usar)
- [Endpoints](#endpoints)
- [Exemplos de Requisição](#exemplos-de-requisição)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

---

## Sobre o Projeto

A **Academia API** é uma aplicação back-end desenvolvida com Spring Boot para facilitar o gerenciamento de alunos de academias. Com ela é possível cadastrar alunos, consultar dados, atualizar informações, verificar status de matrícula e remover registros — tudo via requisições HTTP.

### Funcionalidades

- Listagem de todos os alunos
- Busca de aluno por ID
- Cadastro de novo aluno
- Atualização de dados (nome, CPF, data de nascimento, plano e matrícula)
- Remoção de aluno
- Consulta do status de matrícula do aluno

---

## Tecnologias

| Tecnologia | Descrição |
|---|---|
| Java 17+ | Linguagem principal |
| Spring Boot | Framework web |
| Spring Data JPA | Persistência e repositórios |
| Maven | Gerenciamento de dependências |
| Banco de dados relacional | MySQL / PostgreSQL |

---

## Pré-requisitos

Antes de começar, você precisará ter instalado:

- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/) ou [PostgreSQL](https://www.postgresql.org/)
- [Git](https://git-scm.com/)

---

## Instalação

```bash
# Clone o repositório
git clone https://github.com/manobrown29/academia-api.git

# Entre na pasta do projeto
cd academia-api

# Compile e instale as dependências
mvn install
```

---

## Configuração

Edite o arquivo `src/main/resources/application.properties` com as configurações do seu banco:

```properties
# Banco de dados
spring.datasource.url=jdbc:mysql://localhost:3306/academia_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Servidor
server.port=8080
```



---

## Endpoints

Todos os endpoints estão sob o prefixo `/alunos`.

| Método | Rota | Descrição | Status de retorno |
|---|---|---|---|
| `GET` | `/alunos` | Listar todos os alunos | `200 OK` |
| `GET` | `/alunos/{id}` | Buscar aluno por ID | `200 OK` / `404 Not Found` |
| `POST` | `/alunos` | Cadastrar novo aluno | `201 Created` |
| `PUT` | `/alunos/{id}` | Atualizar dados do aluno | `200 OK` / `404 Not Found` |
| `DELETE` | `/alunos/{id}` | Remover aluno | `204 No Content` / `404 Not Found` |
| `GET` | `/alunos/{id}/matricula` | Consultar status de matrícula | `200 OK` / `404 Not Found` |

---

## Exemplos de Requisição

### Listar todos os alunos

```http
GET /alunos
```

**Resposta `200 OK`:**

```json
[
  {
    "id": 1,
    "nome": "João da Silva",
    "cpf": "123.456.789-00",
    "dtNascimento": "1995-04-20",
    "plano": "Mensal",
    "matriculaAtiva": true
  }
]
```

---

### Buscar aluno por ID

```http
GET /alunos/1
```

**Resposta `200 OK`:**

```json
{
  "id": 1,
  "nome": "João da Silva",
  "cpf": "123.456.789-00",
  "dtNascimento": "1995-04-20",
  "plano": "Mensal",
  "matriculaAtiva": true
}
```

**Resposta `404 Not Found`** — quando o aluno não existe.

---

### Cadastrar aluno

```http
POST /alunos
Content-Type: application/json

{
  "nome": "Maria Oliveira",
  "cpf": "987.654.321-00",
  "dtNascimento": "2000-08-15",
  "plano": "Trimestral",
  "matriculaAtiva": true
}
```

**Resposta `201 Created`:**

```json
{
  "id": 2,
  "nome": "Maria Oliveira",
  "cpf": "987.654.321-00",
  "dtNascimento": "2000-08-15",
  "plano": "Trimestral",
  "matriculaAtiva": true
}
```

---

### Atualizar aluno

```http
PUT /alunos/2
Content-Type: application/json

{
  "nome": "Maria Oliveira Santos",
  "cpf": "987.654.321-00",
  "dtNascimento": "2000-08-15",
  "plano": "Anual",
  "matriculaAtiva": true
}
```

**Resposta `200 OK`** com o objeto atualizado, ou `404 Not Found` se não existir.

---

### Remover aluno

```http
DELETE /alunos/2
```

**Resposta `204 No Content`** — remoção bem-sucedida.  
**Resposta `404 Not Found`** — aluno não encontrado.

---

### Consultar status de matrícula

```http
GET /alunos/1/matricula
```

**Resposta `200 OK`:**

```
Matricula Ativa
```

ou

```
Matricula Não Ativada
```

---

## Estrutura do Projeto

```
academia-api/
├── src/
│   └── main/
│       └── java/com/acadimia/
│           ├── Controller/
│           │   └── AlunoController.java   # Endpoints REST
│           ├── model/
│           │   └── Aluno.java             # Entidade JPA
│           └── Repository/
│               └── AlunoRepository.java   # Interface JPA Repository
├── src/main/resources/
│   └── application.properties            # Configurações da aplicação
└── pom.xml                               # Dependências Maven
```

---

## Contribuindo

Contribuições são bem-vindas! Siga os passos abaixo:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/minha-feature`)
3. Commit suas mudanças (`git commit -m 'feat: adiciona minha feature'`)
4. Push para a branch (`git push origin feature/minha-feature`)
5. Abra um Pull Request

---
