package com.Desafio2.service;

import com.Desafio2.domain.MensajeDTO;
import com.Desafio2.domain.PersonaDTO;
import com.Desafio2.entity.PersonaEntity;
import com.Desafio2.exceptions.PersonaNotFound;
import com.Desafio2.mappers.PersonaMapper;
import com.Desafio2.repositories.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    private final PersonaMapper personaMapper;

    public PersonaService(PersonaRepository personaRepository, PersonaMapper personaMapper){
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
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
            return Optional
                    .ofNullable(personaDTO)
                    .map(dto -> personaMapper.personaDTOToPersonaEntity(dto))
                    .map(entity -> personaRepository.save(entity))
                    .map(entity -> personaMapper.personEntityToMensajeDTO(entity))
                    .orElse(new MensajeDTO());
        } else if (personaname != null) {
            throw new PersonaNotFound("El nombre ya está utilizado");
        } else if (personaphone != null) {
            throw new PersonaNotFound("El número ya está utilizado");
        } else {return null;}
    }
}

 //   public PersonaDTO findOne(Integer id){
   //     return Optional
     //           .ofNullable(id)
       //         .map(ID ->personaRepository.findById(id).get())
         //       .map(entity ->personaMapper.personaEntityToPersonaDTO(entity))
           //     .orElse(new PersonaDTO());}


