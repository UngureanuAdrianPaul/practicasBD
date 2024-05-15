package CRUD;

public class MyColumn {

    private String colName;
    private String colType;
    private boolean nulleable;

    public MyColumn() {
        this.colName = null;
        this.colType = null;
        this.nulleable = false;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColType() {
        return colType;
    }

    public void setColType(String colType) {
        this.colType = colType;
    }

    public boolean isNulleable() {
        return nulleable;
    }

    public void setNulleable(boolean nulleable) {
        this.nulleable = nulleable;
    }

}
