## Lutris

This project was developed during the lessons of "Tópicos da Computação" course, applying several concepts, like containers, orchestrating containers, web-servers and HTTP communication.

### Architecture

This project was divided into two main parts:

- api: where the main business rules were applied and manipulated the stored data
- web: front-end web that consumes `api`

To know more about any of these parts, see the `README.md` present in the respective directory of each part.

### Getting started

This section was dedicated to starting the complete project, to start a part separated, see the `Getting started` section present in the respective directory of each part.

#### Requirements

- Docker
- Docker-Compose

0. Ensure that nothing was running in the ports `1433`, `8080` and `3000`

1. Go to `web/` and rename the `.example.env` file to `.env` and populate the necessary information

2. Go to `api/` and rename the `.example.env` file to `.env` and populate the necessary information

3. In the root directory, run `docker-compose up -d --build --always-recreate-deps`

4. Run the necessary migrations and seeds accessing the database container with `docker exec -it lutris-db /bin/bash`, then running `./tmp/migrations/migrator.sh` and then `/tmp/seeds/seeder.sh`

5. You can access the database by accessing the `1433` from your local

6. You can access the API by accessing the `8080` from your local

7. You can access the web by accessing the `3000` from your local
