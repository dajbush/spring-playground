package com.example.demo;

import javax.validation.Valid;

import com.example.helper.MathHelper;
import com.example.ro.AreaBody;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    private MathHelper mathHelper = new MathHelper();
    
    @GetMapping("/pi")
    public String pi() {
        return "3.141592653589793";
    }

    @GetMapping("/calculate")
    public String calculate(@RequestParam(required = false) String operation,
                            @RequestParam int x,
                            @RequestParam int y) {
            return mathHelper.handleOperation(operation, x, y);
    }

    @PostMapping("/sum")
    public String sum(@RequestParam MultiValueMap<String, String> queryString) {
        if(queryString.get("n") != null && queryString.get("n").size() > 1) {
            return mathHelper.handleSum(queryString.get("n"));
        }
        return "Bad Request";
    }

    @RequestMapping("/volume/{length}/{width}/{height}")
    public String volume(@PathVariable int length, @PathVariable int width, @PathVariable int height) {
        return mathHelper.handleVolume(length, width, height);
    }

    @PostMapping(value="/area", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String area(@Valid AreaBody body) {
        if(body.getType().equalsIgnoreCase("circle")
            && body.getRadius() != 0
            && body.getWidth() == 0
            && body.getHeight() == 0) 
        {
            return mathHelper.handleCircleArea(body.getRadius());
        } 
        else if(body.getType().equalsIgnoreCase("rectangle")
                && body.getRadius() == 0
                && body.getWidth() != 0
                && body.getHeight() != 0) 
        {
            return mathHelper.handleRectangleArea(body.getWidth(), body.getHeight());
        }
        else {
            return "Invalid";
        }
    }
}