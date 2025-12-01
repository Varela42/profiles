package com.mx.marketplace.care.profiles.controller;

import com.mx.marketplace.care.profiles.model.entity.User;
import com.mx.marketplace.care.profiles.model.entity.Carrera;
import com.mx.marketplace.care.profiles.model.entity.UserCarrera;
import com.mx.marketplace.care.profiles.repository.UserCarreraRepository;
import com.mx.marketplace.care.profiles.repository.UserRepository;
import com.mx.marketplace.care.profiles.repository.CarreraRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-carrera")
public class UserCarreraController {

    private final UserCarreraRepository userCarreraRepository;
    private final UserRepository userRepository;
    private final CarreraRepository carreraRepository;

    public UserCarreraController(UserCarreraRepository userCarreraRepository,
                                 UserRepository userRepository,
                                 CarreraRepository carreraRepository) {
        this.userCarreraRepository = userCarreraRepository;
        this.userRepository = userRepository;
        this.carreraRepository = carreraRepository;
    }

    // 🔹 CREAR RELACIÓN USER-CARRERA
    @PostMapping
    public UserCarrera create(@RequestParam Long userId, @RequestParam Long carreraId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrera carrera = carreraRepository.findById(carreraId)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        UserCarrera uc = new UserCarrera();
        uc.setUser(user);
        uc.setCarrera(carrera);

        return userCarreraRepository.save(uc);
    }

    // 🔹 OBTENER TODAS LAS RELACIONES
    @GetMapping
    public List<UserCarrera> getAll() {
        return userCarreraRepository.findAll();
    }

    // 🔹 OBTENER UNA RELACIÓN POR ID
    @GetMapping("/{id}")
    public UserCarrera getById(@PathVariable Long id) {
        return userCarreraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relación no encontrada"));
    }

    // 🔹 ACTUALIZAR RELACIÓN
    @PutMapping("/{id}")
    public UserCarrera update(@PathVariable Long id,
                              @RequestParam Long userId,
                              @RequestParam Long carreraId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrera carrera = carreraRepository.findById(carreraId)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        return userCarreraRepository.findById(id)
                .map(existing -> {
                    existing.setUser(user);
                    existing.setCarrera(carrera);
                    return userCarreraRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Relación no encontrada"));
    }

    // 🔹 ELIMINAR RELACIÓN
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userCarreraRepository.deleteById(id);
    }
}