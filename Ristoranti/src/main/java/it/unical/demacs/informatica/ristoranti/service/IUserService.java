package it.unical.demacs.informatica.ristoranti.service;

import it.unical.demacs.informatica.ristoranti.model.AuthProvider;
import it.unical.demacs.informatica.ristoranti.model.UserRole;
import it.unical.demacs.informatica.ristoranti.model.Utente;

import java.util.Optional;

public interface IUserService {
    Utente createUser(String username, String password, UserRole role, AuthProvider provider);

    Utente updateUser(String username, Utente utente);

    Optional<Utente> getUser(String username, AuthProvider provider);
}
