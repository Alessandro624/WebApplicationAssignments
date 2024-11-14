package it.unical.demacs.informatica.ristoranti.service;

import it.unical.demacs.informatica.ristoranti.exception.RistoranteNotValidException;
import it.unical.demacs.informatica.ristoranti.model.Ristorante;
import it.unical.demacs.informatica.ristoranti.persistence.DAO.RistoranteDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RistoranteService implements IRistoranteService {

    private final RistoranteDAO ristoranteDAO;

    RistoranteService(RistoranteDAO ristoranteDAO) {
        this.ristoranteDAO = ristoranteDAO;
    }

    @Override
    public List<Ristorante> findAll() {
        return this.ristoranteDAO.findAll();
    }

    @Override
    public Ristorante findById(String nome) {
        return this.ristoranteDAO.findByPrimaryKey(nome);
    }

    @Override
    public Ristorante createRistorante(Ristorante ristorante) throws Exception {
        // verify that not exists a Ristorante with the same name
        if (this.ristoranteDAO.findByPrimaryKey(ristorante.getNome()) != null) {
            throw new Exception("Already exists a Ristorante with the same name! " + ristorante.getNome());
        }
        // verify that Ristorante is consistent
        checkRistoranteIsValid(ristorante);
        this.ristoranteDAO.save(ristorante);
        return this.ristoranteDAO.findByPrimaryKey(ristorante.getNome());
    }

    private void checkRistoranteIsValid(Ristorante ristorante) {
        if (ristorante == null) {
            throw new RistoranteNotValidException("Ristorante must be not null");
        }
        if (ristorante.getNome() == null || ristorante.getNome().isEmpty()) {
            throw new RistoranteNotValidException("Ristorante.nome must be not null and not empty");
        }
    }

    @Override
    public Ristorante updateRistorante(String nome, Ristorante ristorante) throws Exception {
        ristorante.setNome(nome);
        checkRistoranteIsValid(ristorante);
        // verify that exists a Ristorante with the same name
        if (this.ristoranteDAO.findByPrimaryKey(ristorante.getNome()) == null) {
            throw new Exception("Not exists a Ristorante with the same name! " + ristorante.getNome());
        }
        this.ristoranteDAO.save(ristorante);
        return this.ristoranteDAO.findByPrimaryKey(ristorante.getNome());
    }

    @Override
    public void deleteRistorante(String nome) {
        this.ristoranteDAO.delete(nome);
    }
}
