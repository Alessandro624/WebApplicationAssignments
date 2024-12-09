package it.unical.demacs.informatica.ristoranti.controller;

import it.unical.demacs.informatica.ristoranti.model.GoogleTokenRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/open/google-login")
public class GoogleController {
    @PostMapping
    public ResponseEntity<?> verifyGoogleToken(@RequestBody GoogleTokenRequest request) {
        System.out.println(request);
        System.out.println(request.getToken());
        return ResponseEntity.ok().build();
    }
}
