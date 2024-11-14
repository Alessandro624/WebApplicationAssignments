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
public class PiattoDAOJDBC implements PiattoDAO {

    private final Connection connection;

    public PiattoDAOJDBC() {
        this.connection = DBManager.getInstance().getConnection();
    }

    @Override
    public List<Piatto> findAll() {
        List<Piatto> piatti = new ArrayList<>();
        String query = "SELECT * FROM piatto";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Piatto piatto = new PiattoProxy(rs.getString("nome"), rs.getString("ingredienti"));
                piatti.add(piatto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return piatti;
    }

    @Override
    public Piatto findByPrimaryKey(String nome) {
        String query = "SELECT * FROM piatto WHERE nome = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, nome);
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return new PiattoProxy(rs.getString("nome"), rs.getString("ingredienti"));
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Piatto piatto) {
        String query = "INSERT INTO piatto(nome, ingredienti) VALUES(?, ?)"
                + "ON CONFLICT (nome) DO UPDATE SET ingredienti = EXCLUDED.ingredienti";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, piatto.getNome());
            st.setString(2, piatto.getIngredienti());
            st.executeUpdate();
            List<Ristorante> ristoranti = piatto.getRistoranti();
            if (ristoranti == null || ristoranti.isEmpty()) {
                return;
            }
            resetRelationInJoinTable(piatto.getNome());
            RistoranteDAO ristoranteDAO = DBManager.getInstance().getRistoranteDAO();
            for (Ristorante ristorante : ristoranti) {
                ristoranteDAO.save(ristorante);
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
        String query = "DELETE FROM ristorante_piatto WHERE piatto_nome = ?";
        PreparedStatement st = connection.prepareStatement(query);
        st.setString(1, nome);
        st.execute();
    }

    @Override
    public void delete(String nome) {
        String query = "DELETE FROM piatto WHERE nome = ?";
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
    public List<Piatto> findAllByRistoranteName(String ristoranteNome) {
        List<Piatto> piatti = new ArrayList<>();
        String query = "SELECT p.nome, p.ingredienti FROM piatto p " +
                "JOIN ristorante_piatto rp ON p.nome = rp.piatto_nome " +
                "WHERE rp.ristorante_nome = ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, ristoranteNome);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                piatti.add(new Piatto(rs.getString("nome"), rs.getString("ingredienti")));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return piatti;
    }

    public static void main(String[] args) {
        PiattoDAO piattoDAO = new PiattoDAOJDBC();
        List<Piatto> piatti = piattoDAO.findAll();
        for (Piatto piatto : piatti) {
            System.out.println(piatto);
        }
        System.out.println("Piatti della Cascina di Zio Tobia");
        List<Piatto> piattiCascina = piattoDAO.findAllByRistoranteName("La Cascina di Zio Tobia");
        for (Piatto piatto : piattiCascina) {
            System.out.println(piatto);
        }
    }
}
