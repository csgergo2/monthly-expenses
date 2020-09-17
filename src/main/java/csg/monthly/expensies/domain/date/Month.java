package csg.monthly.expensies.domain.date;

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
}
