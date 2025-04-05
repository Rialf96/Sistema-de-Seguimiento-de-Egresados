package com.universidad.egresados.service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universidad.egresados.model.OfertaEmpleo;
import com.universidad.egresados.repository.OfertaEmpleoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OfertaEmpleoService {
    @Autowired
    private OfertaEmpleoRepository repository;

    public List<OfertaEmpleo> listarTodas() {
        return repository.findAll();
    }

    public Optional<OfertaEmpleo> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public OfertaEmpleo guardar(OfertaEmpleo oferta) {
        System.out.println("Guardando en la BD: " + oferta); // Log para depuración
        return repository.save(oferta);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
