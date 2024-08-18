package com.uncompa.apirest.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uncompa.apirest.apirest.entities.Nota;
import com.uncompa.apirest.apirest.repository.NotaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    // Crear una nueva nota
    public Nota crearNota(Nota nota) {
        return notaRepository.save(nota);
    }

    // Leer todas las notas
    public List<Nota> obtenerNotas() {
        return notaRepository.findAll();
    }

    // Leer una nota por ID
    public Optional<Nota> obtenerNotaPorId(Long id) {
        return notaRepository.findById(id);
    }

    // Actualizar una nota existente
    public Nota actualizarNota(Long id, Nota nuevaNota) {
        return notaRepository.findById(id)
            .map(nota -> {
                nota.setFecha(nuevaNota.getFecha());
                nota.setTitulo(nuevaNota.getTitulo());
                nota.setContenido(nuevaNota.getContenido());
                nota.setUsername(nuevaNota.getUsername());
                return notaRepository.save(nota);
            }).orElseGet(() -> {
                nuevaNota.setId(id);
                return notaRepository.save(nuevaNota);
            });
    }

    // Eliminar una nota por ID
    public void eliminarNota(Long id) {
        notaRepository.deleteById(id);
    }
}
