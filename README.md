# 💰 Projeto: Sistema Bancário com Padrões de Projeto

Este projeto Java simula um sistema bancário simples que permite criar contas, realizar transações e notificar eventos por e-mail fictício. O foco está na correta aplicação de **padrões de projeto** de software para organização, reutilização de código e escalabilidade.

## 🎯 Tema escolhido
Sistema de Gerenciamento de Contas Bancárias.

---

## 🧱 Arquitetura Geral

A aplicação foi dividida em camadas lógicas com responsabilidades bem definidas:

- `accounts/`: Contas bancárias e suas implementações (`CheckingAccountImpl` e `SavingsAccountImpl`)
- `factory_account/`: Fábrica para criação de contas com controle de IDs
- `observer/`: Observadores que reagem a eventos bancários (envio de notificação)
- `my_bank/`: Singleton que representa a instância única do banco
- `Main.java`: Classe principal que simula a execução da aplicação

---

## 🧠 Padrões Aplicados

### 1. **Singleton**
- **Onde**: `MyBank.java` e `AccountFactory.java`
- **Como**: Garante que exista apenas uma instância do banco e da fábrica de contas.
- **Por quê**: Evita inconsistências na manipulação de contas e IDs ao centralizar o controle.

### 2. **Factory Method**
- **Onde**: `AccountFactory.java`
- **Como**: Permite a criação de diferentes tipos de conta (`CHECKING`, `SAVINGS`) de forma encapsulada.
- **Por quê**: Facilita a manutenção e extensão de tipos de conta sem alterar o código do cliente.

### 3. **Observer**
- **Onde**: `SendEmailNotifier.java` e no modelo de contas
- **Como**: Observadores são notificados em eventos de conta (depósitos, transferências, saques).
- **Por quê**: Simula envio de e-mails ao ocorrerem transações relevantes, separando a lógica de notificação da lógica da conta.

---

## 🧩 Diagrama de Classes (UML Simplificado)
