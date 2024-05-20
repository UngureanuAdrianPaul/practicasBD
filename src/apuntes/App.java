package apuntes;

import java.sql.SQLException;

import CRUD.MiCRUD;
import CRUD.MyColumn;
import CRUD.MyConstraint;

public class App {
    public static void main(String[] args) {

        MiCRUD miAcceso = new MiCRUD("bank");
        // 0 null o algo null
        // 1er caso- 0 columnas
        // con 1 constraint
        // con mas de 1 constraint

        // 2. 0 constraints
        // 2.1 con 1 columna
        // 2.2 con + de 1 columna

        MyColumn[] columns = new MyColumn[2];
        columns[0] = new MyColumn();
        columns[0].setColName("nombre");
        columns[0].setColType("varchar(30)");
        MyConstraint[] restri = new MyConstraint[5];
        

        // MyConstraint[] restricciones = new MyConstraint[1];

        System.out.println(miAcceso.createTable("miTabla", columns, restri));

    }

}
