package csg.monthly.expensies.exception;

public class SameMonthDateException extends MonthlyExpensesException {

    public SameMonthDateException(final String message) {
        super(message);
    }
}
