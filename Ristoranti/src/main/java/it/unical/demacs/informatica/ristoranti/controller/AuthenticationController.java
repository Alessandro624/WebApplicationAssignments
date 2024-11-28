package it.unical.demacs.informatica.ristoranti.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    ResponseEntity<String[]> login(@RequestParam String username, @RequestParam String password, HttpSession session) {
        String decodedUsername = new String(Base64.getDecoder().decode((username)));
        String decodedPassword = new String(Base64.getDecoder().decode(password));
        if (decodedUsername.equals("user@") && decodedPassword.equals("pass")) {
            session.setAttribute("username", decodedUsername);
            return ResponseEntity.ok(new String[]{"Login successful"});
        }
        session.invalidate();
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    ResponseEntity<String[]> logout(HttpSession session) {
        if (Boolean.TRUE.equals(isAuthenticated(session).getBody())) {
            session.invalidate();
            return ResponseEntity.ok(new String[]{"Logout successful"});
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
    }

    @RequestMapping(value = "/isAuthenticated", method = RequestMethod.GET)
    ResponseEntity<Boolean> isAuthenticated(HttpSession session) {
        return ResponseEntity.ok(
                session.getAttribute("username") != null && session.getAttribute("username").equals("user@")
        );
    }
}
