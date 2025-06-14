# 🏦 XuBank - Sistema Bancário com Java (MVC + JUnit)
Desenvolvido por: Allan Mateus Arruda de Souza e Lara Andrade Carvalho

XuBank é um sistema bancário simples construído em Java, utilizando o padrão de arquitetura **MVC** e com **testes unitários em JUnit 5**. O sistema foi desenvolvido com base em um diagrama UML e regras de negócio claras sobre contas, rendimentos, taxas e operações.

---

## 📁 Estrutura do Projeto

```
XuBank/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/xubank/
│   │   │       ├── model/        # Camada de domínio (Conta, Cliente, etc.)
│   │   │       ├── controller/   # Camada de controle (BancoController)
│   │   │       └── view/         # Interface de console (Main.java)
│   └── test/
│       └── java/test/            # Testes unitários com JUnit
├── pom.xml                       # Projeto Maven com dependências
└── README.md
```

---

## 🧠 Regras de Negócio

| Tipo de Conta      | Rendimento                | Taxas & Impostos                                           |
|--------------------|---------------------------|-------------------------------------------------------------|
| Corrente           | Nenhum rendimento         | Se saldo negativo, cobra 3% do valor negativo + R$10       |
| Poupança           | 0,60% ao mês              | Sem impostos                                               |
| Renda Fixa         | 0,5% a 0,85% ao mês       | 15% imposto sobre rendimento (no saque) + R$20/mês fixo     |
| Investimento       | -0,60% a 1,50% ao mês     | 22,5% imposto no saque + 1% sobre rendimento positivo       |

---

## 📋 Funcionalidades

- 👤 Cadastro de cliente (com verificação de CPF duplicado)
- 🏦 Criação de contas (com tipos e limites específicos)
- 💰 Operações de depósito e saque
- 📄 Geração de extrato por cliente
- 📊 Relatórios:
  - Total em custódia por tipo de conta
  - Saldo médio de todas as contas
  - Cliente com maior e menor saldo (inclusive negativos)

---

## ⚙️ Execução

Requisitos:
- Java 17+
- Maven

Para rodar via terminal:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="view.Main"
```

Para rodar testes:

```bash
mvn test
```

---

## ✅ Testes

Os testes cobrem:
- Validação de regras de saque e depósito
- Cálculo de rendimento
- Impostos e taxas específicas
- Exceções de saldo insuficiente e CPF duplicado

---

