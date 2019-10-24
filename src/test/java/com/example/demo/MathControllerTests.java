package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;


@RunWith(SpringRunner.class)
@WebMvcTest(MathController.class)
public class MathControllerTests {

    @Autowired
    MockMvc mvc;

    @Test
    public void testAreaForCircle() throws Exception {
        RequestBuilder request = post("/math/area")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("type", "circle")
                                .param("radius", "4");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));
    }

    @Test
    public void testAreaForRectangle() throws Exception {
        RequestBuilder request = post("/math/area")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("type", "rectangle")
                                .param("width", "4")
                                .param("height", "7");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("Area of a 4x7 rectangle is 28"));
    }

    @Test
    public void testAreaForInvalidRequest() throws Exception {
        RequestBuilder request = post("/math/area")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("type", "rectangle")
                                .param("radius", "4");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("Invalid"));
    }

    @Test
    public void testAreaForNoParameters() throws Exception {
        RequestBuilder request = post("/math/area")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED);
        this.mvc.perform(request)
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testVolumeWithNoParameters() throws Exception {
        RequestBuilder request = get("/math/volume");
        this.mvc.perform(request)
            .andExpect(status().isNotFound());
    }

    @Test
    public void testVolumeWithTwoParameters() throws Exception {
        RequestBuilder request = get(String.format("/math/volume/%d/%d", 3, 5));
        this.mvc.perform(request)
            .andExpect(status().isNotFound());
    }

    @Test
    public void testVolumeWithTwoParametersAndNull() throws Exception {
        RequestBuilder request = get(String.format("/math/volume/%d/%d/%d", 3, 5, null));
        this.mvc.perform(request)
            .andExpect(status().isBadRequest());
    }

    @Test
    public void testVolume() throws Exception {
        RequestBuilder request = get(String.format("/math/volume/%d/%d/%d", 6, 7, 8));
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("The volume of a 6x7x8 rectangle is 336"));
    }

    @Test
    public void testPi() throws Exception {
        RequestBuilder request = get("/math/pi");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("3.141592653589793"));
    }

    @Test
    public void testCalculateAdd() throws Exception {
        RequestBuilder request = get("/math/calculate")
                                .param("operation", "add")
                                .param("x", "24")
                                .param("y", "3");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("24 + 3 = 27"));
    }

    @Test
    public void testCalculateSubtract() throws Exception {
        RequestBuilder request = get("/math/calculate")
                                .param("operation", "subtract")
                                .param("x", "24")
                                .param("y", "3");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("24 - 3 = 21"));
    }

    @Test
    public void testCalculateMultiply() throws Exception {
        RequestBuilder request = get("/math/calculate")
                                .param("operation", "multiply")
                                .param("x", "24")
                                .param("y", "3");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("24 * 3 = 72"));
    }

    @Test
    public void testCalculateDivide() throws Exception {
        RequestBuilder request = get("/math/calculate")
                                .param("operation", "divide")
                                .param("x", "24")
                                .param("y", "3");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("24 / 3 = 8"));
    }

    @Test
    public void testCalculateWithNoOperationGiven() throws Exception {
        RequestBuilder request = get("/math/calculate")
                                .param("x", "24")
                                .param("y", "3");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("24 + 3 = 27"));
    }

    @Test
    public void testCalculateWithNoParametersGiven() throws Exception {
        RequestBuilder request = get("/math/calculate");
        this.mvc.perform(request)
            .andExpect(status().isBadRequest());
    }    

    @Test
    public void testSumWith0Parameters() throws Exception {
        RequestBuilder request = post("/math/sum");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("Bad Request"));
    }

    @Test
    public void testSumWith1Parameter() throws Exception {
        RequestBuilder request = post("/math/sum")
                                .param("n", "3");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("Bad Request"));
    }

    @Test
    public void testSumWithMultipleParameters() throws Exception {
        RequestBuilder request = post("/math/sum")
                                .param("n", "3")
                                .param("n", "5")
                                .param("n", "7");
        this.mvc.perform(request)
            .andExpect(status().isOk())
            .andExpect(content().string("3 + 5 + 7 = 15"));
    }
}