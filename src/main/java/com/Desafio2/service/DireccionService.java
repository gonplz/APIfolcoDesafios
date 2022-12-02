package com.Desafio2.service;

import com.Desafio2.domain.DireccionDTO;
import com.Desafio2.domain.PersonaDTO;
import com.Desafio2.entity.DireccionEntity;
import com.Desafio2.mappers.DireccionMapper;
import com.Desafio2.repositories.DireccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DireccionService {
    private final DireccionMapper direccionMapper;

    private final DireccionRepository direccionRepository;

    private final PersonaService personaService;

    public DireccionService(DireccionMapper direccionMapper, DireccionRepository direccionRepository, PersonaService personaService) {
        this.direccionMapper = direccionMapper;
        this.direccionRepository = direccionRepository;
        this.personaService = personaService;

}

    public DireccionDTO add(DireccionDTO direccionDTO){

        return Optional
                .ofNullable(direccionDTO)
                .map(direccionDTO1 -> direccionMapper.DireccionDTOToDireccionEntity(direccionDTO1))
                .map(direccionRepository::save)
                .map(direccionMapper::direccionEntityToDireccionDTO)
                .orElse(new DireccionDTO());
    }
    public List<DireccionDTO> findAll(){
    return direccionRepository
            .findAll()
            .stream()
            .map(direccionEntity -> {System.out.println();return direccionMapper.direccionEntityToDireccionDTO(direccionEntity);})
            .collect(Collectors.toList());
}
}
