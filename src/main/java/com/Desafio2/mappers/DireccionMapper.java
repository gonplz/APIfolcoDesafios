package com.Desafio2.mappers;

import com.Desafio2.domain.DireccionDTO;
import com.Desafio2.domain.PersonaDTO;
import com.Desafio2.entity.DireccionEntity;
import com.Desafio2.entity.PersonaEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DireccionMapper {

    public DireccionEntity DireccionDTOToDireccionEntity (DireccionDTO direccionDTO){
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setAltura(direccionDTO.getAltura());
        direccionEntity.setCalle(direccionDTO.getCalle());

        return direccionEntity;
    }
    public DireccionDTO direccionEntityToDireccionDTO (DireccionEntity direccionEntity){
        return Optional
                .ofNullable(direccionEntity)
                .map(entity -> new DireccionDTO(
                        entity.getId(),
                        entity.getCalle(),
                        entity.getAltura()))
                .orElse(new DireccionDTO());
    }
}
