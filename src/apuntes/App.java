package apuntes;

import CRUD.MiCRUD;
import CRUD.MyColumn;
import CRUD.MyConstraint;

public class App {
    public static void main(String[] args) {

        MiCRUD miAcceso = new MiCRUD("ferreteria");

        MyColumn[] columnas = new MyColumn[2];
        columnas[0] = new MyColumn();
        columnas[0].setColName("nombre");
        columnas[0].setColType("varchar(32)");
        columnas[1] = new MyColumn();
        columnas[1].setColName("apellido");
        columnas[1].setColType("varchar(32)");
        columnas[1].setNulleable(true);

        MyConstraint[] restricciones = new MyConstraint[1];
        restricciones[0] = new MyConstraint(true);
        restricciones[0].setParams(new String[] { "pk_nombre", "nombre" });
        // 1er caso- 0 columnas
        // con 1 constraint
        // con mas de 1 constraint

        // 2. 0 constraints
        // 2.1 con 1 columna
        // 2.2 con + de 1 columna

        // MyConstraint[] restricciones = new MyConstraint[1];
        miAcceso.initDriver();
        miAcceso.initConnection();
        miAcceso.createStatement();

        System.out.println(miAcceso.createTable("enfermos", columnas, restricciones));

        System.out.println(miAcceso.dropTable("enfermos"));

        String[] vista = miAcceso.readBD(new String[] { "cliente.nombre as cliente", "empleado.nombre as empleado" },
                new String[] { "cliente", "empleado", "asesora" },
                "cliente.id_cliente = asesora.id_cliente and asesora.id_empleado = empleado.dni");

        for (String string : vista) {
            System.out.println(string);
        }

    }

}
