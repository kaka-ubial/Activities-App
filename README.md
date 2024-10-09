# MealLet

## Video do aplicativo

[![Thumbnail do vídeo](https://img.youtube.com/vi/85blZ22s0Dk/0.jpg)](https://youtu.be/85blZ22s0Dk)


## Descrição

O **MealLet** é um aplicativo Android desenvolvido em Kotlin com uma arquitetura baseada nos princípios de Clean Architecture e SOLID. O aplicativo faz uso de diversas bibliotecas modernas, como Retrofit para chamadas de API, Hilt para injeção de dependências, e ViewModel para gerenciamento de estado e ciclo de vida. Ele segue a abordagem Model-View-ViewModel (MVVM), garantindo a separação clara de responsabilidades e facilitando a escalabilidade e manutenção do código.

## Tecnologias Utilizadas

Aqui estão as principais tecnologias utilizadas no desenvolvimento deste aplicativo:

- ![Kotlin](https://skillicons.dev/icons?i=kotlin) **Kotlin 1.9.24**
- ![Android](https://skillicons.dev/icons?i=androidstudio) **Android SDK 34**
- ![Room](https://skillicons.dev/icons?i=sqlite) **Room**
- ![JUnit](https://skillicons.dev/icons?i=java) **JUnit**
- <img src="https://developer.android.com/images/training/testing/espresso.png" alt="Espresso" width="40"/> **Espresso**
- **Retrofit**
- **Hilt**
- **Picasso**
## Funcionalidades

- **Lista de Receitas**: O aplicativo exibe uma lista de receitas obtidas de uma API, utilizando Retrofit para fazer o consumo dos dados.
- **Injeção de Dependência**: Utiliza Hilt para gerenciar a injeção de dependências de maneira modular e escalável.
- **Arquitetura MVVM**: Separação entre camada de dados, lógica de negócios e interface com o usuário.
- **Testes Automatizados**: Suporte para testes unitários e de UI utilizando JUnit e Espresso.
  
## Estrutura do Projeto

- **app/src/main/java/com/example/activitiesapp/**: Contém as classes principais do projeto.
  - **di/**: Configurações de injeção de dependências.
  - **data/**: Contém os modelos e classes de acesso a dados (API e banco de dados local).
  - **ui/**: Contém as activities e fragments que lidam com a interação do usuário.
  - **viewmodel/**: Gerencia o ciclo de vida e a lógica de negócios, sendo responsável por conectar a View com os dados.
  
## Requisitos

- **Android Studio Bumblebee (ou superior)**
- **SDK 34**
- **Java 20**
  
## Configuração

1. Clone este repositório:
   ```bash
   git clone https://github.com/yourusername/activities-app.git
