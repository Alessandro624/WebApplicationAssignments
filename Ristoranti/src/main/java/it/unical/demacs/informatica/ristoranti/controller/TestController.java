package it.unical.demacs.informatica.ristoranti.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    @RequestMapping(value = "/open/test", method = RequestMethod.GET)
    ResponseEntity<Void> testOpenController() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/auth/test", method = RequestMethod.GET)
    ResponseEntity<Void> testAuthController() {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/admin/test", method = RequestMethod.GET)
    ResponseEntity<Void> testAdminController() {
        return ResponseEntity.ok().build();
    }
}
