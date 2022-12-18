package com.Desafio2.controller;

import com.Desafio2.Model.domain.ListaDTO;
import com.Desafio2.Model.domain.ListaDireDTO;
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

    @DeleteMapping
    public ResponseEntity deletePersonas(){
        personaService.eliminarPersonas();
        return ResponseEntity.ok().build();

    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletePersona(@PathVariable Integer id){
        personaService.eliminarPersona(id);
        return ResponseEntity.ok().build();

    }
    @PutMapping
    public void update(@PathVariable Integer id, @RequestBody PersonaDTO personaDTO){
        personaService.agregar(id, personaDTO);

    }

    @GetMapping("/{personaid}")
    public ResponseEntity<PersonaDTO> findById(@PathVariable Integer personaid){
        return ResponseEntity.ok(personaService.findById(personaid));
    }

    @GetMapping("/contactos")
    public ResponseEntity<ListaDTO> findByContacts (@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                    @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                    @RequestParam String name,
                                                    @RequestParam String phone){
        return ResponseEntity.ok(personaService.findAllContactos(pageNumber,pageSize,name,phone));
    }

    @GetMapping("/direc")
    public ResponseEntity<ListaDireDTO> findFiltro(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize,
                                                   @RequestParam String calle,
                                                   @RequestParam Float altura){
        return ResponseEntity.ok(personaService.findFiltro(pageNumber,pageSize,calle,altura));
    }

    @GetMapping("/direc/{id}/contac")
    public ResponseEntity<ListaDTO> getContactByDomicilio(@PathVariable Integer id){
        return ResponseEntity.ok(personaService.getContactByDomicilio(id));
    }

}
