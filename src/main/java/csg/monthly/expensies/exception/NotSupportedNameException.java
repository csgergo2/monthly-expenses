package csg.monthly.expensies.exception;

public class NotSupportedNameException extends MonthlyExpensesException {

    public NotSupportedNameException(final String name) {
        super("Not supported name: " + name);
    }
}
