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
        columns[1] = new MyColumn();
        columns[1].setColName("apellido");
        columns[1].setColType("varchar(30)");
        columns[1].setNulleable(true);

        MyConstraint[] restri = new MyConstraint[3];
        restri[0] = new MyConstraint(true);
        restri[0].setParams(new String[] { "pk_nombre", "col_nombre" });
        restri[1] = new MyConstraint(false);
        restri[1].setParams(new String[] { "fk_nombre", "apellido", "habitacion", "num" });
        restri[2] = new MyConstraint(false);
        restri[2].setParams(new String[] { "fk_nombre1", "apellido1", "habitacion1", "num" });

        // MyConstraint[] restricciones = new MyConstraint[1];

        System.out.println(miAcceso.createTable("miTabla", columns, restri));

    }

}
