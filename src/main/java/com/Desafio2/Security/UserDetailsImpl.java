package com.Desafio2.Security;

import com.Desafio2.Model.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
    //recibe un suaurio
    private final UsuarioEntity usuario;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); //util si mi usuario tiene permisos o roles
    }
    @Override
    public String getPassword() {
        return usuario.getPassword();
    }
    @Override
    public String getUsername() {
        return usuario.getUsuario();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    public String getNombre() {
        return usuario.getNombre();
    }

}
