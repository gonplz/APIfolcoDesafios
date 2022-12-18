package com.Desafio2.Model.repositories;

import com.Desafio2.Model.entity.DireccionEntity;
import com.Desafio2.Model.entity.PersonaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity,Integer> {
    PersonaEntity findByName(String name);
    PersonaEntity findByPhone(String phone);

    List<PersonaEntity> findAllByNameContains (String name, Pageable pageable);
    List<PersonaEntity> findAllByPhoneContains(String phone, Pageable pageable);

    List<PersonaEntity> findAllByNameContainsAndPhoneContains(String name, String phone,Pageable pageable);

    List<PersonaEntity> findAllByDireccion(DireccionEntity direccion);
}
