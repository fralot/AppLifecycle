# Projeto lyfecycle

## Visão Geral

O projeto **lyfecycle** é uma aplicação Spring que se conecta a um banco de dados Oracle. Ele é empacotado como um contêiner Docker para facilitar o deploy e a gestão.

## Descrição dos Arquivos

- **src/main/resources/application.properties**: Contém as configurações da aplicação Spring, incluindo detalhes de conexão com o banco de dados e propriedades do Hibernate.

- **Dockerfile**: Define as instruções para construir a imagem Docker da aplicação. Utiliza a imagem base do Eclipse Temurin, adiciona o arquivo JAR da aplicação, expõe a porta 8080 e define o ponto de entrada para executar o JAR.

- **docker-compose.yaml**: Define os serviços, redes e volumes da aplicação. Especifica como executar o contêiner da aplicação e inclui variáveis de ambiente para configuração do datasource do Spring.

## Instruções de Configuração

1. **Clonar o Repositório**  
   Clone o repositório para sua máquina local.

2. **Construir a Imagem Docker**  
   Navegue até o diretório do projeto e execute o seguinte comando para construir a imagem Docker:
   ```bash
   docker build -t lyfecycle .

3. **Executar a Aplicação**
   Use o Docker Compose para iniciar a aplicação:
   ```
   docker-compose up
   ```

## Endpoints da API

### QualidadeArController

Este controlador gerencia as operações relacionadas à qualidade do ar.

#### Endpoints

1. **Buscar todos os registros de qualidade do ar**
   - **URL**: `/qualidade-ar`
   - **Método**: `GET`
   - **Resposta**: Lista de objetos `QualidadeAr`.
   - **Códigos de status**:
     - `200 OK`: Registros encontrados.
     - `404 Not Found`: Nenhum registro disponível.

2. **Obtener un registro de calidad del aire por ID**
   - **URL**: `/qualidade-ar/{id}`
   - **Método**: `GET`
   - **Parâmetros**:
     - `id` (Path Variable): ID do registro.
   - **Resposta**: Objeto `QualidadeAr`.
   - **Códigos de status:**:
     - `200 OK`: Registro encontrado.
     - `404 Not Found`: Registro não encontrado.

3. **Criar novo registro de qualidade do ar**
   - **URL**: `/qualidade-ar`
   - **Método**: `POST`
   - **Corpo da requisição**: 
        ```json
        {
            "idEstacao": 8,
            "dataHora": "2024-11-03T10:20:00",
            "nivelPm25": 90,
            "nivelPm10": 10,
            "configAlertasIdConfiguracao": 1
        }

        ```

   - **Resposta**: Mensagem de sucesso com os detalhes do registro criado.
   - **Códigos de status**:
     - `201 Created`: Registro criado com sucesso.

4. **Actualizar un registro de calidad del aire**
    > Requer autenticação com Bearer Token de um usuario com role ADMIN

   - **URL**: `/qualidade-ar/{id}`
   - **Método**: `PUT`
   - **Parâmetros**:
     - `id` (Path Variable): ID do registro a ser atualizado.
   - **Corpo da requisição**: 
        ```json 
        {
            "idEstacao": 8,
            "dataHora": "2024-11-03T10:20:00",
            "nivelPm25": 50,
            "nivelPm10": 20,
            "configAlertasIdConfiguracao": 1
        }

        ```
   - **Resposta**: Mensagem de sucesso com os detalhes do registro atualizado.
   - **Códigos de status**:
     - `200 OK`: Registro atualizado com sucesso.
     - `404 Not Found`: Registro não encontrado.

5. **Excluir um registro de qualidade do ar**
    > Requer autenticação com Bearer Token de um usuario com role ADMIN


   - **URL**: `/qualidade-ar/{id}`
   - **Método**: `DELETE`
   - **Parâmetros**:
     - `id` (Path Variable): ID do registro a ser excluído.
   - **Resposta** Mensagem indicando que o registro foi removido.
   - **Códigos de status**:
     - `204 No Content`: Registro excluído com sucesso.
     - `404 Not Found`: Registro não encontrado.

---

### AuthController

Este controlador gerencia a autenticação e o registro de usuários.

#### Endpoints

1. **Iniciar sesión**
   - **URL**: `/auth/login`
   - **Método**: `POST`
   - **Corpo da requisição**: 
     ```json
     {
       "email": "usuario@example.com",
       "password": "senha"
     }
     ```
   - **Resposta**: Token JWT de autenticação.
   - **Códigos de status**:
     - `200 OK`: Credenciais válidas.
     - `401 Unauthorized`: Credenciais inválidas.

2. **Registrar novo usuário**
   - **URL**: `/auth/register`
   - **Método**: `POST`
   - **Corpo da requisição**:
     ```json
     {
        "email": "devuser@mail.com",
        "password": "senha",
        "role": "ADMIN"
    }
     ```
   - **Resposta**: Objeto JSON com os dados do usuário registrado.
   - **Códigos de status**:
     - `201 Created`: Usuário registrado com sucesso.
     - `400 Bad Request`: Dados inválidos.

---

### HealthCheckController

Este controlador fornece um endpoint simples para verificar o estado do serviço.

#### Endpoints

- **URL**: `/`
   - **Método**: `GET`
   - **Resposta**: "O serviço está funcionando corretamente."
   - **Códigos de status**:
     - `200 Created`: O serviço está funcionando corretamente.
     - `404 Not Found`:  O serviço **Não** está funcionando corretamente.


---
