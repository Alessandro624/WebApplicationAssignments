package it.unical.demacs.informatica.ristoranti.controller;

import it.unical.demacs.informatica.ristoranti.exception.RistoranteNotValidException;
import it.unical.demacs.informatica.ristoranti.model.Ristorante;
import it.unical.demacs.informatica.ristoranti.service.IRistoranteService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/ristorante/v1")
public class RistoranteController {
    private final IRistoranteService ristoranteService;

    public RistoranteController(IRistoranteService ristoranteService) {
        this.ristoranteService = ristoranteService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    ResponseEntity<Collection<Ristorante>> getAllRistoranti() {
        return ResponseEntity.ok(
                this.ristoranteService.findAll()
        );
    }

    // gestire nomi con spazi, ecc ...
    @RequestMapping(value = "/{nomeRistorante}", method = RequestMethod.GET)
    ResponseEntity<Ristorante> getRistoranteById(@PathVariable String nomeRistorante) {
        return ResponseEntity.ok(
                this.ristoranteService.findById(nomeRistorante)
        );
    }

    @RequestMapping(value = "/addRistorante", method = RequestMethod.POST)
    ResponseEntity<Ristorante> postCreateNewRistorante(@RequestBody Ristorante ristorante) throws Exception {
        try {
            return ResponseEntity.ok(
                    this.ristoranteService.createRistorante(ristorante)
            );
        } catch (RistoranteNotValidException e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).build();
        }
    }

    @RequestMapping(value = "/updateRistorante/{nomeRistorante}", method = RequestMethod.POST)
    ResponseEntity<Ristorante> postUpdateNewRistorante(@PathVariable String nomeRistorante, @RequestBody Ristorante ristorante) throws Exception {
        return ResponseEntity.ok(
                this.ristoranteService.updateRistorante(nomeRistorante, ristorante)
        );
    }

    @RequestMapping(value = "/removeRistorante/{nomeRistorante}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteNewRistorante(@PathVariable String nomeRistorante) {
        this.ristoranteService.deleteRistorante(nomeRistorante);
        return ResponseEntity.ok().build();
    }
}
