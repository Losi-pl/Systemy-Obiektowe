package org.losi.site;

import org.losi.site.auth.Account;
import org.losi.site.auth.AccountManager;
import org.losi.site.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        DatabaseConnection dbConn = new DatabaseConnection();
        try {
            dbConn.connect("test.db");
            Connection conn = dbConn.getConnection();   // pobieramy raz

            // Tworzymy tabelę – używamy Statement, ale nie zamykamy Connection
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, password TEXT)");
            }

            AccountManager accountManager = new AccountManager(dbConn);
            accountManager.register("ania", "haslo123");
            boolean ok = accountManager.authenticate("ania", "haslo123");
            System.out.println("Logowanie: " + ok);

            Account acc = accountManager.getAccount("ania");
            System.out.println("Konto: " + acc);

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            dbConn.disconnect();   // dopiero tutaj zamykamy połączenie
        }
    }
}
