package it.unical.demacs.informatica.ristoranti.persistence.DAO.implJDBC;

import it.unical.demacs.informatica.ristoranti.model.UserRole;
import it.unical.demacs.informatica.ristoranti.model.Utente;
import it.unical.demacs.informatica.ristoranti.persistence.DAO.UserDAO;
import it.unical.demacs.informatica.ristoranti.persistence.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOJDBC implements UserDAO {

    private final Connection connection;

    public UserDAOJDBC() {
        this.connection = DBManager.getInstance().getConnection();
    }

    @Override
    public Utente findByPrimaryKey(String username) {
        String query = "SELECT username, password , role FROM utente WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Utente(
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        UserRole.valueOf(resultSet.getString("role"))
                );
            }
            return null;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public void save(Utente utente) {
        String query = "INSERT INTO utente (username, password , role) VALUES (?, ? , ?) ";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utente.getUsername());
            statement.setString(2, utente.getPassword());
            statement.setString(3, utente.getRole().toString());
            statement.executeUpdate();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }
}
