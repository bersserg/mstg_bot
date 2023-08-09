# The mstg_bot project

mstg_bot is a file sharing telegram bot on microservice architecture.

---------------------------------------------------------------------------------------------------------------
- You need Java version 12 or later, such as [OpenJDK 12](https://jdk.java.net/12/)
- The project is built on Spring Boot
- RabbitMQ is used as a message broker
- PostgreSQL database
- RabbitMQ and PostgreSQL deployed in the docker
- To get a static ip address, ngrok is used
---------------------------------------------------------------------------------------------------------------
## Build instructions:

### Default credentials in the project and commands below:
- `user: userok`
- `password: p@ssw0rd`
- `Postgres database name: mstg`

### By default, microservices and DB are set to local uri (for running on a computer). 
To use other credentials and addresses, you must also change the settings in the application.properties files.
---------------------------------------------------------------------------------------------------------------
- Download and install [docker](https://www.docker.com)
- Deploying RabbitMQ in the docker:
  - Download image rabbitmq: 
  ```bash
  docker pull rabbitmq:3.11.0-management
  ```
  - Create volume: 
  ```bash
  docker volume create rabbitmq_data
  ```
  - Run a rabbitmq container with a rabbitmq_data volume, such as this:
    - For Linux:
  ```bash 
  docker run -d --hostname rabbitmq --name rabbitmq -p 5672:5672 -p 15672:15672 -v rabbitmq_data:/var/lib/rabbitmq --restart=unless-stopped rabbitmq:3.11.0-management
  ```
    - For Windows:
  ```bash
  docker run -d --hostname rabbitmq --name rabbitmq -p 5672:5672 -p 15672:15672 -v rabbitmq_data:/var/lib/docker/volumes/rabbitmq_data/_data --restart=unless-stopped rabbitmq:3.11.0-management
  ```
  - Connect to the container with rabbitmq:
  ```bash 
  docker exec -it rabbitmq /bin/bash
  ```
  - Inside the container, create a user, make him admin and set permissions, for example:
  ```bash 
  rabbitmqctl add_user userok p@ssw0rd
  rabbitmqctl set_user_tags userok administrator
  rabbitmqctl set_permissions -p / userok ".*" ".*" ".*"
  ```
- Deploying PostgresQL in the docker:
  - Download image:
  ```bash 
  docker pull postgres:14.5
  ```
  - Create volume: 
  ```bash 
  docker volume create postgresql
  ```
  - Run a postgres container with a postgresql volume, such as this:
  ```bash 
  docker run -d --hostname mstg --name mstg -p 5434:5432 -e POSTGRES_USER=userok -e POSTGRES_PASSWORD=p@ssw0rd -e POSTGRES_DB=mstg -v /data:/var/lib/docker/volumes/postgresql/_data --restart=unless-stopped postgres:14.5
  ```
I use external port 5434 as my default port 5432 is busy, but you can use it if you have it free.

- Prepare ngrok
  - Register on the [ngrok](https://ngrok.com) website and copy the authtoken in your personal account
  - Download [ngrok](https://ngrok.com/download) for Windows
  - Install ngrok in Ubuntu:
  ```bash 
  sudo snap install ngrok
  ```
  - Add authtoken:
  ```bash 
  ngrok config add-authtoken [your_authtoken]
  ```

- Set application.properties:
  - dispatcher service:
    - bot.name=your bot's name 
    - bot.token=your bot's token 
  - nod service:
    - token=your bot's token
    - salt=your encryption salt
    - rest-service:
    - salt=your encryption salt
  - mail-servise:
    - spring.mail.host=your mail server
    - spring.mail.username=your user
    - spring.mail.password=your password
---------------------------------------------------------------------------------------------------------------
## Run instructions:

- Start ngrock and run the command:
```bash 
ngrok http 8084
```
- Copy the temporary static ip from ngrok and set this value to the bot.uri variable in application.properties in the dispatcher service:
  - bot.uri=temporary static ip from ngrok
- Run containers with RabbitMQ and Postgres in docker
- Run microservices:
  - dispatcher
  - nod
  - rest-service
  - mail-service
  
### The bot is ready to use

---------------------------------------------------------------------------------------------------------------
## How to use the bot:

- Before using it, you need to register, for which you need to select the /registration command in the menu, after which the bot will request an e-mail. It is necessary to send the e-mail address to the chat, after which the bot will send an e-mail with a link to the specified e-mail, when you click on it, a message about successful registration will appear, after which you can save files.
- Send the file to chat, the bot will save it in the database and return you a link to it.
---------------------------------------------------------------------------------------------------------------
I'm still just learning development and this is my learning project, the development of this bot is ongoing.