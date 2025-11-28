package com.mx.marketplace.care.profiles.controller;

import com.mx.marketplace.care.profiles.model.entity.Carrera;
import com.mx.marketplace.care.profiles.repository.CarreraRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    private final CarreraRepository carreraRepository;

    // Inyección por constructor (buena práctica)
    public CarreraController(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    // -------------------------------
    // ✔ GET: Obtener todas las carreras
    // -------------------------------
    @GetMapping
    public ResponseEntity<List<Carrera>> getAllCarreras() {
        return ResponseEntity.ok(carreraRepository.findAll());
    }

    // -------------------------------
    // ✔ GET por ID
    // -------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getCarreraById(@PathVariable Long id) {
        return carreraRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -------------------------------
    // ✔ POST: Crear carrera
    // -------------------------------
    @PostMapping
    public ResponseEntity<Carrera> createCarrera(@RequestBody Carrera carrera) {
        Carrera saved = carreraRepository.save(carrera);
        return ResponseEntity.ok(saved);
    }

    // -------------------------------
    // ✔ PUT: Actualizar carrera
    // -------------------------------
    @PutMapping("/{id}")
    public ResponseEntity<Carrera> updateCarrera(
            @PathVariable Long id,
            @RequestBody Carrera details) {

        return carreraRepository.findById(id)
                .map(carrera -> {
                    carrera.setDescripcion(details.getDescripcion());
                    carrera.setEstado(details.getEstado());
                    carreraRepository.save(carrera);
                    return ResponseEntity.ok(carrera);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // -------------------------------
    // ✔ DELETE: Eliminar carrera
    // -------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable Long id) {

        if (!carreraRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        carreraRepository.deleteById(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}
