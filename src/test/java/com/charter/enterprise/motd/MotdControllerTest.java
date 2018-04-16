package com.charter.enterprise.motd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MotdControllerTest {
    @Autowired
    private MockMvc mvc;


    @Test
    public void getMotd() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Welcome to Charter.  All systems are nominal.")));
    }

    @Test
    public void setMotdBadString() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/").param("message", ""))
        .andExpect(status().is(400)).andExpect(content().string((equalTo("Message cannot be set to null or an empty string."))));
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Welcome to Charter.  All systems are nominal.")));
    }

    @Test
    public void setMotd() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/").param("message", "Goodbye world!"));
        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Goodbye world!")));
    }

    @Test
    public void resetMotd() throws Exception{
        mvc.perform(MockMvcRequestBuilders.put("/").param("message", "Goodbye world!"));
        mvc.perform(MockMvcRequestBuilders.put("/").param("message", "reset"));
        getMotd();
    }
}
