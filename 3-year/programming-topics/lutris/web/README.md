# WEB

This application is the better way to consume and manipulate the data stored with `api`

## Technologies

- Typescript
- ReactJS
- NextJS
- TailwindCSS
- React-Hook-Form
- Lucide Icons
- Docker

## Getting started

### Requirements

- NodeJS v18 or newer
- Docker (optional)

### Configuring your environment

1. Rename the `.example.env` file to `.env` and populate the necessary information
2. Ensure that the `api` is running
3. Install the dependencies running `npm install`

### Starting the project as dev mode

1.  Run `npm run dev` to start the dev server
2.  Open a browser of your preference and navigate to `http://localhost:3000/`

### Starting the production build with Docker

1. Build the container image running `docker build -t lutris-web .`
2. Create an application container running `docker -it -p 3000:3000 lutris-web`
3. Open a browser of your preference and navigate to `http://localhost:3000/`

### Starting with the production build

1. Build the application running `npm run build`
2. Start the production server running `npm run start`
3. Open a browser of your preference and navigate to `http://localhost:3000/`
