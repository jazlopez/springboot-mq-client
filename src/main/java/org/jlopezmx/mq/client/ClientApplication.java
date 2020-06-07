package org.jlopezmx.mq.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableJms
public class ClientApplication {


    private static Logger LOG = LoggerFactory.getLogger(ClientApplication.class);

    @Autowired
    private JmsTemplate jmsTemplate;

	public static void main(String[] args) {

		SpringApplication.run(ClientApplication.class, args);
	}

	@PostMapping("/")
    @ResponseBody
    String send(@RequestParam Map<String,String> allParams) {

        try{

            String payload = allParams.get("payload");

            LOG.info(String.format("Received payload: %s", payload));

            jmsTemplate.convertAndSend("DEV.QUEUE.1", payload);

            return String.format("Enqueue payload: %s", payload);

        }catch(JmsException ex){

            ex.printStackTrace();

            return "FAIL";
        }
    }

    @GetMapping("recv")
    String recv(){
        try{
            return jmsTemplate.receiveAndConvert("DEV.QUEUE.1").toString();
        }catch(JmsException ex){
            ex.printStackTrace();
            return "FAIL";
        }
    }

}
