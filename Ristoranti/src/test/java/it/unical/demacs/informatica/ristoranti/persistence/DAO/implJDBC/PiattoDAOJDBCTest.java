package it.unical.demacs.informatica.ristoranti.persistence.DAO.implJDBC;

import it.unical.demacs.informatica.ristoranti.model.Piatto;
import it.unical.demacs.informatica.ristoranti.model.Ristorante;
import it.unical.demacs.informatica.ristoranti.persistence.DAO.PiattoDAO;
import it.unical.demacs.informatica.ristoranti.persistence.DBManager;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PiattoDAOJDBCTest {
    @Test
    void whenCallFindAll_ReturnAllValues() {
        //given
        //when
        PiattoDAO piattoDao = DBManager.getInstance().getPiattoDAO();
        List<Piatto> all = piattoDao.findAll();
        assertNotNull(all);
        assertEquals(5, all.size());
        for (Piatto piatto : all) {
            System.out.println(piatto);
        }
    }

    @Test
    void whenTryToSaveANewPiatto_Then_saveItCorrectly() {
        Piatto p = new Piatto();
        p.setNome("Piatto12");
        p.setIngredienti("Ing12");
        p.setRistoranti(Arrays.asList(
                new Ristorante("RIST10", "Desc10", "Ub10"),
                new Ristorante("RIST11", "Desc11", "Ub11")
        ));
        DBManager.getInstance().getPiattoDAO().save(p);
    }
}
