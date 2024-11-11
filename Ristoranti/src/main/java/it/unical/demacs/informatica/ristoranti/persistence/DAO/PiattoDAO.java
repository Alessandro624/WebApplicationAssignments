package it.unical.demacs.informatica.ristoranti.persistence.DAO;

import it.unical.demacs.informatica.ristoranti.model.Piatto;

import java.util.List;

public interface PiattoDAO {
    List<Piatto> findAll();

    Piatto findByPrimaryKey(String nome);

    void save(Piatto piatto);

    void delete(Piatto piatto);

    List<Piatto> findAllByRistoranteName(String ristornateNome);
}
