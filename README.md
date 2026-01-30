# Sistema de Gestão Hoteleira

## Descrição do Projeto
Este projeto consiste em um Sistema de Gestão Hoteleira desenvolvido em Java, 
utilizando interface gráfica (Swing) e banco de dados relacional MySQL.  
O sistema tem como finalidade automatizar e organizar os principais processos 
administrativos de um hotel, como cadastro de funcionários, quartos, reservas, limpeza e controle de pagamentos.

O desenvolvimento seguiu os requisitos funcionais e não funcionais definidos em documento de especificação, 
adotando boas práticas de programação orientada a objetos e organização em camadas.

---

## Objetivo
Desenvolver um sistema desktop que auxilie a gestão hoteleira, proporcionando maior controle, 
eficiência operacional e organização das informações, atendendo às necessidades de funcionários e gerentes do hotel.

---

## Arquitetura do Sistema
O projeto segue o padrão **MVC (Model-View-Controller)**, com separação clara entre:
- **Model**: entidades e regras de negócio
- **View**: telas do sistema (interface gráfica)
- **Controller/DAO**: controle das ações e acesso ao banco de dados

---

## Telas Implementadas
O sistema conta com as seguintes telas implementadas:

- **TelaLogin** – Autenticação de funcionários
- **TelaPrincipal** – Menu principal do sistema
- **TelaCadastroFuncionario** – Cadastro e gerenciamento de funcionários
- **TelaCadastroQuarto** – Cadastro e controle de quartos
- **TelaCadastrarReserva** – Registro e gerenciamento de reservas
- **TelaCadastrarLimpeza** – Registro de limpeza dos quartos
- **TelaConsultarSalarioFuncionario** – Consulta de salário e informações funcionais
- **TelaLiberarPagamentoSalario** – Liberação e controle de pagamento de salários

---

## Funcionalidades Principais
- Login de funcionários com controle de acesso
- Cadastro e consulta de funcionários
- Cadastro e gerenciamento de quartos
- Registro e controle de reservas
- Registro de limpeza dos quartos
- Consulta e liberação de pagamento de salários
- Integração com banco de dados MySQL

---

## Tecnologias Utilizadas
- **Java**
- **Java Swing** (Interface gráfica)
- **MySQL** (Banco de dados)
- **NetBeans IDE**
- **JDBC** para conexão com o banco de dados

---

## Banco de Dados
O banco de dados foi modelado em **MySQL**, seguindo o padrão definido no documento de requisitos do projeto, respeitando a estrutura de entidades como:
- Funcionário
- Quarto
- Reserva
- Limpeza
- Fluxo de Caixa

---

## Como Executar o Projeto
1. Clone este repositório
2. Abra o projeto na **IDE NetBeans**
3. Configure a conexão com o banco de dados MySQL
4. Execute o script SQL para criação das tabelas
5. Execute a classe principal do sistema
6. Realize o login para acessar o sistema

---

## Requisitos
- Java JDK 8 ou superior
- MySQL Server
- NetBeans IDE

---

## Autor
**Manoel Pimentel**  
Projeto desenvolvido para fins acadêmicos.
