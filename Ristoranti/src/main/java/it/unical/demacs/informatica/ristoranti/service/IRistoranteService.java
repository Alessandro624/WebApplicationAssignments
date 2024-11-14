package it.unical.demacs.informatica.ristoranti.service;

import it.unical.demacs.informatica.ristoranti.model.Ristorante;

import java.util.List;

public interface IRistoranteService {

    // list
    List<Ristorante> findAll();

    // retrive byID
    Ristorante findById(String nome);

    // create
    Ristorante createRistorante(Ristorante ristorante) throws Exception;

    // update
    Ristorante updateRistorante(String nome, Ristorante ristorante) throws Exception;

    // delete
    void deleteRistorante(String nome);
}
