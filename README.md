# 🚗 Sistema de Gerenciamento de Automóveis em Java

Este é um projeto simples desenvolvido em Java para gerenciar o cadastro de automóveis. Ele permite realizar operações CRUD (Criar, Ler, Atualizar, Excluir) em uma lista de automóveis, com persistência de dados em um arquivo de texto.

## ✨ Funcionalidades

* **Inclusão de Automóvel:** Adiciona um novo automóvel ao sistema, com validação de placa única.
* **Exclusão de Automóvel:** Remove um automóvel do cadastro com base na sua placa.
* **Alteração de Dados:** Permite modificar as informações de um automóvel existente.
* **Consulta por Placa:** Exibe os detalhes de um automóvel específico pesquisando pela placa.
* **Listagem Ordenada:** Lista todos os automóveis cadastrados, com opções de ordenação por:
    * Placa
    * Modelo
    * Marca
* **Persistência de Dados:**
    * Salva todos os dados dos automóveis em um arquivo de texto (`automoveis.txt`) no formato CSV.
    * Carrega os dados do arquivo automaticamente ao iniciar o programa, garantindo que as informações não sejam perdidas.

## 🛠️ Tecnologias Utilizadas

* **Java 8+**: Linguagem de programação principal.
* **`ArrayList<Automovel>`**: Estrutura de dados para armazenar os automóveis em memória.
* **`BufferedReader` e `BufferedWriter`**: Classes para leitura e escrita de dados em arquivos de texto.
* **`Comparator`**: Utilizado para realizar a ordenação da lista de automóveis.
* **Scanner**: Para interagir com o usuário via console.

## 📁 Estrutura do Projeto

O projeto segue uma estrutura básica de diretórios para aplicações Java:

