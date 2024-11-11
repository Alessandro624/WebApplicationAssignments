package it.unical.demacs.informatica.ristoranti.persistence.DAO.implJDBC;

import it.unical.demacs.informatica.ristoranti.model.Piatto;
import it.unical.demacs.informatica.ristoranti.model.Ristorante;
import it.unical.demacs.informatica.ristoranti.persistence.DBManager;

import java.util.List;

public class PiattoProxy extends Piatto {
    public PiattoProxy(String nome, String ingredienti) {
        super(nome, ingredienti);
    }

    @Override
    public List<Ristorante> getRistoranti() {
        if (this.ristoranti == null)
            super.setRistoranti(DBManager.getInstance().getRistoranteDAO().findAllByPiattoName(this.nome));
        return super.getRistoranti();
    }
}
