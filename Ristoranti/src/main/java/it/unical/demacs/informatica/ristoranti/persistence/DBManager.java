package it.unical.demacs.informatica.ristoranti.persistence;

import it.unical.demacs.informatica.ristoranti.persistence.DAO.PiattoDAO;
import it.unical.demacs.informatica.ristoranti.persistence.DAO.RistoranteDAO;
import it.unical.demacs.informatica.ristoranti.persistence.DAO.implJDBC.PiattoDAOJDBC;
import it.unical.demacs.informatica.ristoranti.persistence.DAO.implJDBC.RistoranteDAOJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static DBManager instance;
    private Connection con = null;
    private RistoranteDAO ristoranteDAO = null;
    private PiattoDAO piattoDAO = null;

    private DBManager() {
    }

    public static DBManager getInstance() {
        return instance == null ? instance = new DBManager() : instance;
    }

    public Connection getConnection() {
        if (con == null) {
            try {
                con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ristoranti", System.getenv("POSTGRES_USER"), System.getenv("POSTGRES_PASSWORD"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return con;
    }

    public PiattoDAO getPiattoDAO() {
        if (piattoDAO == null)
            piattoDAO = new PiattoDAOJDBC(getConnection());
        return piattoDAO;
    }

    public RistoranteDAO getRistoranteDAO() {
        if (ristoranteDAO == null)
            ristoranteDAO = new RistoranteDAOJDBC(getConnection());
        return ristoranteDAO;
    }
}
