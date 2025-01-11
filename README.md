# 💵 Desafio Backend - PicPay  

Este é um projeto desenvolvido como desafio de backend, inspirado no funcionamento do **PicPay**. Ele inclui funcionalidades de gerenciamento de usuários, transações financeiras, notificações por e-mail e distinção de permissões.  

---

## 📋 Funcionalidades  

- **Usuários**:
  - **Usuário Comum**: Pode realizar transações e receber pagamentos.
  - **Usuário Comercial**: Pode apenas receber pagamentos.
  - A distinção entre os tipos de usuário foi implementada utilizando **Spring Security** com configuração de roles.

- **Transações**:
  - Os usuários podem realizar transações financeiras com reversão suportada.
  - Todas as transações são registradas no banco de dados.

- **Notificações**:
  - Sistema de notificações por e-mail implementado utilizando **SMTP** para informar os usuários sobre status de transações.

- **Persistência de Dados**:
  - Banco de dados configurado e gerenciado com **Docker** para fácil configuração e isolamento do ambiente.

---

## 🛠️ Tecnologias Utilizadas  

- **Java 17**
- **Spring Boot**  
  - Spring Security (Autenticação e autorização)  
  - Spring Data JPA (Persistência)  
- **Banco de Dados**: PostgreSQL (gerenciado com Docker)  
- **SMTP**: Envio de notificações por e-mail  
- **Docker**: Containerização do banco de dados  
