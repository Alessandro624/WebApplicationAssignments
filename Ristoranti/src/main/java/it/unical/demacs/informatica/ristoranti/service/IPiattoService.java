package it.unical.demacs.informatica.ristoranti.service;

import it.unical.demacs.informatica.ristoranti.model.Piatto;

import java.util.List;

public interface IPiattoService {

    // list
    List<Piatto> findAll();

    // retrive byID
    Piatto findById(String nome);

    // create
    Piatto createPiatto(Piatto piatto) throws Exception;

    // update
    Piatto updatePiatto(String nome, Piatto piatto) throws Exception;

    // delete
    void deletePiatto(String nome);
}
