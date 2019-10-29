package slogo.frontend;

public class ChangedVariable {
    private String changedVariable = "";

    public String getChangedVariable() {
        String str = changedVariable;
        changedVariable = "";
        return str;
    }

    public void setChangedVariable(String changedVariable) {
        this.changedVariable = changedVariable;
    }
}
