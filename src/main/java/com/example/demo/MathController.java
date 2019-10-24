package com.example.demo;

import com.example.helper.MathHelper;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    private MathHelper mathHelper = new MathHelper();
    
    @GetMapping("/math/pi")
    public String pi() {
        return "3.141592653589793";
    }

    @GetMapping("/math/calculate")
    public String calculate(@RequestParam(required = false) String operation,
                            @RequestParam int x,
                            @RequestParam int y) {
            return mathHelper.handleOperation(operation, x, y);
    }

    @PostMapping("/math/sum")
    public String sum(@RequestParam MultiValueMap<String, String> queryString) {
        if(queryString.get("n") != null && queryString.get("n").size() > 1) {
            return mathHelper.handleSum(queryString.get("n"));
        }
        return "Bad Request";
    }
}