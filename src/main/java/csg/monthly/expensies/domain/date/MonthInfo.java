package csg.monthly.expensies.domain.date;

import java.util.List;
import java.util.Map;

import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.Tag;

public class MonthInfo implements Comparable<MonthInfo> {

    private MonthDate month;
    private Map<Tag, List<Item>> items;

    public MonthInfo(final MonthDate month, final Map<Tag, List<Item>> items) {
        this.month = month;
        this.items = items;
    }

    public MonthDate getMonth() {
        return month;
    }

    public void setMonth(final MonthDate month) {
        this.month = month;
    }

    public Map<Tag, List<Item>> getItems() {
        return items;
    }

    public void setItems(final Map<Tag, List<Item>> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(month.getYear());
        sb.append(' ');
        sb.append(month.getMonth().getTranslatedName());
        return sb.toString();
    }

    @Override
    public int compareTo(final MonthInfo o) {
        return month.compareTo(o.getMonth());
    }
}
