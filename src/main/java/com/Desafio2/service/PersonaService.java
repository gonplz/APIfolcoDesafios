package com.Desafio2.service;

import com.Desafio2.Model.domain.MensajeDTO;
import com.Desafio2.Model.domain.PersonaDTO;
import com.Desafio2.Model.entity.PersonaEntity;
import com.Desafio2.exceptions.Kinds.NumeroNotFound;
import com.Desafio2.exceptions.Kinds.PersonaNotFound;
import com.Desafio2.Model.mappers.PersonaMapper;
import com.Desafio2.Model.repositories.PersonaRepository;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    private final PersonaMapper personaMapper;

    private DireccionService direccionService;

    public PersonaService(PersonaRepository personaRepository, PersonaMapper personaMapper, DireccionService direccionService){
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
        this.direccionService = direccionService;
    }

    public List<PersonaDTO> findAll(){
        return personaRepository
                .findAll()
                .stream()
                .map(personaEntity -> personaMapper.personaEntityToPersonaDTO(personaEntity))
                .collect(Collectors.toList());
    }
    public PersonaDTO findById(Integer personaId){
        return personaRepository
                .findById(personaId)
                .map(personaEntity -> personaMapper.personaEntityToPersonaDTO(personaEntity))
                .orElseThrow(()->new ProviderNotFoundException("No se encuentra a la persona"));
    }

    public MensajeDTO add(PersonaDTO personaDTO) {
        PersonaEntity personaname = personaRepository.findByName(personaDTO.getName());
        PersonaEntity personaphone = personaRepository.findByPhone(personaDTO.getPhone());
        if (personaname == null && personaphone == null) {
            PersonaEntity Entity = personaMapper.personaDTOToPersonaEntity(personaDTO);
            Entity.setDireccion(direccionService.add(personaDTO.getDireccion()));
            personaRepository.save(Entity);
            return personaMapper.personEntityToMensajeDTO(Entity);
            /*return Optional
                    .ofNullable(personaDTO)
                    .map(dto -> personaMapper.personaDTOToPersonaEntity(dto))
                    .map(entity -> personaRepository.save(entity))
                    .map(entity -> personaMapper.personEntityToMensajeDTO(entity))
                    .orElse(new MensajeDTO());*/

        } else if (personaname != null) {
            throw new PersonaNotFound("El nombre ya está utilizado");
        } else if (personaphone != null) {
            throw new NumeroNotFound("El número ya está utilizado");
        } else {return null;}
    }
}

 //   public PersonaDTO findOne(Integer id){
   //     return Optional
     //           .ofNullable(id)
       //         .map(ID ->personaRepository.findById(id).get())
         //       .map(entity ->personaMapper.personaEntityToPersonaDTO(entity))
           //     .orElse(new PersonaDTO());}


