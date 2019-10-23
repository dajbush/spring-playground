package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;


@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HelloControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void testHomepage() throws Exception {
        RequestBuilder request = get("/hello");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("Hello world!"));
    }
}