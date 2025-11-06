package com.mx.marketplace.care.profiles.dto;

import lombok.Data;

import java.util.List;

@Data
public class HomeDTO {
    private String name;
    private String lastName;
    private String course;
    private String semester;
    private String role;
    private List<String> permissions;
}
