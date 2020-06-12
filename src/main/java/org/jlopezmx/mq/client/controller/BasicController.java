package org.jlopezmx.mq.client.controller;

import org.jlopezmx.mq.client.ClientApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.Message;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@EnableJms
public class BasicController {

    private static Logger LOG = LoggerFactory.getLogger(ClientApplication.class);

    @Value("${ibm.mq.queueManager}")
    String queueManager;

    @Value("${ibm.mq.channel}")
    String channel;

    @Value("{ibm.mq.connName}")
    String connection;

    @Value("{ibm.mq.user}")
    String user;

    @Value("${ibm.mq.password}")
    String password;

    @Value("${application.notification-queue.name}")
    String queue;

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/")
    @ResponseBody
    String send(@RequestParam("file")MultipartFile xml) throws Exception {

        /////////////////
        String output;
        String payload;


        if(queue.isEmpty())
            throw new RuntimeException("Queue name cannot be null. " +
                    "Must be defined in application.properties");

        if(xml.isEmpty())
            throw new RuntimeException("xml cannot be null." +
                    "Must be uploaded");

        payload = new String(xml.getBytes());


        if(payload.isEmpty())
            throw new RuntimeException("payload cannot be empty. " +
                    "Must be defined as body param: payload=yourpayload");

        LOG.info("payload:\n%s", payload);
        //////////////

        AtomicReference<Message> message = new AtomicReference<>();

        try{

            LOG.info(String.format("Received payload: %s", payload));

            jmsTemplate.convertAndSend(queue, payload, messagePostProcessor -> {

                message.set(messagePostProcessor);

                return messagePostProcessor;
            });

            output = String.format("Message sent! ID: %s", message.get().getJMSMessageID());

            LOG.info(output);

            return output;

        }catch(JmsException ex){

            ex.printStackTrace();

            return "FAIL";
        }
    }

    @GetMapping("recv")
    String recv(){

        try{
            return jmsTemplate.receiveAndConvert(queue).toString();
        }catch(JmsException ex){
            ex.printStackTrace();
            return "FAIL";
        }

    }

    @GetMapping("config")
    String config(){

        LOG.info(String.format("INFORMATION ABOUT QUEUE: %s", queue));

        return String
                .format("Queue manager: %s <br/>" +
                        "Queue channel: %s <br/>" +
                        "Queue connection name: %s <br/>" +
                        "Queue user: %s <br/>" +
                        "Queue password: %s <br/>" +
                        "Queue name: %s <br/>" ,
                    queueManager,
                    channel,
                    connection,
                    user,
                    password,
                    queue);
    }


}
