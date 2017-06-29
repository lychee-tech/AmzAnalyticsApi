package com.leechi.amz.analytics.features.hello;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloController {
    @RequestMapping(value="/",method= RequestMethod.GET)
    public String greeting(){
        return "hello";
    }
}
