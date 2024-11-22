# EcoCharge

# Vídeo Pitch

[Link do Video Pitch](https://youtu.be/Ky5cP5GOysk?si=at-Vu5HNZXcJN9AE)

# Vídeo do Código

[Link do Video](https://youtu.be/o7f56VZMOXU?si=MUX4wba4XZ0jG7Ok)

# Link do Projeto Railway

[Link do Projeto Railway](https://java-production-b3d5.up.railway.app)

## Introdução

O uso de veículos elétricos (VEs) tem experimentado um crescimento expressivo nos últimos anos, impulsionado pela urgente necessidade de reduzir as emissões de carbono e cumprir metas globais de sustentabilidade. Os VEs oferecem uma alternativa mais ecológica em comparação aos veículos movidos a combustíveis fósseis, contribuindo para a diminuição da poluição do ar e do impacto ambiental. No entanto, a ampla adoção desses veículos ainda enfrenta desafios significativos, principalmente relacionados à infraestrutura de carregamento e ao planejamento de viagens para veículos com autonomia limitada.

Neste contexto, a EcoCharge surge como uma solução inovadora, dedicada a simplificar o acesso a pontos de carregamento e otimizar o uso dos VEs. A proposta da EcoCharge é fornecer uma plataforma digital que permite aos usuários localizar e acessar pontos de carregamento de forma eficiente, além de facilitar o planejamento de viagens, garantindo que os motoristas de VEs possam maximizar a autonomia de seus veículos e minimizar o tempo de inatividade. Com soluções como a EcoCharge, a transição para a mobilidade elétrica se torna mais viável e acessível, promovendo um futuro mais sustentável.

## Problemática

Atualmente, no Brasil, de acordo com a Associação Brasileira de Veículos Elétricos (ABVE), temos cerca de 78.000 veículos elétricos circulando pelas ruas do país, sendo 40.601 deles veículos elétricos leves ou carros de passeio. A ABVE prevê que esse número ultrapassará os 100.000 até janeiro de 2025, fazendo com que a frota de veículos elétricos aumente de 2% para 3,3%. As projeções indicam que, em 2027, essa porcentagem deverá crescer para 5,6%, e em 2029 para 9,8%, o que representará um impacto significativo no meio ambiente brasileiro, apesar de ainda não ser proporcional à quantidade de veículos a combustão nas ruas.

O maior desafio enfrentado por potenciais compradores de veículos elétricos é a infraestrutura de carregamento. Muitas pessoas se perguntam: “Onde posso carregar meu veículo?” e “Onde estão localizados os postos de recarga?”. Em março de 2024, havia apenas 4.600 pontos de recarga para carros elétricos no país, segundo a ABVE. Embora a previsão seja de que esse número aumente para 10.000 no início de 2025, a quantidade atual ainda é insuficiente para atender à crescente demanda.

O problema real para os motoristas de veículos elétricos hoje é saber até onde seu carro pode chegar com a carga disponível e onde está o ponto de recarga mais próximo.

## Objetivo

A EcoCharge tem como objetivo principal resolver o maior desafio enfrentado pelos proprietários e futuros proprietários de veículos elétricos, proporcionando uma experiência de recarga mais tranquila, eficaz e segura. A solução da EcoCharge facilita o acesso a postos de carregamento próximos utilizando a localização do usuário. O aplicativo exibe postos nas proximidades, detalhando o tipo de carregamento oferecido, os conectores disponíveis, horário de funcionamento, entre outras informações relevantes para os condutores.

Além disso, a EcoCharge oferece um planejador de viagens otimizado para veículos elétricos, sugerindo rotas com pontos de carregamento estratégicos, levando em consideração a autonomia do veículo e a infraestrutura disponível ao longo do percurso. A aplicação também permite a criação de perfis de usuários e o registro do histórico de carregamento, possibilitando o monitoramento do uso e das emissões evitadas, promovendo a conscientização sobre o impacto ambiental positivo.

A EcoCharge também visa incentivar uma rede de carregamento confiável através do feedback dos usuários. A plataforma permite a avaliação dos postos de carregamento, promovendo uma rede orientada pela experiência dos motoristas. Com essas funcionalidades, a EcoCharge otimiza a utilização dos veículos elétricos e contribui para uma transição mais fluida para a mobilidade elétrica.

## Sobre o Projeto

**EcoCharge** é um projeto de aplicativo de transporte sustentável desenvolvido por estudantes da **FIAP** como parte da Global Solution. Este aplicativo é voltado para promover o uso de veículos elétricos (VEs) ao facilitar o acesso a pontos de carregamento e otimizar o planejamento de viagens. Nossa equipe de cinco integrantes está comprometida em criar uma experiência prática e intuitiva para motoristas de VEs, contribuindo com um futuro mais sustentável.

## Equipe

| Integrante                       | RM     | Turma  |
| -------------------------------- | ------ | ------ |
| Augusto Barcelos Barros          | 98078  | 2TDSS  |
| Izabelly De Oliveira Menezes     | 551423 | 2TDSA  |
| Lucas Pinheiro de Melo           | 97707  | 2TDSS  |
| Marcos Henrique Garrido da Silva | 99578  | 2TDSA  |
| Mel Maia Rodrigues               | 98266  | 2TDSA  |

## Funcionalidades Principais

- **Localização de Postos de Carregamento**  
  Visualize postos de carregamento próximos com informações detalhadas sobre a ocupação atual, tipos de conectores e velocidade de carregamento.
- **Disponibilidade e Reservas**  
  Verifique a disponibilidade em tempo real e, onde aplicável, reserve o ponto de carregamento desejado.
- **Planejador de Viagem para VEs**  
  Planeje suas viagens com segurança, recebendo sugestões de rotas com pontos de carregamento estratégicos ao longo do trajeto.
- **Perfis e Histórico de Carregamento**  
  Crie um perfil, registre seu histórico de carregamentos e acompanhe estatísticas de impacto ambiental.
- **Feedback e Avaliações**  
  Avalie os pontos de carregamento para contribuir com uma rede mais confiável e orientada pela experiência dos usuários.

---

Junte-se a nós nessa jornada por um futuro mais sustentável!

## Instruções para uso

1. Clone o repositório do projeto ou baixe os arquivos do zip:
    ```sh
    git clone https://github.com/seu-usuario/ecocharge.git
    cd ecocharge
    ```

2. Inicialize o projeto Maven na sua IDE:
    ```sh
    mvn clean install
    ```

3. Execute o projeto:
    ```sh
    mvn spring-boot:run
    ```

4. Teste usando seu API tester de preferência (Postman/Insomnia):
    Copie nosso arquivo mais recente .json dentro da pasta requests, e importe em seu API tester, para que assim, veja as requisições possíveis.

## Requisitos

- Java 17
- Maven

## Dependências

- **spring-boot-starter-web**: Fornece recursos essenciais para desenvolver aplicativos da web no Spring Boot, como configuração do servidor incorporado e suporte a RESTful.
- **spring-boot-devtools**: Facilita o desenvolvimento ao oferecer ferramentas como reinicialização automática do aplicativo e configurações específicas de desenvolvimento.
- **lombok**: Reduz a verbosidade do código Java automatizando a geração de métodos padrão, como getters, setters e construtores, através de anotações.
- **spring-boot-starter-test**: Oferece suporte para testes em aplicativos Spring Boot, incluindo bibliotecas como JUnit e Mockito, simplificando a escrita e execução de testes.
- **spring-boot-starter-data-jpa**: Simplifica o acesso e manipulação de dados em bancos de dados através do Spring Data JPA, configurando automaticamente a camada de persistência para trabalhar com JPA em aplicativos Spring Boot.
- **spring-security-test**: Facilita testes de segurança no Spring.
- **java-jwt**: Implementa a funcionalidade de geração e validação de tokens JWT em aplicativos Java.
- **spring-boot-starter-oauth2-client**: Suporte para autenticação e autorização OAuth2 em aplicativos Spring Boot.
- **spring-boot-starter-security**: Fornece autenticação e controle de acesso para aplicativos Spring Boot.
- **spring-boot-starter-mail**: Suporte para envio de e-mails em aplicativos Spring Boot.
- **spring-boot-starter-actuator**: Fornece funcionalidades de monitoramento e gerenciamento para aplicativos Spring Boot.
- **spring-boot-starter-amqp**: Suporte para mensageria AMQP em aplicativos Spring Boot.
- **spring-boot-starter-hateoas**: Suporte para a criação de APIs HATEOAS em aplicativos Spring Boot.
- **spring-boot-starter-validation**: Suporte para validação de dados em aplicativos Spring Boot.
- **flyway-core**: Ferramenta de migração de banco de dados para Java.
- **flyway-sqlserver**: Suporte para migração de banco de dados SQL Server com Flyway.
- **springdoc-openapi-starter-webmvc-ui**: Integração com Springdoc OpenAPI para geração de documentação de APIs.
- **h2**: Banco de dados em memória para testes.
- **mssql-jdbc**: Driver JDBC para Microsoft SQL Server.
- **spring-ai-openai-spring-boot-starter**: Suporte para integração com OpenAI em aplicativos Spring Boot.

## Funcionalidade

O sistema possui diversas funcionalidades CRUD para nossas entidades:

### Autenticação

A autenticação no projeto EcoCharge é realizada utilizando JSON Web Tokens (JWT) e OAuth2. O JWT é um token que é gerado ao fazer o login e é necessário para acessar as rotas protegidas do sistema. Abaixo estão os endpoints relacionados à autenticação:

- **Login**: Permite que um usuário faça login no sistema. Ao fazer login, um token JWT é gerado e retornado ao usuário.

#### Métodos de Login

Os métodos de login presentes no projeto EcoCharge são responsáveis por autenticar os usuários e gerar tokens JWT. Abaixo estão os detalhes dos métodos de login:

- **POST /login**: Este endpoint recebe as credenciais do usuário (nome de usuário e senha) e verifica se são válidas. Se as credenciais forem válidas, um token JWT é gerado e retornado ao usuário. O token deve ser incluído no cabeçalho das solicitações subsequentes para acessar as rotas protegidas.

Exemplo de solicitação de login:

#### Métodos OAuth2

Os métodos OAuth2 presentes no projeto EcoCharge são responsáveis por autenticar os usuários através de provedores externos. Abaixo estão os detalhes dos métodos OAuth2:

- **GET /oauth2/authorization/{Google}**: Redireciona o usuário para o provedor OAuth2 especificado para autenticação.

#### Criação de Novo Usuário com OAuth2

Quando um usuário tenta fazer login usando OAuth2 pela primeira vez, o sistema verifica se o usuário já existe no banco de dados. Se o usuário não existir, um novo usuário é criado automaticamente com base nas informações fornecidas pelo provedor OAuth2 (por exemplo, nome, email). Esse novo usuário é então salvo no banco de dados e autenticado no sistema.

#### Buscar Imagem de Perfil do Google ao Logar

Quando um usuário faz login usando OAuth2, o sistema também busca a imagem de perfil da conta do Google e a associa ao usuário no sistema.

### Usuários

- **GET /usuarios**: Lista todos os usuários.
- **GET /usuarios/{id}**: Retorna um usuário específico.
- **POST /usuarios**: Cria um novo usuário.
- **PUT /usuarios/{id}**: Atualiza um usuário existente.
- **DELETE /usuarios/{id}**: Deleta um usuário pelo ID.
- **POST /usuarios/perfil/{id}**: Faz upload de uma imagem de perfil para um usuário.

### Postos de Carregamento

- **GET /postos**: Lista todos os postos de carregamento.
- **GET /postos/{id}**: Retorna um posto de carregamento específico.
- **POST /postos**: Cria um novo posto de carregamento.
- **PUT /postos/{id}**: Atualiza um posto de carregamento existente.
- **DELETE /postos/{id}**: Deleta um posto de carregamento pelo ID.

### Pontos de Carregamento

- **GET /pontos-carregamento**: Lista todos os pontos de carregamento.
- **GET /pontos-carregamento/{id}**: Retorna um ponto de carregamento específico.
- **POST /pontos-carregamento**: Cria um novo ponto de carregamento.
- **PUT /pontos-carregamento/{id}**: Atualiza um ponto de carregamento existente.
- **DELETE /pontos-carregamento/{id}**: Deleta um ponto de carregamento pelo ID.

### Reservas

- **GET /reservas**: Lista todas as reservas.
- **GET /reservas/{id}**: Retorna uma reserva específica.
- **POST /reservas**: Cria uma nova reserva.
- **PUT /reservas/{id}**: Atualiza uma reserva existente.
- **DELETE /reservas/{id}**: Deleta uma reserva pelo ID.

### Avaliações

- **GET /avaliacoes**: Lista todas as avaliações.
- **GET /avaliacoes/{id}**: Retorna uma avaliação específica.
- **POST /avaliacoes**: Cria uma nova avaliação.
- **PUT /avaliacoes/{id}**: Atualiza uma avaliação existente.
- **DELETE /avaliacoes/{id}**: Deleta uma avaliação pelo ID.

### Históricos de Carregamento

- **GET /historicos-carregamento**: Lista todos os históricos de carregamento.
- **GET /historicos-carregamento/{id}**: Retorna um histórico de carregamento específico.
- **POST /historicos-carregamento**: Cria um novo histórico de carregamento.
- **PUT /historicos-carregamento/{id}**: Atualiza um histórico de carregamento existente.
- **DELETE /historicos-carregamento/{id}**: Deleta um histórico de carregamento pelo ID.

### Veículos

- **GET /veiculos**: Lista todos os veículos.
- **GET /veiculos/{id}**: Retorna um veículo específico.
- **POST /veiculos**: Cria um novo veículo.
- **PUT /veiculos/{id}**: Atualiza um veículo existente.
- **DELETE /veiculos/{id}**: Deleta um veículo pelo ID.

### Viagens

- **GET /viagens**: Lista todas as viagens.
- **GET /viagens/{id}**: Retorna uma viagem específica.
- **POST /viagens**: Cria uma nova viagem.
- **PUT /viagens/{id}**: Atualiza uma viagem existente.
- **DELETE /viagens/{id}**: Deleta uma viagem pelo ID.

## Estrutura

O sistema está organizado seguindo a arquitetura MVC:

- **controller**: Classes que lidam com solicitações HTTP, invocando a lógica de negócios apropriada e retornando respostas HTTP.
- **dto**: Classes que transferem dados entre diferentes partes do sistema, frequentemente refletindo a estrutura dos dados das solicitações e respostas HTTP.
- **handler**: Classes que lidam com exceções específicas ou globais no aplicativo.
- **model**: Classes que representam os objetos de negócios do aplicativo.
- **repository**: Classes ou interfaces que definem operações de acesso a dados para interagir com o banco de dados.
- **service**: Classes que contêm a lógica de negócios da aplicação, intermediando entre os controllers e os repositórios, promovendo a reutilização e a testabilidade do código.
- **config**: Classes de configurações do projeto, como por exemplo, configurações de segurança.

# Diagrama de Classes e Métodos

![Diagrama](docs\Java.jpg)
