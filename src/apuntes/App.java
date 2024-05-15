package apuntes;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        CreateTableBank miTable = new CreateTableBank();
        miTable.initConnection();
        miTable.creaStatement();
        miTable.useSelect();
        miTable.closeConnection();


    }


}
