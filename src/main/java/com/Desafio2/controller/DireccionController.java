package com.Desafio2.controller;

import com.Desafio2.Model.domain.DireccionDTO;
import com.Desafio2.service.DireccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direcciones")
public class DireccionController {

    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService){this.direccionService = direccionService;}

    @GetMapping
    public ResponseEntity<List<DireccionDTO>> findAll(){return ResponseEntity.ok(direccionService.findAll());}

    @PostMapping
    public ResponseEntity<DireccionDTO> add(@RequestBody DireccionDTO direccionDTO){
        return ResponseEntity.ok(direccionService.add(direccionDTO));}
}
