package com.Desafio2.controller;

import com.Desafio2.domain.MensajeDTO;
import com.Desafio2.domain.PersonaDTO;
import com.Desafio2.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
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
    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> findOne(@PathVariable Integer id) {
        return ResponseEntity.ok(personaService.findOne(id));
    }
}
