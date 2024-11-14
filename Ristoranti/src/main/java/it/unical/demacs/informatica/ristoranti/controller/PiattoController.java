package it.unical.demacs.informatica.ristoranti.controller;

import it.unical.demacs.informatica.ristoranti.model.Piatto;
import it.unical.demacs.informatica.ristoranti.service.IPiattoService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/piatto/v1/")
public class PiattoController {
    private final IPiattoService piattoService;

    public PiattoController(IPiattoService piattoService) {
        this.piattoService = piattoService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    ResponseEntity<Collection<Piatto>> getAllPiatti() {
        return ResponseEntity.ok(
                this.piattoService.findAll()
        );
    }

    // gestire nomi con spazi, ecc ...
    @RequestMapping(value = "/{nomePiatto}", method = RequestMethod.GET)
    ResponseEntity<Piatto> getPiattoById(@PathVariable("nomePiatto") /*si può lasciare vuoto se il nome della variabile è uguale a quella nel RequestMapping*/ String nome) {
        return ResponseEntity.ok(
                this.piattoService.findById(nome)
        );
    }

    @RequestMapping(value = "/addPiatto", method = RequestMethod.POST)
    ResponseEntity<Piatto> postCreateNewPiatto(@RequestBody Piatto piatto) throws RuntimeException {
        try {
            return ResponseEntity.ok(
                    this.piattoService.createPiatto(piatto)
            );
        } catch (RuntimeException e) {
            // TODO custom error
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }

    @RequestMapping(value = "/updatePiatto/{nomePiatto}", method = RequestMethod.POST)
    ResponseEntity<Piatto> postUpdateNewPiatto(@PathVariable String nomePiatto, @RequestBody Piatto piatto) {
        return ResponseEntity.ok(
                this.piattoService.updatePiatto(nomePiatto, piatto)
        );
    }

    @RequestMapping(value = "/removePiatto/{nomePiatto}", method = RequestMethod.DELETE)
    ResponseEntity<Void> postDeleteNewPiatto(@PathVariable String nomePiatto) {
        this.piattoService.deletePiatto(nomePiatto);
        return ResponseEntity.ok().build();
    }
}
