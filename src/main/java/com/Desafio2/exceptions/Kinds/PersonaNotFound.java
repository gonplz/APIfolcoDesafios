package com.Desafio2.exceptions.Kinds;

import java.rmi.StubNotFoundException;

public class PersonaNotFound extends RuntimeException{
    public PersonaNotFound(String massage){super(massage);}
}
