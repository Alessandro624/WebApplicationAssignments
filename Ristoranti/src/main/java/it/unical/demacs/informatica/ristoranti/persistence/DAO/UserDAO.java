package it.unical.demacs.informatica.ristoranti.persistence.DAO;

import it.unical.demacs.informatica.ristoranti.model.Utente;

public interface UserDAO {
    Utente findByPrimaryKey(String username);

    void save(Utente utente);
}
