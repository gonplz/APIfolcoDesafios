package com.Desafio2.repositories;

import com.Desafio2.entity.DireccionEntity;
import com.Desafio2.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository <DireccionEntity,Integer> {
}
