package csg.monthly.expensies.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import csg.monthly.expensies.domain.date.Month;

@Entity(name = "Item")
public class Item implements Comparable<Item> {

    @Id
    private int id;

    private String name;

    @OneToOne
    @JoinColumn(name = "tag", foreignKey = @ForeignKey(name = "item_on_tag"))
    private Tag tag;

    private int amount;
    @Column(name = "is_income")
    private boolean isIncome;
    @Column(name = "is_end_month")
    private boolean isEndMonth;
    private Date date;

    private int year;
    @Enumerated(EnumType.STRING)
    private Month month;

    public Item(final String name, final Tag tag, final int amount, final boolean isIncome, final boolean isEndMonth, final Date date, final int year,
                final Month month) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.amount = amount;
        this.isIncome = isIncome;
        this.isEndMonth = isEndMonth;
        this.date = date;
        this.year = year;
        this.month = month;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(final Tag tag) {
        this.tag = tag;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(final boolean income) {
        this.isIncome = income;
    }

    public boolean isEndMonth() {
        return isEndMonth;
    }

    public void setEndMonth(final boolean isEndMonth) {
        this.isEndMonth = isEndMonth;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(final Month month) {
        this.month = month;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", tag=").append(tag);
        sb.append(", amount=").append(amount);
        sb.append(", isIncome=").append(isIncome);
        sb.append(", isEndMonth=").append(isEndMonth);
        sb.append(", date=").append(date);
        sb.append(", year=").append(year);
        sb.append(", month=").append(month);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(final Item o) {
        final int comparedDate = date.compareTo(o.date);
        if (comparedDate != 0) {
            return comparedDate;
        }
        return name.compareTo(o.name);
    }
}
