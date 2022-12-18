package com.Desafio2.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    //esto mover a properties, no debería estar en el codigo
    private final static String ACCESS_TOKEN_SECRET="abc123abc123abc123abc123abc123abc123abc123abc123";
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L; //30 * 24 * 60 * 60

    public static String createToken(String nombre, String email) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000; //lo paso a milisegundos
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();      //definimos para agregar mas datos, estos datos viajaran con el token
        extra.put("nombre", nombre);
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes())) //firmo usando hmacShaKey en bytes y compactamos
                .compact(); //produce un token q sera enviado al cliente
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token) //envio el token que obtengo como parametro
                    .getBody();
            //Extraer el correo o username q identifica a un usuario
            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        }
        catch(JwtException e) { //Manejo excepcion si el token esta en un formato incorrecto
            return null;
        }

    }
}

