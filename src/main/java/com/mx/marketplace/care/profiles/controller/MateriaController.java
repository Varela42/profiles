package com.mx.marketplace.care.profiles.controller;

import com.mx.marketplace.care.profiles.model.entity.Materia;
import com.mx.marketplace.care.profiles.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/materias")  // Ruta organizada y permitida por seguridad
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepository;

    // ✔ LISTAR TODAS
    @GetMapping
    public List<Materia> listar() {
        return materiaRepository.findAll();
    }

    // ✔ CREAR
    @PostMapping
    public Materia crear(@RequestBody Materia materia) {
        return materiaRepository.save(materia);
    }

    // ✔ OBTENER POR ID
    @GetMapping("/{id}")
    public Materia obtener(@PathVariable Long id) {
        return materiaRepository.findById(id).orElse(null);
    }

    // ✔ ACTUALIZAR
    @PutMapping("/{id}")
    public Materia actualizar(@PathVariable Long id, @RequestBody Materia datos) {
        Materia materia = materiaRepository.findById(id).orElse(null);
        if (materia == null) {
            return null;
        }

        materia.setDescripcion(datos.getDescripcion());
        materia.setEstado(datos.getEstado());

        return materiaRepository.save(materia);
    }

    // ✔ ELIMINAR
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        materiaRepository.deleteById(id);
    }
}
