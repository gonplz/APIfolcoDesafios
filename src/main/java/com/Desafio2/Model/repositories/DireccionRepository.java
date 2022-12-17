package com.Desafio2.Model.repositories;

import com.Desafio2.Model.entity.DireccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository <DireccionEntity,Integer> {
}
