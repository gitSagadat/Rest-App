package io.saga.RestApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sagadat Kuandykov
 */
@RestController   // @RestController its @Controller + ResponseBody in every scope(area) here;
@RequestMapping("/api")
public class FirstRESTController {

    @ResponseBody
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Rest";
    }

}
