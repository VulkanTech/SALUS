# 🏥 SALUS – Sistema de Agendamento de Consultas Médicas

Aplicação **full stack** para gerenciamento de **médicos, pacientes e consultas**, desenvolvida com **Spring Boot**, **React** e **Docker**.

O projeto utiliza **arquitetura em camadas**, **DTOs**, **API REST documentada com Swagger** e **Docker Compose** para orquestração dos serviços.

---

## ✅ Tecnologias Utilizadas

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Maven
- Hibernate
- PostgreSQL
- Swagger (OpenAPI)

### Frontend
- React
- Vite
- Axios
- Nginx (produção)

### Infraestrutura
- Docker
- Docker Compose

---

## 🧱 Arquitetura da Aplicação
```
┌──────────────────────────┐
│ Frontend │ React + Vite
│ (Nginx) │
│ Porta 3000 │
└───────┬──────────────────┘
│ HTTP (REST)
▼
┌──────────────────────┐
│ Backend │ Spring Boot
│ API REST │
│ Porta 8081 │
└───────┬──────────────┘
│ JPA / Hibernate
▼
┌──────────────┐
│ PostgreSQL │
│ Porta 5432 │
└──────────────┘
```


Cada camada da aplicação roda em um **container independente**, garantindo isolamento, portabilidade e facilidade de deploy.

---

## 📁 Estrutura de Pastas
```
SALUS/
├─ backend/
│ ├─ src/main/java/com/vulkantech/salus
│ │ ├─ controller
│ │ ├─ service
│ │ ├─ repository
│ │ ├─ model
│ │ ├─ dto
│ │ └─ exception
│ ├─ Dockerfile
│ └─ docker-compose.yml
│
├─ frontend/
│ ├─ src/
│ ├─ Dockerfile
│ └─ package.json
│
├─ .gitignore
├─ .dockerignore
└─ README.md
```

---

## 🚀 Como Executar o Projeto (Docker)

### Pré-requisitos
- Docker
- Docker Compose

---

### 1️⃣ Build e execução

Dentro da pasta `backend`:

```bash
docker compose up --build
```
### 2️⃣ Acessos

- Frontend:
👉 http://localhost:3000

- Backend (Swagger):
👉 http://localhost:8081/swagger-ui.html

## 🔎 Funcionalidades
### ✅ Backend

- CRUD de médicos

- CRUD de pacientes

- Agendamento de consultas

- Validação de conflito de horário

- Cancelamento de consultas

- Relatórios por médico, paciente e período

### ✅ Frontend

- Listagem de médicos, pacientes e consultas

- Cadastro via formulários

- Consumo da API REST

- Interface simples e funcional

## 📄 Documentação da API

- A API REST está documentada com Swagger/OpenAPI:
```
http://localhost:8081/swagger-ui.html
```

## 🧠 Decisões de Projeto

- DTOs para evitar exposição direta das entidades

- Separação de responsabilidades (controller, service, repository)

- Relatórios isolados em camada própria

- Docker Compose para simplificar execução e deploy

#
## 👨‍💻Autores
- Projeto desenvolvido por discentes 2º período de Engenharia de Software da Universidade de Pernambuco (UPE) para a disciplina de Programação II da Profª Aêda Sousa.

## Discentes
[@caddu57](https://github.com/caddu57) |
[@Dandebs19](https://github.com/Dandebs19) |
[@felipefeiitosa ](https://github.com/felipefeiitosa) |
[@PauloQueirozz](https://github.com/PauloQueirozz) |
[@Vpantaleao](https://github.com/Vpantaleao)