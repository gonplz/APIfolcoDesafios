package com.Desafio2.Model.repositories;

import com.Desafio2.Model.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity,Integer> {
    PersonaEntity findByName(String name);
    PersonaEntity findByPhone(String phone);
}
