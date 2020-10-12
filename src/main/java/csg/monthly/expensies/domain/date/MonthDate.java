package csg.monthly.expensies.domain.date;

import java.util.Objects;

import csg.monthly.expensies.exception.SameMonthDateException;

public class MonthDate implements Comparable<MonthDate> {

    private Month month;
    private int year;

    public MonthDate() {
    }

    public MonthDate(final Month month, final int year) {
        this.month = month;
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(final Month month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MonthDate monthDate = (MonthDate) o;
        return year == monthDate.year && month == monthDate.month;
    }

    @Override
    public int hashCode() {

        return Objects.hash(month, year);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MonthDate{");
        sb.append("month=").append(month);
        sb.append(", year=").append(year);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(final MonthDate o) {
        if (year == o.year && month == o.month) {
            throw new SameMonthDateException("Month and year can not be the same! Current MonthDate: " + this + "; Compared to " + o);
        }
        return year > o.year ? -1 : year < o.year ? 1 : month.getMonthOfYear() > o.getMonth().getMonthOfYear() ? -1 : 1;
    }
}
