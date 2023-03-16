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

</br>

### Instalação

Requisitos:

- Docker
- Maven
- Java 8
- API Cliente

</br>

Todos arquivos necessários para instalação e gerenciamento já estão na pasta raiz do projeto:

- Na pasta work está o ambiente docker para virtualizar o banco de dados e o arquivo jar para executar o aplicativo

- Na raiz do projeto já tem uma collection do postman configurada com os endpoint necessário, inclusive a autenticação

</br>

Variáveis utilizadas

![image](https://user-images.githubusercontent.com/84253307/225777315-7f62bd10-9663-4111-9151-95fd3f46083d.png)


</br>

Autenticação:

![image](https://user-images.githubusercontent.com/84253307/225777389-be844561-3fce-4195-b9a5-bb8a5cb30fb3.png)

</br>

Ingredientes paginados:

![image](https://user-images.githubusercontent.com/84253307/225777492-fde40186-2b3f-4960-a402-aadfa6c335f3.png)

</br>

Novo pedido pelo id do lanche:

![image](https://user-images.githubusercontent.com/84253307/225777763-62bc8f00-9fe2-4206-a9fd-3653c641c530.png)


<br>

**Para executar alguns endpoint será necessário autenticar com o username maria@gmail.com que tem o perfil de acesso ADM, inclusive criar outros usuários**



</br>
















