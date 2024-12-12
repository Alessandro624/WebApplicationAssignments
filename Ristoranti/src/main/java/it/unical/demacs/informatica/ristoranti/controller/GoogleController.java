package it.unical.demacs.informatica.ristoranti.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import it.unical.demacs.informatica.ristoranti.model.AuthProvider;
import it.unical.demacs.informatica.ristoranti.model.GoogleTokenRequest;
import it.unical.demacs.informatica.ristoranti.model.UserRole;
import it.unical.demacs.informatica.ristoranti.model.Utente;
import it.unical.demacs.informatica.ristoranti.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.UUID;

@RestController
@RequestMapping("/api/open/google-login")
public class GoogleController {
    private final IUserService userService;

    public GoogleController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> verifyGoogleToken(@RequestBody GoogleTokenRequest request, HttpSession session) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(System.getenv("GOOGLE_CLIENT_ID_RISTORANTI")))
                    .build();
            GoogleIdToken idToken = verifier.verify(request.getToken());
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String email = payload.getEmail();
                /*
                 *   boolean emailVerified = payload.getEmailVerified();
                 *   String name = (String) payload.get("name");
                 *   String pictureUrl = (String) payload.get("picture");
                 */
                Utente utente = userService.getUser(email, AuthProvider.GOOGLE).orElseGet(() -> userService.createUser(email, String.valueOf(UUID.randomUUID()), UserRole.ROLE_USER, AuthProvider.GOOGLE));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(utente, null, utente.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(401).body("Invalid token.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error validating token.");
        }
    }
}
