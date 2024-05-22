package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition;

public class MiCRUD {

    // atributtes

    private String driver = "com.mysql.cj.jdbc.Driver";
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
            this.statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // create

    public boolean createTable(String name, MyColumn[] columns, MyConstraint[] constraints) {

        if (columns == null || constraints == null) {
            return false;
        } else {
            for (MyColumn colActual : columns) {
                if (colActual == null) {
                    return false;
                }

            }
            for (MyConstraint constActual : constraints) {
                if (constActual == null) {
                    return false;
                }

            }
        }
        String myQuery = "CREATE TABLE ".concat(name) + " (";
        for (int i = 0; i < columns.length; i++) {
            myQuery = myQuery + (columns[i].getColName()) + " " + columns[i].getColType();
            if (columns[i].isNulleable()) {
                myQuery = myQuery + ", ";
            } else {
                myQuery = myQuery + " NOT NULL" + ", ";
            }
            // there is always at least one constraint for the last,
        }

        for (int i = 0; i < constraints.length - 1; i++) {
            myQuery = myQuery + "CONSTRAINT " + constraints[i].getParams()[0];
            if (constraints[i].getParams().length == 2) { // PRIMARY KEY
                myQuery = myQuery + " PRIMARY KEY (";
                myQuery = myQuery + constraints[i].getParams()[1] + ")";
            } else { // FOREIGN KEY
                myQuery = myQuery + " FOREIGN KEY (";
                myQuery = myQuery + constraints[i].getParams()[1] + ")";
                myQuery = myQuery + " REFERENCES ";
                myQuery = myQuery + constraints[i].getParams()[2] + "(";
                myQuery = myQuery + constraints[i].getParams()[3] + ")";

            }
            myQuery = myQuery + ", ";

        }
        myQuery = myQuery + "CONSTRAINT " + constraints[constraints.length - 1].getParams()[0];
        if (constraints[constraints.length - 1].getParams().length == 2) { // PRIMARY KEY
            myQuery = myQuery + " PRIMARY KEY (";
            myQuery = myQuery + constraints[constraints.length - 1].getParams()[1] + ")";
        } else { // FOREIGN KEY
            myQuery = myQuery + " FOREIGN KEY (";
            myQuery = myQuery + constraints[constraints.length - 1].getParams()[1] + ")";
            myQuery = myQuery + " REFERENCES ";
            myQuery = myQuery + constraints[constraints.length - 1].getParams()[2] + "(";
            myQuery = myQuery + constraints[constraints.length - 1].getParams()[3] + ")";

        }
        myQuery = myQuery + ");";

        try {
            return (0 == this.statement.executeUpdate(myQuery));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        // copy and paste for last element of constraint

    }

    // read
    public String[] readBD(String[] select, String[] from, String where) {

        if (select == null || from == null) {
            return null;
        } else {
            for (String string : select) {
                if (string == null) {
                    return null;
                }
            }
            for (String string : from) {
                if (string == null) {
                    return null;
                }
            }
        }

        String myQuery = "SELECT ";

        for (int i = 0; i < select.length - 1; i++) {
            myQuery = myQuery + select[i] + ", ";
        }
        myQuery = myQuery + select[select.length - 1] + " ";

        myQuery = myQuery + "FROM ";
        for (int i = 0; i < from.length - 1; i++) {
            myQuery = myQuery + from[i] + ", ";
        }

        myQuery = myQuery + from[from.length - 1];

        if (where != null) {
            myQuery = myQuery + " WHERE " + where + " ;";
        } else {
            myQuery = myQuery + ";";
        }

        try {
            ResultSet resultSet = this.statement.executeQuery(myQuery);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numRows = 0;
            int numCol = metaData.getColumnCount();
            while (resultSet.next()) {
                numRows = resultSet.getRow();
            }
            String[] vista = new String[numRows];
            resultSet.beforeFirst();
            for (int i = 0; i < vista.length; i++) {
                resultSet.next();
                vista[i] = "";
                for (int j = 1; j < numCol; j++) {
                    vista[i] = vista[i] + resultSet.getString(j) + " / ";
                }
                vista[i] = vista[i] + resultSet.getString(numCol);

            }
            return vista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
    // update

    // delete
    public int deleteRows(String table, String condition) {

        String query = "DELETE FROM " + table + " Where " + condition + ";";

        try {
            return (this.statement.executeUpdate(query));
        } catch (SQLException e) {
            return -1;
        }
    }

    public boolean dropTable(String table) {
        try {
            this.statement.executeUpdate("DROP TABLE " + table + ";");
            return true;
        } catch (SQLException e) {
            return false;
        }

    }
}
