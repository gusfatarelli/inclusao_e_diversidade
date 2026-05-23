# API Inclusão e Diversidade

Solução corporativa backend desenvolvida em Java com Spring Boot e Oracle Database para gestão de processos seletivos inclusivos. A arquitetura implementa autenticação stateless baseada em tokens JWT e migração automatizada de esquemas relacionais.

---

## Arquitetura e Execução (DevOps)

A infraestrutura foi totalmente containerizada via Docker. O ambiente isola a aplicação do banco de dados, eliminando a necessidade de configurações locais prévias de SDKs ou SGBDs.

### Pré-requisitos
* Docker e Docker Compose instalados e operacionais.

### Instruções de Inicialização
1. Efetue o download ou clone deste repositório na máquina local.
2. Navegue até a raiz do projeto através do terminal (diretório que contém os arquivos Dockerfile e docker-compose.yml).
3. Execute o comando de build e inicialização:
   ```bash
   docker-compose up --build
