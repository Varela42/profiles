package com.mx.marketplace.care.profiles.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "user_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", nullable = false, unique = true)
    private User user;

    private String name;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "second_lastname")
    private String secondLastName;

    @Column(name = "created_at")
    private Date createdAt;

    private Boolean active;
}
