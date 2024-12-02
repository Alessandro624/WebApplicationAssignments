package it.unical.demacs.informatica.ristoranti.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtility {

    /**
     * Restituisce il nome dell'utente corrente autenticato.
     *
     * @return il nome dell'utente corrente, oppure null se non autenticato.
     */
    public static String getCurrentUsername() {
        UserDetails currentUser = getCurrentUser();
        if (currentUser == null) {
            return null;
        } else {
            return currentUser.getUsername();
        }
    }

    public static UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal);
            } else {
                throw new RuntimeException("Principal is not a UserDetails");
            }
        }
        return null; // Nessun utente autenticato
    }
}
