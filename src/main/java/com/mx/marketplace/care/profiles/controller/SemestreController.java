package com.mx.marketplace.care.profiles.controller;

import com.mx.marketplace.care.profiles.model.entity.Semestre;
import com.mx.marketplace.care.profiles.repository.SemestreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/semestres")  // RUTA UNIFICADA y permitida en SecurityConfig
public class SemestreController {

    @Autowired
    private SemestreRepository semestreRepository;

    // ✔ Listar todos
    @GetMapping
    public List<Semestre> listar() {
        return semestreRepository.findAll();
    }

    // ✔ Crear
    @PostMapping
    public Semestre crear(@RequestBody Semestre semestre) {
        return semestreRepository.save(semestre);
    }

    // ✔ Obtener por ID
    @GetMapping("/{id}")
    public Semestre obtener(@PathVariable Long id) {
        return semestreRepository.findById(id).orElse(null);
    }

    // ✔ Actualizar
    @PutMapping("/{id}")
    public Semestre actualizar(@PathVariable Long id, @RequestBody Semestre datos) {
        Semestre semestre = semestreRepository.findById(id).orElse(null);
        if (semestre == null) return null;

        semestre.setDescripcion(datos.getDescripcion());
        semestre.setEstado(datos.getEstado());

        return semestreRepository.save(semestre);
    }

    // ✔ Eliminar
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        semestreRepository.deleteById(id);
    }
}