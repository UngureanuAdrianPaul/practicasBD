package CRUD;



public class MyConstraint {

    // only two types: primary and foreign
    private String[] params;

    public MyConstraint(boolean isPrimary) {

        // 1. Name of constraint
        // 2. Name of affected column
        // 3.name of refenced table if it's foreign
        // 4. Name of referenced column if it's foreign

        if (isPrimary) {
            params = new String[2];
        } else {
            params = new String[4];

        }
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    

}
