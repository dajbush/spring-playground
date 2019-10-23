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
@WebMvcTest(MathController.class)
public class MathControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void testPi() throws Exception {
        RequestBuilder request = get("/math/pi");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("3.141592653589793"));
    }
}