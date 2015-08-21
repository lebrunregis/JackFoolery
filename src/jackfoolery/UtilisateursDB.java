/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jackfoolery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Recupere les infos sur la db
 * @author Régis
 */
public class UtilisateursDB {

    /**
     * Retourne la liste des utilisateurs
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public static ResultSet getListeUtilisateurs(String username, String password) throws SQLException {

        Connection con = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CarteDB",
                username,
                password);

        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result = stmt.executeQuery("SELECT * FROM utilisateurs");
        //  result.last();
        //    System.out.println(result.getRow()+" utilisateurs recus!");
        return result;

    }

    /**
     * Ajoute un utilisateur
     * @param username
     * @param email
     * @param password
     */
    public static void ajouterUtilisateur(String username, String email, String password) {

        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/CarteDB", "main", "main")) {
            Statement stmt = con.createStatement();
            String query = "INSERT INTO utilisateurs(usr_name ,usr_email,usr_pass)"
                    + "VALUES ('" + username + "','" + email + "','" + password + "')";
            stmt.execute(query);
            System.out.println("Utilisateur inséré avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**
     * Retourne si l'utilisateur existe ou non
     * @param username
     * @param password
     * @return
     */
    public static boolean utilisateurExiste(String email, String password) {

        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/CarteDB", "main", "main")) {
            Statement stmt = con.createStatement();
            String query = "SELECT COUNT(*) FROM utilisateurs WHERE usr_email = '" + email + "' AND usr_pass ='" + password + "'";

            ResultSet result = stmt.executeQuery(query);

            if (result.next()) {
                int nbResult = result.getInt(1);
                //System.out.println(nbResult);
                if (result.getInt(1) > 0) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /**
     * Enregistre une victoire d'un utilisateur sur un autre utilisateur
     * @param gagnant
     * @param perdant
     */
    public static void victoireUtilisateur(String gagnant, String perdant) {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/CarteDB", "main", "main")) {

            Statement stmt = con.createStatement();

            String query = "";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Retourne les vitoires et defaites entre 2 utilisateurs
     * @param joueur1
     * @param joueur2
     */
    public static void getStatsUtilisateurs(String joueur1, String joueur2) {
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/CarteDB", "main", "main")) {
            Statement stmt = con.createStatement();
            String query = "";
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Cree la db
     * @param username
     * @param password
     */
    public static void creerDB(String username, String password) {

        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/CarteDB;create=true", "main", "main")) {
            Statement stmt = con.createStatement();
            String query = "CREATE TABLE resultats (usr_email_a VARCHAR(64),usr_email_b VARCHAR(64) ,vic_a INTEGER,vic_b INTEGER)";
            stmt.execute(query);
            query = "CREATE TABLE utilisateurs ("
                    + "usr_name VARCHAR(32) NOT NULL,"
                    + "usr_email VARCHAR(64) NOT NULL PRIMARY KEY,"
                    + "usr_pass VARCHAR(32) NOT NULL)";
            stmt.execute(query);
            query = "CREATE TABLE equipe (usr_email_a VARCHAR(64),usr_email_b VARCHAR(64),equipe_nom VARCHAR(32) PRIMARY KEY)";
            stmt.execute(query);
            System.out.println("DB créée avec succès!");
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }

    /**
     * Efface la db
     * @param username
     * @param password
     */
    public static void effacerDB(String username, String password) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/CarteDB", username, password);
            Statement stmt = con.createStatement();
            String query = "DROP TABLE resultats ";
            stmt.execute(query);
            query = "DROP TABLE utilisateurs ";
            stmt.execute(query);
            query = "DROP TABLE equipe ";
            stmt.execute(query);
            con.close();
            System.out.println("DB effacée avec succès!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Remplis la db
     * @param username
     * @param password
     */
    public static void remplirDB(String username, String password) {
        try (Connection con = DriverManager.getConnection(
                "jdbc:derby://localhost:1527/CarteDB", username, password)) {
            Statement stmt = con.createStatement();
            String query = "INSERT INTO utilisateurs(usr_name ,usr_email,usr_pass)"
                    + "VALUES ('Regis','g36720@gmail.com','passwd')";
            stmt.execute(query);
            System.out.println("Utilisateur inséré avec succès!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
