package org.jlopezmx.mq.client.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
// import static org.hamcrest.Matchers.is;
// import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BasicController.class)
public class BasicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JmsTemplate jmsTemplate;

    @Test
    public void configTest() throws Exception{

        mockMvc.perform(get("/config"))
                .andExpect(status().isOk())
                .andExpect(view().name("config"))
                .andExpect(content().string(containsString("queue.manager")));

    }
}
