package com.mx.marketplace.care.profiles.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class MainController {


    @GetMapping("/public/hello")
    ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Carga materia publico");
    }

    @GetMapping("/private/hello")
    ResponseEntity<String> sayHelloTwice(){
        return ResponseEntity.ok("Carga carrera privada");
    }
}
