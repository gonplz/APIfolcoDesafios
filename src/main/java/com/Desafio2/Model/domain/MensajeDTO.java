package com.Desafio2.Model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDTO {
   private Integer id;
   private String mensaje = "añadido exitosamente";

   public MensajeDTO(Integer id) {
      this.id = id;
   }
}
