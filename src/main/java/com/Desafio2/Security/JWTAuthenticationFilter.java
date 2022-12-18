package com.Desafio2.Security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {
    //intento de autentificacion

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletRequest response) throws AuthenticationException
    {
        AuthCredential authCredentials = new AuthCredential();

        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredential.class);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getUsername(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );
        return getAuthenticationManager().authenticate(usernamePAT);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();  //capturo el objeto de getPrincipal()
        String token = TokenUtils.createToken(userDetails.getNombre(), userDetails.getUsername()); //creo el token
        response.addHeader("Authorization", "Bearer " + token); //lo agrego al response con el prefijo Bearer
        response.getWriter().flush();

        super.successfulAuthentication(request, response, chain, authResult);

    }
}
