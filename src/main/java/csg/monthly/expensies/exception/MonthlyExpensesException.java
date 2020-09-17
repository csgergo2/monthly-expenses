package csg.monthly.expensies.exception;

public class MonthlyExpensesException extends RuntimeException {

    public MonthlyExpensesException() {
        super("Business exception occurred!");
    }

    public MonthlyExpensesException(final String message) {
        super(message);
    }

    public MonthlyExpensesException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
