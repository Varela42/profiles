package com.mx.marketplace.care.profiles.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "cs_role_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", nullable = false)
    private Role role;

    private String authority;
    private String name;
    private String description;

    @Column(name = "created_at")
    private Date createdAt;

    private Boolean active;
}
