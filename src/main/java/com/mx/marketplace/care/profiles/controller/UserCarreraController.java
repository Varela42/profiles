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
@RequestMapping("/api/public/user-carrera")
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

    // 🔹 Crear relación User–Carrera
    @PostMapping
    public UserCarrera create(@RequestParam Long userId, @RequestParam Long carreraId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrera carrera = carreraRepository.findById(carreraId)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));

        UserCarrera userCarrera = new UserCarrera();
        userCarrera.setUser(user);
        userCarrera.setCarrera(carrera);

        return userCarreraRepository.save(userCarrera);
    }

    // 🔹 Listar todas las relaciones
    @GetMapping
    public List<UserCarrera> getAll() {
        return userCarreraRepository.findAll();
    }

    // 🔹 Buscar por ID
    @GetMapping("/{id}")
    public UserCarrera getById(@PathVariable Long id) {
        return userCarreraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Relación no encontrada"));
    }

    // 🔹 Actualizar relación
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

    // 🔹 Eliminar relación
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userCarreraRepository.deleteById(id);
    }
}