package com.Desafio2.controller;

import com.Desafio2.Model.domain.MensajeDTO;
import com.Desafio2.Model.domain.PersonaDTO;
import com.Desafio2.service.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacto")
public class PersonaController {
    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> findAll() {
        return ResponseEntity.ok(personaService.findAll());
    }

    @PostMapping
    public ResponseEntity<MensajeDTO> add(@RequestBody PersonaDTO personaDTO){
        return ResponseEntity.ok(personaService.add(personaDTO));
    }

    @GetMapping("/{personaid}")
    public ResponseEntity<PersonaDTO> findById(@PathVariable Integer personaid){
        return ResponseEntity.ok(personaService.findById(personaid));
    }
}
