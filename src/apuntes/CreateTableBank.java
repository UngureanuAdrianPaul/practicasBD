package apuntes;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CreateTableBank {

    private String driver = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/bank";
    private String login = "root";
    private String password = "";
    private String createTableBank = "CREATE TABLE banco (" +
            "client VARCHAR(100) NOT NULL, " +
            "password VARCHAR(20) NOT NULL, " +
            "balance Integer NOT NULL, " +
            "PRIMARY KEY(client))";
    private String createInsertInto = "INSERT INTO banco" +
            "(client, password, balance) VALUES" +
            "('Maria','c', 10 )";
    private String createSelect = "SELECT client,password, balance FROM banco";
    private Connection connection;
    private Statement statement;

    // PARA CONEXIONES

    public void CreateTableBank() {

    }

    public void initConnection() {

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void creaStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void useStatement() {
        try {
            statement.executeUpdate(createTableBank);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void userInsertStatement() {
        try {
            statement.executeUpdate(createInsertInto);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void useSelect() {
        try {
            ResultSet vista = statement.executeQuery(createSelect);
            ResultSetMetaData datosVista = vista.getMetaData();
            while (vista.next()) {
                for (int i = 1; i <= datosVista.getColumnCount(); i++) {
                    System.out.print("[" + datosVista.getColumnName(i) + " : " + vista.getString(i) + "] ");

                }
                System.out.println();
            }
            System.out.println("El punto al final apunta a: " + vista.getRow());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeConnection() throws SQLException {

        try {
            connection.close();
        } catch (SQLException e) {
            throw e;
        }
    }

}
