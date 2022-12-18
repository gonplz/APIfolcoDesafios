package com.Desafio2.Model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(10) UNSIGNED")
    private Integer id;

    @Column(name = "nombre", columnDefinition = "VARCHAR(50)")
    public String nombre;

    @Column(name = "usuario", columnDefinition = "VARCHAR(50)")
    public String usuario;

    @Column(name = "password", columnDefinition = "VARCHAR(255)")
    public String password;
}
