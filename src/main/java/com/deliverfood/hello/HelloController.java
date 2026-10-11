package com.deliverfood.hello;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public Person sayHello() {
        return new Person(
                "Eddy",
                "Example Inc",
                new Address("123 Happy Street", "San Francisco", "California", "US"),
                new Book("Clean Code", "John Smith")
        );
    }
}
