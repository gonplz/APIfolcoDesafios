package com.Desafio2.Model.mappers;

import com.Desafio2.Model.domain.MensajeDTO;
import com.Desafio2.Model.domain.PersonaDTO;
import com.Desafio2.Model.entity.DireccionEntity;
import com.Desafio2.Model.entity.PersonaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonaMapper {

    private final DireccionMapper direccionMapper;

    public PersonaMapper(DireccionMapper direccionMapper) {
        this.direccionMapper = direccionMapper;
    }

    public PersonaEntity personaDTOToPersonaEntity (PersonaDTO personaDTO){
        PersonaEntity personaEntity = new PersonaEntity();
        personaEntity.setName(personaDTO.getName());
        personaEntity.setPhone(personaDTO.getPhone());
        personaEntity.setId(personaDTO.getId());
        DireccionEntity direccionEntity = direccionMapper.DireccionDTOToDireccionEntity(personaDTO.getDireccion());
        personaEntity.setDireccion(direccionEntity);
        return personaEntity;

    }
    public PersonaDTO personaEntityToPersonaDTO(PersonaEntity personaEntity){
        return Optional
                .ofNullable(personaEntity)
                .map(entity -> new PersonaDTO(entity.getId(),entity.getName(),entity.getPhone(),
                        direccionMapper.direccionEntityToDireccionDTO(personaEntity.getDireccion())))
                .orElse(new PersonaDTO());
    }

    public MensajeDTO personEntityToMensajeDTO(PersonaEntity personaEntity){
    return Optional
            .ofNullable(personaEntity)
            .map(entity -> new MensajeDTO(entity.getId()))
            .orElse(new MensajeDTO());
    }
}