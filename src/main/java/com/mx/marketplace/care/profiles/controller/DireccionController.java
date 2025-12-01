package com.mx.marketplace.care.profiles.controller;

import com.mx.marketplace.care.profiles.model.entity.Direccion;
import com.mx.marketplace.care.profiles.model.entity.User;
import com.mx.marketplace.care.profiles.repository.DireccionRepository;
import com.mx.marketplace.care.profiles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/direcciones")  // Ruta pública
public class DireccionController {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private UserRepository userRepository;

    // ✔ Listar todas las direcciones
    @GetMapping
    public List<Direccion> listar() {
        return direccionRepository.findAll();
    }

    // ✔ Crear dirección (requiere id_user)
    @PostMapping
    public Direccion crear(@RequestBody Direccion direccion) {

        // Verificar si el usuario existe
        User usuario = userRepository.findById(direccion.getUser().getId())
                .orElse(null);

        if (usuario == null) {
            throw new RuntimeException("El usuario con ese ID no existe");
        }

        direccion.setUser(usuario);
        return direccionRepository.save(direccion);
    }

    // ✔ Obtener por ID
    @GetMapping("/{id}")
    public Direccion obtener(@PathVariable Long id) {
        return direccionRepository.findById(id).orElse(null);
    }

    // ✔ Actualizar
    @PutMapping("/{id}")
    public Direccion actualizar(@PathVariable Long id, @RequestBody Direccion datos) {
        Direccion direccion = direccionRepository.findById(id).orElse(null);
        if (direccion == null) return null;

        direccion.setCalle(datos.getCalle());
        direccion.setNumeroint(datos.getNumeroint());
        direccion.setNumeroext(datos.getNumeroext());
        direccion.setColonia(datos.getColonia());
        direccion.setCiudad(datos.getCiudad());
        direccion.setEstado(datos.getEstado());
        direccion.setCp(datos.getCp());

        // ⚠ Si se envía un nuevo usuario, validarlo
        if (datos.getUser() != null) {
            User usuario = userRepository.findById(datos.getUser().getId())
                    .orElse(null);

            if (usuario != null) {
                direccion.setUser(usuario);
            }
        }

        return direccionRepository.save(direccion);
    }

    // ✔ Eliminar
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        direccionRepository.deleteById(id);
    }
}