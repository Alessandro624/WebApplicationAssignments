package it.unical.demacs.informatica.ristoranti.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.*;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Utente implements UserDetails {
    private String username;
    private String password;
    private UserRole role;
    private AuthProvider provider;

    public Utente(String username, String password, UserRole role, AuthProvider provider) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.provider = provider;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                (GrantedAuthority) role::toString
        );
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
