## Apresentação da API para Gerenciamento de Pedidos de Lanches


### Introdução

A API de Gerenciamento de Pedidos de Lanches é uma solução desenvolvida para uma startup de alimentos que permite aos clientes fazer pedidos de lanches personalizados ou escolher opções do cardápio. A API também calcula o preço do pedido com base nos ingredientes escolhidos pelo cliente e nas promoções disponíveis.

### funcionalidades

**A API oferece as seguintes funcionalidades:**

- Listar todos os ingredientes disponíveis

- Listar as opções de lanches do cardápio

- Permitir que o cliente faça um pedido personalizado escolhendo os ingredientes

- Calcular o preço do pedido com base nos ingredientes escolhidos

- Aplicar descontos para promoções válidas

- Retornar o valor total do pedido

- Permitir que o cliente finalize o pedido

### Tecnologias Utilizadas

 - Java 8

 - Spring Boot

- Maven

- Banco de dados relacional (PostgreSQL)

- Docker para empacotar a aplicação

### Arquitetura da API

A API é construída em uma arquitetura RESTful e utiliza o protocolo HTTP para comunicação entre cliente e servidor. O cliente envia requisições HTTP para o servidor e o servidor responde com as informações solicitadas.

### Segurança

A API utiliza autenticação e autorização para garantir que apenas usuários autorizados possam fazer pedidos e acessar informações confidenciais.

### Docker

O banco é empacotado em um container Docker, tornando-a fácil de ser implantada em diferentes ambientes de produção.

### Conclusão

A API de Gerenciamento de Pedidos de Lanches é uma solução eficiente e escalável para gerenciar pedidos de lanches personalizados e opções de cardápio. Com a utilização de tecnologias modernas e boas práticas de desenvolvimento, a API garante a segurança, confiabilidade e facilidade de uso para clientes e desenvolvedores.

###

## Instalação

**Você precisará instalar e configurar na sua máquina:**
 - java 8
 - maven
 - docker

A aplicação está configurada para rodar em um banco postgres com as seguintes informações:

spring.datasource.url=jdbc:postgresql://localhost:5432/dslanchonete

spring.datasource.username=dslanchonete

spring.datasource.password=dslanchonete123

**Dentro da pasta work\docker_db já tem o arquivo postgres-dslanchonete.dockerfile e o init.sql para virtualizar o banco de dados postgres**

**Será necessário instalar o docker na maquina e executar os seguintes comandos:**

docker build -f [ caminho do arquivo .dockerfile ] -t postgres-dslanchonete:latest .

docker run --name postgres-dslanchonete -p 5432:5432 -d postgres-dslanchonete

**Para ter certeza que o ambiente de virtualição está rodando use uma ferramenta como datagrip ou dbeaver para acessar o banco de dados**

Use o comando mvn clean install na pasta raiz do projeto

Na pasta lanchonete-api\backend\target use o comando java -jar application.jar para subir a aplicação


###

Será necessário um cliente API para executar os endpoint, como postman ou similar.


### Variáveis criadas:

![image](https://user-images.githubusercontent.com/84253307/225772008-6135fa28-6b78-439d-94c4-6b4771abba86.png)

Autenticação:

![image](https://user-images.githubusercontent.com/84253307/225772255-32484c94-5e1d-47d3-a808-c1b778a16a62.png)

O alex está registrado com perfil de somente operador, ele não poderá executar alguns endpoint (exemplo os que alteraram e adicionam usuários)

Caso queira o perfil mais completo mude o username para maria@gmail.com















