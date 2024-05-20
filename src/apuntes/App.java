package apuntes;

import java.sql.SQLException;

import CRUD.MiCRUD;
import CRUD.MyColumn;
import CRUD.MyConstraint;

public class App {
    public static void main(String[] args) {

        MiCRUD miAcceso = new MiCRUD("bank");

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
        columns[1] = new MyColumn();
        columns[1].setColName("apellido");
        columns[1].setColType("varchar(30)");
        columns[1].setNulleable(true);

        MyConstraint[] restri = new MyConstraint[1];
        restri[0] = new MyConstraint(true);
        restri[0].setParams(new String[] { "pk_nombre", "nombre" });
        

        // MyConstraint[] restricciones = new MyConstraint[1];
        miAcceso.initDriver();
        miAcceso.initConnection();
        miAcceso.createStatement();
        String miQuery = miAcceso.createTable("enfermos", columns, restri);
        System.out.println(miQuery);
        if (miAcceso.useStatement(miQuery)) {
            System.out.println("Tabla creada");
        } else {
            System.out.println("ERROR!!");
        }

    }

}
