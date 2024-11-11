package it.unical.demacs.informatica.ristoranti.persistence.DAO;

import it.unical.demacs.informatica.ristoranti.model.Ristorante;

import java.util.List;

public interface RistoranteDAO {
    List<Ristorante> findAll();

    Ristorante findByPrimaryKey(String nome);

    void save(Ristorante ristorante);

    void delete(Ristorante ristorante);

    List<Ristorante> findAllByPiattoName(String piattoNome);
}
