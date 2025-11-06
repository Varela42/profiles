package com.mx.marketplace.care.profiles.controller;

import com.mx.marketplace.care.profiles.dto.HomeDTO;
import com.mx.marketplace.care.profiles.service.HomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api")
@RestController
public class HomeController {

    private HomeService homeService;

    @GetMapping("/private/home")
    ResponseEntity<HomeDTO> gethomeData(@RequestParam("userId") Long userId){
        return ResponseEntity.ok(homeService.getHomeData(userId));
    }
}
