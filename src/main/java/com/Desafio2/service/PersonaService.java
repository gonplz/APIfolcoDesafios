package com.Desafio2.service;

import com.Desafio2.Model.domain.ListaDTO;
import com.Desafio2.Model.domain.ListaDireDTO;
import com.Desafio2.Model.domain.MensajeDTO;
import com.Desafio2.Model.domain.PersonaDTO;
import com.Desafio2.Model.entity.DireccionEntity;
import com.Desafio2.Model.entity.PersonaEntity;
import com.Desafio2.Model.mappers.DireccionMapper;
import com.Desafio2.Model.repositories.DireccionRepository;
import com.Desafio2.exceptions.Kinds.NumeroNotFound;
import com.Desafio2.exceptions.Kinds.PersonaNotFound;
import com.Desafio2.Model.mappers.PersonaMapper;
import com.Desafio2.Model.repositories.PersonaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    private final PersonaMapper personaMapper;

    private DireccionService direccionService;

    private DireccionRepository direccionRepository;

    private DireccionMapper direccionMapper;

    public PersonaService(PersonaRepository personaRepository, PersonaMapper personaMapper, DireccionService direccionService, DireccionRepository direccionRepository, DireccionMapper direccionMapper){
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
        this.direccionService = direccionService;
        this.direccionRepository = direccionRepository;
        this.direccionMapper = direccionMapper;
    }

    public List<PersonaDTO> findAll(){
        return personaRepository
                .findAll()
                .stream()
                .map(personaEntity -> personaMapper.personaEntityToPersonaDTO(personaEntity))
                .collect(Collectors.toList());
    }

    public void eliminarPersonas(){
        personaRepository.deleteAll();
    }

    public void eliminarPersona(Integer id){
        Optional<PersonaEntity> exits = personaRepository.findById(id);
        if (!exits.isPresent()) throw new PersonaNotFound("La persona que se desea eliminar no existe");
        personaRepository.deleteById(id);

    }

    public PersonaDTO agregar(Integer id, PersonaDTO personaDTO){
        Optional<PersonaEntity> exits = personaRepository.findById(id);
        if(!exits.isPresent()) throw new PersonaNotFound("La persona que intenta modificar ya existe");
        PersonaEntity entity = exits.get();
        entity.setId(personaDTO.getId());
        entity.setName(personaDTO.getName());
        entity.setPhone(personaDTO.getPhone());
        personaRepository.save(entity);
        return personaDTO;

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
        } else if (personaname != null) {
            throw new PersonaNotFound("El nombre ya está utilizado");
        } else if (personaphone != null) {
            throw new NumeroNotFound("El número ya está utilizado");
        } else {return null;}
    }

    public ListaDTO findAllContactos(Integer pageNumber, Integer pageSize, String name, String phone){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if(!name.equals("") && phone.equals("")){
            return new ListaDTO( personaRepository.findAllByNameContains(name, pageable)
                    .stream()
                    .map(entity -> personaMapper.personaEntityToPersonaDTO(entity))
                    .collect(Collectors.toList())
            );

        }else if(name.equals("") && !phone.equals("")){
            return new ListaDTO(personaRepository.findAllByPhoneContains(phone,pageable)
                    .stream()
                    .map(entity -> personaMapper.personaEntityToPersonaDTO(entity))
                    .collect(Collectors.toList())
            );

        }else if(!name.equals("") && !phone.equals("")){
            return  new ListaDTO (personaRepository.findAllByNameContainsAndPhoneContains(name, phone, pageable)
                    .stream()
                    .map(entity -> personaMapper.personaEntityToPersonaDTO(entity) )
                    .collect(Collectors.toList())
            );

        }else {
            return new ListaDTO(personaRepository.findAll()
                    .stream()
                    .map(entity -> personaMapper.personaEntityToPersonaDTO(entity) )
                    .collect(Collectors.toList())
            );
        }
    }

    public ListaDireDTO findFiltro(Integer pageNumber, Integer pageSize, String calle, Float altura){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        if(!calle.equals("") && altura.equals("")){
            return new ListaDireDTO(direccionRepository.findAllByCalleContains(calle, pageable)
                    .stream()
                    .map(direccionEntity -> direccionMapper.direccionEntityToDireccionDTO(direccionEntity))
                    .collect(Collectors.toList())
            );
        }else if(calle.equals("") && !altura.equals("")){
            return new ListaDireDTO(direccionRepository.findAllByAlturaContains(altura, pageable)
                    .stream()
                    .map(entity -> direccionMapper.direccionEntityToDireccionDTO(entity))
                    .collect(Collectors.toList())
            );
        }else if(!calle.equals("") && !altura.equals("")){
            return  new ListaDireDTO (direccionRepository.findAllByCalleContainsAndAlturaContains(calle, altura, pageable)
                    .stream()
                    .map(entity -> direccionMapper.direccionEntityToDireccionDTO(entity) )
                    .collect(Collectors.toList())
            );
        }else {
            return new ListaDireDTO(direccionRepository.findAll(pageable)
                    .stream()
                    .map(direccion -> direccionMapper.direccionEntityToDireccionDTO(direccion))
                    .collect(Collectors.toList())
            );
        }
    }

    public ListaDTO getContactByDomicilio(Integer id){
        DireccionEntity direccionEntity = new DireccionEntity();
        direccionEntity.setId(id);

        return new ListaDTO(personaRepository.findAllByDireccion(direccionEntity)
                .stream()
                .map(contactEntity -> personaMapper.personaEntityToPersonaDTO(contactEntity))
                .collect(Collectors.toList())
        );

    }

}

