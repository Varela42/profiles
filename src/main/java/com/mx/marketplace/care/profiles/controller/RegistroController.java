package com.mx.marketplace.care.profiles.controller;

import com.mx.marketplace.care.profiles.model.entity.Registro;
import com.mx.marketplace.care.profiles.model.entity.Direccion;
import com.mx.marketplace.care.profiles.repository.RegistroRepository;
import com.mx.marketplace.care.profiles.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/registros")   // RUTA PÚBLICA
public class RegistroController {

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private DireccionRepository direccionRepository;

    // ✔ LISTAR REGISTROS
    @GetMapping
    public List<Registro> listar() {
        return registroRepository.findAll();
    }

    // ✔ CREAR REGISTRO
    @PostMapping
    public Registro crear(@RequestBody Registro registro) {

        // Validar direccion
        Direccion direccion = direccionRepository.findById(registro.getDireccion().getId())
                .orElse(null);

        if (direccion == null) {
            throw new RuntimeException("La dirección no existe");
        }

        registro.setDireccion(direccion);
        return registroRepository.save(registro);
    }

    // ✔ OBTENER POR ID
    @GetMapping("/{id}")
    public Registro obtener(@PathVariable Long id) {
        return registroRepository.findById(id).orElse(null);
    }

    // ✔ ACTUALIZAR REGISTRO
    @PutMapping("/{id}")
    public Registro actualizar(@PathVariable Long id, @RequestBody Registro datos) {

        Registro registro = registroRepository.findById(id).orElse(null);
        if (registro == null) return null;

        registro.setNombre(datos.getNombre());
        registro.setApellidop(datos.getApellidop());
        registro.setApellidom(datos.getApellidom());
        registro.setFechar(datos.getFechar());
        registro.setFechan(datos.getFechan());
        registro.setEdad(datos.getEdad());
        registro.setGenero(datos.getGenero());
        registro.setCorreo(datos.getCorreo());
        registro.setTelefono(datos.getTelefono());
        registro.setRol(datos.getRol());

        // Si se envía una nueva dirección
        if (datos.getDireccion() != null) {
            Direccion direccion = direccionRepository.findById(datos.getDireccion().getId())
                    .orElse(null);
            if (direccion != null) {
                registro.setDireccion(direccion);
            }
        }

        return registroRepository.save(registro);
    }

    // ✔ ELIMINAR
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        registroRepository.deleteById(id);
    }
}