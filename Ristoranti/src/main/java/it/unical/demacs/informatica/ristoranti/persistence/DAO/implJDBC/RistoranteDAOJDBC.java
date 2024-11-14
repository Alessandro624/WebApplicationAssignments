package it.unical.demacs.informatica.ristoranti.persistence.DAO.implJDBC;

import it.unical.demacs.informatica.ristoranti.model.Piatto;
import it.unical.demacs.informatica.ristoranti.model.Ristorante;
import it.unical.demacs.informatica.ristoranti.persistence.DAO.PiattoDAO;
import it.unical.demacs.informatica.ristoranti.persistence.DAO.RistoranteDAO;
import it.unical.demacs.informatica.ristoranti.persistence.DBManager;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class RistoranteDAOJDBC implements RistoranteDAO {

    private final Connection connection;

    public RistoranteDAOJDBC() {
        this.connection = DBManager.getInstance().getConnection();
    }

    @Override
    public List<Ristorante> findAll() {
        List<Ristorante> ristoranti = new ArrayList<>();
        String query = "SELECT * FROM ristorante";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Ristorante ristorante = new RistoranteProxy(rs.getString("nome"), rs.getString("descrizione"), rs.getString("ubicazione"));
                ristoranti.add(ristorante);
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
        return ristoranti;
    }

    @Override
    public Ristorante findByPrimaryKey(String nome) {
        String query = "SELECT * FROM ristorante WHERE nome = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return new RistoranteProxy(rs.getString("nome"), rs.getString("descrizione"), rs.getString("ubicazione"));
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Ristorante ristorante) {
        String query = "INSERT INTO ristorante(nome, descrizione, ubicazione) VALUES(?, ?, ?)"
                + "ON CONFLICT (nome) DO UPDATE SET descrizione = EXCLUDED.descrizione, ubicazione = EXCLUDED.ubicazione";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, ristorante.getNome());
            st.setString(2, ristorante.getDescrizione());
            st.setString(3, ristorante.getUbicazione());
            st.executeUpdate();
            List<Piatto> piatti = ristorante.getPiatti();
            if (piatti == null || piatti.isEmpty()) {
                return;
            }
            resetRelationInJoinTable(ristorante.getNome());
            PiattoDAO piattoDAO = DBManager.getInstance().getPiattoDAO();
            for (Piatto piatto : piatti) {
                piattoDAO.save(piatto);
                insertRelationInJoinTable(ristorante.getNome(), piatto.getNome());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertRelationInJoinTable(String nomeRistorante, String nomePiatto) throws SQLException {
        String query = "INSERT INTO ristorante_piatto(ristorante_nome, piatto_nome) VALUES(?, ?)";
        PreparedStatement st = connection.prepareStatement(query);
        st.setString(1, nomeRistorante);
        st.setString(2, nomePiatto);
        st.execute();
    }

    private void resetRelationInJoinTable(String nome) throws SQLException {
        String query = "DELETE FROM ristorante_piatto WHERE ristorante_nome = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setString(1, nome);
        st.execute();
    }

    @Override
    public void delete(String nome) {
        String query = "DELETE FROM ristorante WHERE nome = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, nome);
            st.executeUpdate();
            resetRelationInJoinTable(nome);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Ristorante> findAllByPiattoName(String piattoNome) {
        List<Ristorante> ristoranti = new ArrayList<>();
        String query = "SELECT r.nome, r.descrizione, r.ubicazione FROM ristorante r " +
                "JOIN ristorante_piatto rp ON r.nome = rp.ristorante_nome " +
                "WHERE rp.piatto_nome = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, piattoNome);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ristoranti.add(new Ristorante(rs.getString("nome"), rs.getString("descrizione"), rs.getString("ubicazione")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ristoranti;
    }

    public static void main(String[] args) {
        RistoranteDAO ristoranteDAO = new RistoranteDAOJDBC();
        List<Ristorante> ristoranti = ristoranteDAO.findAll();
        for (Ristorante ristorante : ristoranti) {
            System.out.println(ristorante);
        }
        System.out.println("Ristoranti con Pizza Margherita");
        List<Ristorante> ristorantiMargherita = ristoranteDAO.findAllByPiattoName("pizza margherita");
        for (Ristorante ristorante : ristorantiMargherita) {
            System.out.println(ristorante);
        }
    }
}
