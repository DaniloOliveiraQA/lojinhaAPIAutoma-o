## Lojinha API Automação
Este é um repositório que contém a automação de alguns testes de API Rest de um software denominado Lojinha. Os sub-tópicos abaixo descrevem algumas decisões tomadas na estruturação do projeto.

## Tecnologias Utilizadas

- Java
  https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html
- JUnit
  https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine/5.7.1
- RestAssured
  https://mvnrepository.com/artifact/io.rest-assured/rest-assured/4.4.0
- Maven
  https://maven.apache.org/

## Testes Automatizados
Testes para validar as partições de equivalência relacionadas ao valor produto na Lojinha, que estão vinculadas diretamente a regra de negócio que diz que o valor do produto deve estar entre R$ 0,01 e R$ 7.000,00 reais.

## Notas Gerais

- Sempre utilizamos a anotação Before Each para capturar o token que será utilizado posteriormente nos métodos de teste
- Armazenei os dados que são enviados para a API através do uso de classes POJO
- Criei dados iniciais atravéz do uso de classe Data Factory, para facilitar a criação e controla dos mesmos
- Neste projeto fiz o uso do JUnit 5, o que nos dá a possibilidade de usar a anotação DisplayName para dar descrições em português para os testes