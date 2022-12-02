package com.Desafio2.exceptions.DTOexeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMensajeDTO {
    private String menssage;
    private String path;
    private String code;

}
