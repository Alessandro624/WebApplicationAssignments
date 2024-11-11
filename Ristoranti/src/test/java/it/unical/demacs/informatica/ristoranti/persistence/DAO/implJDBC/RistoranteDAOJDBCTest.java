package it.unical.demacs.informatica.ristoranti.persistence.DAO.implJDBC;

import it.unical.demacs.informatica.ristoranti.model.Piatto;
import it.unical.demacs.informatica.ristoranti.model.Ristorante;
import it.unical.demacs.informatica.ristoranti.persistence.DAO.RistoranteDAO;
import it.unical.demacs.informatica.ristoranti.persistence.DBManager;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RistoranteDAOJDBCTest {
    @Test
    void whenFindAll_thenRetrieveAll() {
        RistoranteDAO ristoranteDao = DBManager.getInstance().getRistoranteDAO();
        List<Ristorante> all = ristoranteDao.findAll();
        assertNotNull(all);
        assertEquals(5, all.size());
        for (Ristorante ristorante : all) {
            System.out.println(ristorante);
        }
    }

    @Test
    void whenTryToSaveANewRistorante_Then_saveItCorrectly() {
        Ristorante r = new Ristorante();
        r.setNome("Ristorante5");
        r.setDescrizione("Desc5");
        r.setUbicazione("Ub5");
        r.setPiatti(Arrays.asList(
                new Piatto("PIATTO10", "ing10"),
                new Piatto("PIATTO11", "ing11")
        ));
        DBManager.getInstance().getRistoranteDAO().save(r);
    }
}
