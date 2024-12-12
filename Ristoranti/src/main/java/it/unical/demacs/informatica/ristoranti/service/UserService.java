package it.unical.demacs.informatica.ristoranti.service;

import it.unical.demacs.informatica.ristoranti.model.AuthProvider;
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
    public Utente createUser(String username, String password, UserRole role, AuthProvider provider) {
        if (username == null || password == null || role == null || provider == null) {
            throw new IllegalArgumentException("Fields cannot be null");
        }

        if (provider == AuthProvider.LOCAL && !isPasswordComplex(password)) {
            throw new IllegalArgumentException("Password does not meet complexity requirements");
        }

        if (this.userDAO.findByPrimaryKey(username) != null) {
            throw new IllegalArgumentException("Username is already taken");
        }
        this.userDAO.save(new Utente(
                username,
                passwordEncoder.encode(password),
                role,
                provider
        ));
        Optional<Utente> utente = this.getUser(username, provider);
        return utente.orElse(null);
    }

    @Override
    public Utente updateUser(String username, Utente utente) {
        if (utente == null) {
            throw new IllegalArgumentException("User object cannot be null");
        }

        if (this.userDAO.findByPrimaryKey(username) == null) {
            throw new IllegalArgumentException("User not found");
        }

        utente.setUsername(username);

        if (utente.getPassword() == null || utente.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password field cannot be blank");
        } else if (!isPasswordComplex(utente.getPassword())) {
            throw new IllegalArgumentException("New password does not meet complexity requirements");
        }

        if (utente.getRole() == null) {
            throw new IllegalArgumentException("Role field cannot be blank");
        }

        if (utente.getProvider() == null) {
            throw new IllegalArgumentException("Provider field cannot be blank");
        }
        
        this.userDAO.save(utente);
        return this.userDAO.findByPrimaryKey(username);
    }

    @Override
    public Optional<Utente> getUser(String username, AuthProvider provider) {
        Utente byPrimaryKey = this.userDAO.findByPrimaryKey(username);
        if (byPrimaryKey == null) {
            return Optional.empty();
        }
        if (byPrimaryKey.getProvider() == provider) {
            return Optional.of(byPrimaryKey);
        }
        return Optional.empty();
    }

    private boolean isPasswordComplex(String password) {
        return password.length() >= 8 &&
                password.chars().anyMatch(Character::isUpperCase) &&
                password.chars().anyMatch(Character::isLowerCase) &&
                password.chars().anyMatch(Character::isDigit);
    }
}
