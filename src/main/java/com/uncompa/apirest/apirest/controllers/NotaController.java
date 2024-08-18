package com.uncompa.apirest.apirest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uncompa.apirest.apirest.entities.Nota;
import com.uncompa.apirest.apirest.services.NotaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    // Crear una nueva nota
    @PostMapping
    public Nota crearNota(@RequestBody Nota nota) {
        return notaService.crearNota(nota);
    }

    // Obtener todas las notas
    @GetMapping
    public List<Nota> obtenerNotas() {
        return notaService.obtenerNotas();
    }

    // Obtener una nota por ID
    @GetMapping("/{id}")
    public ResponseEntity<Nota> obtenerNotaPorId(@PathVariable Long id) {
        Optional<Nota> nota = notaService.obtenerNotaPorId(id);
        return nota.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar una nota existente
    @PutMapping("/{id}")
    public ResponseEntity<Nota> actualizarNota(@PathVariable Long id, @RequestBody Nota nuevaNota) {
        Optional<Nota> nota = notaService.obtenerNotaPorId(id);
        if (nota.isPresent()) {
            return ResponseEntity.ok(notaService.actualizarNota(id, nuevaNota));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una nota por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNota(@PathVariable Long id) {
        if (notaService.obtenerNotaPorId(id).isPresent()) {
            notaService.eliminarNota(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
