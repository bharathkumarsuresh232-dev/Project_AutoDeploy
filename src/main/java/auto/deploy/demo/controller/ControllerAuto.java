package auto.deploy.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ControllerAuto {

    @GetMapping("/health")
    public String health() {
        return "Application Running Successfully";
    }

}
