package com.Desafio2.Model.repositories;

import com.Desafio2.Model.entity.DireccionEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionRepository extends JpaRepository <DireccionEntity,Integer> {

    List<DireccionEntity> findAllByCalleContains (String calle, Pageable pageable);
    List<DireccionEntity> findAllByAlturaContains(Float altura, Pageable pageable);

    List<DireccionEntity> findAllByCalleContainsAndAlturaContains(String calle, Float altura, Pageable pageable);
}
