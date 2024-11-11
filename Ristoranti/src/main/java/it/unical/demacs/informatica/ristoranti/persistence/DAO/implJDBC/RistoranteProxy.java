package it.unical.demacs.informatica.ristoranti.persistence.DAO.implJDBC;

import it.unical.demacs.informatica.ristoranti.model.Piatto;
import it.unical.demacs.informatica.ristoranti.model.Ristorante;
import it.unical.demacs.informatica.ristoranti.persistence.DBManager;

import java.util.List;

public class RistoranteProxy extends Ristorante {
    public RistoranteProxy(String nome, String descrizione, String ubicazione) {
        super(nome, descrizione, ubicazione);
    }

    @Override
    public List<Piatto> getPiatti() {
        if (this.piatti == null)
            super.setPiatti(DBManager.getInstance().getPiattoDAO().findAllByRistoranteName(this.getNome()));
        return super.getPiatti();
    }
}
