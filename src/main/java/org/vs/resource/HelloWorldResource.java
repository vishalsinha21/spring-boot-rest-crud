package org.vs.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {

    @RequestMapping(value = "/hello")
    public String sayHello() {
        return "Hello World!";
    }
}
