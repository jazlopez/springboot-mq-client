# springboot-mq-client
springboot-mq-client

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
curl -X POST http://localhost:8080 -d "payload=demopayload"

# 2020-06-07 16:15:44.548  INFO 27434 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
# 2020-06-07 16:15:44.548  INFO 27434 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
# 2020-06-07 16:15:44.552  INFO 27434 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 4 ms
# 2020-06-07 16:15:44.568  INFO 27434 --- [nio-8080-exec-1] o.jlopezmx.mq.client.ClientApplication   : Received payload: demopayload
# Enqueue payload: demopayload%
```

### CONTRIBUTOR

[Jaziel Lopez](#)

### VERSION

1.0.0 Initial with limited support
      Enqueue message only. Not fetch/ delete queued messages
      Endpoint to enqueue message accept multipart/form data
      

