package com.uncompa.apirest.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uncompa.apirest.apirest.entities.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByUsername(String username);
}
