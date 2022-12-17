package com.Desafio2.Model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Direccion")
public class DireccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "INT UNSIGNED")
    private Integer id;

    @Column(name = "calle", columnDefinition = "VARCHAR(50)")
    private String calle;

    @Column(name = "altura", columnDefinition = "FLOAT")
    private Float altura;

    @OneToOne(mappedBy = "direccion")
    private PersonaEntity persona;
}
