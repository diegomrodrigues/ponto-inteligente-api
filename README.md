[![Build Status](https://travis-ci.org/diegomrodrigues/ponto-inteligente-api.svg?branch=master)](https://travis-ci.org/diegomrodrigues/ponto-inteligente-api)

# Ponto Inteligente API
API para um sistema de **Ponto Inteligente**, utilizando o Java e o Spring Boot.  
  
  
## Tecnologias utilizadas
* Java 8  
* Spring Boot  
* Spring Security  
* MySQL  
* H2  
* Flyway: Framework que permite o versionamento e automatização no processo de criação​ ​de​ ​banco​ ​de​ ​dados 
* Swagger: Estrutura de software de código aberto que ajuda os desenvolvedores a projetar, criar, documentar e consumir serviços da Web RESTful  
  
  
## Armazenamento
* GitHub  
* Travis CI: Serviço de integração contínua, usado para criar e testar projetos de software hospedados no GitHub  
  
  
## Pacotes
* api: Ponto de entrada na API
* api.config: Configuração do Swagger
* api.controllers: Expor a API para ser consumida pelos clientes
* api.dtos: O padrão de projetos DTO (Data Transfer Object), permite que os dados utilizados em uma requisição, sejam facilmente convertidos para uma classe Java, sendo classes contendo atributos e os assessores getters e setters
* api.entities: Entidades, com mapeamento para as tabelas no banco de dados
* api.enums: Enumerações utilizadas no sistema
* api.repositories: Interfaces para os Repositórios utilizados na implementação dos serviços
* api.response: Classes para encapsular todas as requisições HTTP de nossa​ ​API
* api.security: Camada de autorização e autenticação com tokens JWT
* api.services: Interfaces para a implementação dos serviços
* api.services.impl: Implementação dos serviços
* api.utils: Utilitários da API
  
  
## Autor
Diego Mendes Rodrigues  
  
[drSolutions - Agência digital](https://www.drsolutions.com.br)