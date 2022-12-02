package com.Desafio2.domain;

import com.Desafio2.entity.PersonaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class DireccionDTO {

    private Integer id;
    private String calle;
    private Float altura;
}
