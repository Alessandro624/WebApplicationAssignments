package it.unical.demacs.informatica.ristoranti.controller;

import it.unical.demacs.informatica.ristoranti.config.security.SecurityUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/v1")
public class AuthController {
    @RequestMapping(value = "/check-user", method = RequestMethod.GET)
    public ResponseEntity<UserDetails> getCurrentUser() {
        UserDetails currentUser = SecurityUtility.getCurrentUser();
        return ResponseEntity.ok(currentUser);
    }
}
