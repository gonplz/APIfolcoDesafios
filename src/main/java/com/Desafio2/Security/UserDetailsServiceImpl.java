package com.Desafio2.Security;

import com.Desafio2.Model.entity.UsuarioEntity;
import com.Desafio2.Model.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        UsuarioEntity user = usuarioRepository
                .findOneByUsuario(usuario)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario" + usuario + "no existe."));
        return new UserDetailsImpl(user);
    }

}
