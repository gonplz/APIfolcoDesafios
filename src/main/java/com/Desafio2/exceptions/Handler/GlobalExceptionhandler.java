package com.Desafio2.exceptions.Handler;

import com.Desafio2.exceptions.DTOexeption.ErrorMensajeDTO;
import com.Desafio2.exceptions.Kinds.NumeroNotFound;
import com.Desafio2.exceptions.Kinds.PersonaNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionhandler {
    //Esto por si no funciona el servidor//
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseEntity<ErrorMensajeDTO> defaultErrorHandler(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMensajeDTO(e.getMessage(),req.getRequestURI(),"Codigo interno"+" "+20), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //Esto maneja un problema interno de la API//
    //Esto valida que el nombre existe y larga el error
    @ExceptionHandler(PersonaNotFound.class)
    @ResponseBody
    public ResponseEntity<ErrorMensajeDTO> notFoundException(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMensajeDTO(e.getMessage(), req.getRequestURI(),"Codigo interno"+" "+20),HttpStatus.NOT_FOUND);
    }
    //Esto valida que el numero existe y larga el error
    @ExceptionHandler(NumeroNotFound.class)
    @ResponseBody
    public ResponseEntity<ErrorMensajeDTO> notNumeroFoundException(HttpServletRequest req, Exception e){
        return new ResponseEntity<>(new ErrorMensajeDTO(e.getMessage(), req.getRequestURI(),"Codigo interno"+" "+20),HttpStatus.BAD_REQUEST);
    }

}
