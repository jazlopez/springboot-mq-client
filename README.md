# springboot-mq-client
springboot-mq-client: simple Java implementation of a client to connect to IBM Message Queue service. Enqueue messages via exposed simple API.



### ABSTRACT

Simple MQ IBM client to enqueue messages

### PRE-REQUISITE

[Docker](#)
[Java 8](#)

### GET STARTED

```
git clone git@github.com:jazlopez/springboot-mq-client.git

# Cloning into 'springboot-mq-client'...
# remote: Enumerating objects: 26, done.
# remote: Counting objects: 100% (26/26), done.
# remote: Compressing objects: 100% (16/16), done.
# remote: Total 26 (delta 0), reused 23 (delta 0), pack-reused 0
# Receiving objects: 100% (26/26), done.
```

After successfully clone repository change directories to project

```
cd springboot-mq-client
```

### SETUP

Run an IBM MQ docker container in your system

```
docker-compose -f infra/infrastructure.yml up &
```

### INSTALLATION

```
mvn install
```

### RUN

```
mvn spring-boot:run &
```

While the application is running you can submit payloads and will be enqueued in IBM MQ queue.

```
# example
# publish a file (content) to queue
curl -v -X POST http://localhost:18888 -F file=@yourfile

```

### CONTRIBUTOR

[Jaziel Lopez](#)

### VERSION

1.0.0 Initial with limited support
      Enqueue message only. Not fetch/ delete queued messages
      Endpoint to enqueue message accept multipart/form data
      

