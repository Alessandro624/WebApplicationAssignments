package it.unical.demacs.informatica.ristoranti.controller;

import it.unical.demacs.informatica.ristoranti.model.Ristorante;
import it.unical.demacs.informatica.ristoranti.persistence.DBManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class GestioneRistorante {
    @PostMapping("/addRistorante")
    public String aggiungiRistorante(@RequestBody Ristorante ristorante) {
        System.out.println(ristorante);
        DBManager.getInstance().getRistoranteDAO().save(ristorante);
        return "OK";
    }

    @GetMapping("/removeRistorante")
    public String rimuoviRistorante(@RequestBody Ristorante ristorante) {
        System.out.println(ristorante);
        return "OK";
    }
}
