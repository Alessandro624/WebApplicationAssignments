package it.unical.demacs.informatica.ristoranti.service;

import it.unical.demacs.informatica.ristoranti.model.UserRole;
import it.unical.demacs.informatica.ristoranti.model.Utente;
import it.unical.demacs.informatica.ristoranti.persistence.DAO.UserDAO;
import it.unical.demacs.informatica.ristoranti.persistence.DBManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.userDAO = DBManager.getInstance().getUserDAO();
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Utente createUser(String username, String password, UserRole role) {
        // TODO checks:
        //  fields are not null ,
        //  password complex
        //  username not already used
        this.userDAO.save(new Utente(
                username,
                passwordEncoder.encode(password),
                role
        ));
        Optional<Utente> utente = this.getUser(username);
        return utente.orElse(null);
    }

    @Override
    public Optional<Utente> getUser(String username) {
        Utente byPrimaryKey = this.userDAO.findByPrimaryKey(username);
        return Optional.of(byPrimaryKey);
    }
}
