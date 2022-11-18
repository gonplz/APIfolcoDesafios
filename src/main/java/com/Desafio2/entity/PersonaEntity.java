package com.Desafio2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity (name= "Persona")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "Contactos")
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id", columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column( name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column( name = "phone", columnDefinition = "VARCHAR(50)")
    private String phone;
}