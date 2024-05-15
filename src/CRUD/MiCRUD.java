package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MiCRUD {

    // atributtes

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/";
    private String user = "root";
    private String password = "";

    private Connection connection;
    private Statement statement;

    // constructor

    public MiCRUD(String basedeDatos) {

        this.url = this.url.concat(basedeDatos);

    }

    public boolean initDriver() {
        try {
            Class.forName(driver);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public boolean initConnection() {
        this.connection = null;
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public boolean closeConnection() {
        try {
            this.connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }

    }

    public boolean createStatement() {

        try {
            this.statement = connection.createStatement();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // create

    public boolean createTable(String name, MyColumn[] columns, MyConstraint[] constraints) {

        


    }

    // read

    // update

    // delete

}
