package it.unical.demacs.informatica.ristoranti.service;

import it.unical.demacs.informatica.ristoranti.model.UserRole;
import it.unical.demacs.informatica.ristoranti.model.Utente;

import java.util.Optional;

public interface IUserService {
    Utente createUser(String username, String password, UserRole role);

    Optional<Utente> getUser(String username);
}
