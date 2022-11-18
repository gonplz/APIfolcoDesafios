package com.Desafio2.repositories;

import com.Desafio2.entity.PersonaEntity;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity,Integer> {
    PersonaEntity findByName(String name);
    PersonaEntity findByPhone(String phone);
}
