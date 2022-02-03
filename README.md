## Getting Started

This is a project for our subject of Oriented Object Programming at UFC, which consists in create a Stock Control System for a mechanical workshop, where the main things the employee can do is to create a product at the stock, edit a product at the stock, list all the products available and their quantity and remove the item from the stock.  

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.


# Descrição :pushpin:

O projeto proposto apresenta uma solução para os instrumentos de controle de estoque das oficinas. A ideia central do projeto é fornecer uma aplicação na qual os funcionários da oficina terão um controle sobre todos os produtos presentes no estoque, podendo solicitar novos produtos ao fornecedor, vender produtos aos clientes da oficina, repor produtos já existentes no estoque, além de verificar informações específicas sobre todos os produtos ou um produto específico. 


# Requisitos Funcionais :pushpin:

## 1.1 [RF001] Adicionar um novo produto ao estoque

Prioridade: ☒ Essencial ☐ Importante ☐ Desejável  

O sistema deve permitir que o funcionário adicione um novo produto ao estoque.

## 1.2 [RF002] Repor um produto já existente no estoque

Prioridade: ☒ Essencial ☐ Importante ☐ Desejável  

O sistema deve permitir que o funcionário reponha um produto já existente que está em falta no estoque.

## 1.3 [RF003] Editar ID do produto no estoque

Prioridade: ☒ Essencial ☐ Importante ☐ Desejável  

O sistema deve permitir que o funcionário edite o ID de um produto já existente que está no estoque.

## 1.4 [RF004] Remover um produto do estoque 

Prioridade: ☒ Essencial ☐ Importante ☐ Desejável  

O sistema deve permitir que o funcionário remova um produto do estoque 

## 1.5 [RF005] Listar produtos do estoque com suas respectivas quantidades

Prioridade: ☒ Essencial ☐ Importante ☐ Desejável 

O sistema deve permitir que o funcionário liste todos os produtos do estoque, juntamente com a quantidade disponível.

## 1.6 [RF006] Buscar informações de um produto específico do estoque pelo Nome e pelo ID

Prioridade: ☒ Essencial ☐ Importante ☐ Desejável 

O sistema deve permitir que o funcionário encontre as informações(nome, código, quantidade, preço, disponibilidade e procura) detalhadas sobre determinado produto, sendo buscadas pelo nome ou pelo id do mesmo.

## 1.7 [RF007] Controlar a saída de produtos presentes no estoque 

Prioridade: ☐ Essencial ☒ Importante ☐ Desejável 

O sistema deve permitir que o funcionário cadastre os produtos que saírem do estoque em decorrência de expedições ou de vendas, modificando a quantidade do produto vendido.

## 1.8 [RF008] Calcular o preço de um produto de acordo com a margem de lucro

Prioridade: ☐ Essencial ☒ Importante ☐ Desejável 

O sistema deve permitir que o funcionário calcule automaticamente o preço de um produto já com uma taxa de 15% em cima do preço sugerido pelo fornecedor. Essa taxa representa a margem de lucro da oficina.

## 1.9 [RF009] Calcular a demanda do produto

Prioridade: ☐ Essencial ☐ Importante ☒ Desejável 

O sistema deve permitir que o funcionário receba informações sobre a procura de determinado produto, podendo ele ser Muito Procurado, Procurado, Pouco procurado ou Não Procurado.

## 1.10 [RF010] Calcular a disponibilidade do produto

Prioridade: ☐ Essencial ☐ Importante ☒ Desejável 

O sistema deve permitir que o funcionário receba informações sobre a disponibilidade de determinado produto, podendo ele ser Indisponível, Muito Baixa, Baixa, Média, Alta, Muito Alta.

## 1.11 [RF011] Listar produtos que são disponibilizados pelo fornecedor

Prioridade: ☐ Essencial ☐ Importante ☒ Desejável 

O sistema deve permitir que o funcionário liste aqueles produtos que são disponibilizados pelo seu fornecedor com seu respectivo valor e quantidade. O funcionário só poderá repor o estoque de um determinado produto se o fornecedor disponibilizar.

# Diagrama UML :pushpin:
![](img/diagramaUML.png)
