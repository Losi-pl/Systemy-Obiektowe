package org.losi.site.auth;

import org.losi.site.database.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager {
    private final DatabaseConnection dbConnection;

    public AccountManager(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * Rejestracja nowego użytkownika.
     * @param username nazwa użytkownika
     * @param password hasło (jawne)
     * @throws SQLException gdy błąd bazy
     */
    public void register(String username, String password) throws SQLException {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        String sql = "INSERT INTO users (name, password) VALUES (?, ?)";
        Connection conn = dbConnection.getConnection();   // NIE zamykamy!
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, hashed);
            pstmt.executeUpdate();
        }
    }

    /**
     * Uwierzytelnienie użytkownika.
     * @param username nazwa użytkownika
     * @param password hasło (jawne)
     * @return true jeśli nazwa i hasło są poprawne
     * @throws SQLException gdy błąd bazy
     */
    public boolean authenticate(String username, String password) throws SQLException {
        String sql = "SELECT password FROM users WHERE name = ?";
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return BCrypt.checkpw(password, rs.getString("password"));
                }
            }
        }
        return false;
    }


    /**
     * Pobiera konto na podstawie nazwy użytkownika.
     */
    public Account getAccount(String username) throws SQLException {
        String sql = "SELECT id, name FROM users WHERE name = ?";
        Connection conn = dbConnection.getConnection();
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(rs.getInt("id"), rs.getString("name"));
                }
            }
        }
        return null;
    }

    /**
     * Pobiera konto na podstawie ID.
     */
    @SuppressWarnings("unused")
    public Account getAccount(int id) throws SQLException {
        String sql = "SELECT id, name FROM users WHERE id = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(rs.getInt("id"), rs.getString("name"));
                }
            }
        }
        return null;
    }
}