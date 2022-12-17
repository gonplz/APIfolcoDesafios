package com.Desafio2.service;

import com.Desafio2.Model.domain.DireccionDTO;
import com.Desafio2.Model.entity.DireccionEntity;
import com.Desafio2.Model.mappers.DireccionMapper;
import com.Desafio2.Model.repositories.DireccionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DireccionService {
    private final DireccionMapper direccionMapper;

    private final DireccionRepository direccionRepository;


    public DireccionService(DireccionMapper direccionMapper, DireccionRepository direccionRepository) {
        this.direccionMapper = direccionMapper;
        this.direccionRepository = direccionRepository;

}
    public DireccionEntity add(DireccionDTO direccionDTO){

        return Optional
                .ofNullable(direccionDTO)
                .map(direccionDTO1 -> direccionMapper.DireccionDTOToDireccionEntity(direccionDTO1))
                .map(entity-> direccionRepository.save(entity))
                .orElse(new DireccionEntity());
    }
    public List<DireccionDTO> findAll(){
    return direccionRepository
            .findAll()
            .stream()
            .map(direccionEntity -> {System.out.println();return direccionMapper.direccionEntityToDireccionDTO(direccionEntity);})
            .collect(Collectors.toList());
}
}
