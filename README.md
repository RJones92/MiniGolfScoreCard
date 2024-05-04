# Minigolf Score Card
Latest build status: [![CircleCI](https://circleci.com/gh/RJones92/MiniGolfScoreCard/tree/main.svg?style=svg)](https://circleci.com/gh/RJones92/MiniGolfScoreCard/tree/main)

The app's purpose is to record annual mini golf tournament scores, and draw up statistics for each participant.

## Tech stack

Technologies used for the frontend, backend and database:
- NextJs frontend
  - NodeJs v22.1.0
- Java backend
  - Java 11
- SQL DB
  - h2 for local development
  - postgres for production

## Run the program in developer mode
You can run the frontend and the backend separately.\
They can still communicate.\
The main benefit of this is you can amend the frontend using the builtin hot-reload,
whilst having the backend up and running without rebuilding.

### Backend
You build just the backend with `mvn clean install -Pdev`.
This builds the application with maven using the 'dev' profile to prevent building the frontend too.\
Run the compiled Java Spring application on port 8080 with environment variable `spring_profiles_active=dev`.

### Frontend
You can start up just the frontend by navigating to the UI directory: `cd /src/main/ui`.\
You can start up the frontend in developer mode by running `npm run dev`.

## Run on your own webserver
The application is configured with a Spring Bean that serves the application on port 9090.
This set up is compatible with (at least) an Apache2 web server.