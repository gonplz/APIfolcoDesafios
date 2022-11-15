package com.Desafio2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {
    private Integer id;
    private String name;
    private String phone;
}

