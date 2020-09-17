package csg.monthly.expensies.domain.date;

import java.time.LocalDate;

import csg.monthly.expensies.exception.MonthlyExpensesException;

public enum Month {
    JANUARY(1, "Január"),
    FEBRUARY(2, "Február"),
    MARCH(3, "Március"),
    APRIL(4, "Április"),
    MAY(5, "Május"),
    JUNE(6, "Június"),
    JULE(7, "Július"),
    AUGUST(8, "Augusztus"),
    SEPTEMBER(9, "Szeptember"),
    OCTOBER(10, "Október"),
    NOVEMBER(11, "November"),
    DECEMBER(12, "December");
    private final int monthOfYear;
    private final String translatedName;

    Month(final int monthOfYear, final String translatedName) {
        this.monthOfYear = monthOfYear;
        this.translatedName = translatedName;
    }

    public int getMonthOfYear() {
        return monthOfYear;
    }

    public String getTranslatedName() {
        return translatedName;
    }

    @Override
    public String toString() {
        return translatedName;//todo english
    }

    public static Month getCurrent() {
        final int monthValue = LocalDate.now().getMonthValue();
        for (Month month : Month.values()) {
            if (month.getMonthOfYear() == monthValue) {
                return month;
            }
        }
        throw new MonthlyExpensesException("Could not find month for current month");
    }
}
