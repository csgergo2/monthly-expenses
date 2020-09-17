package csg.monthly.expensies.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "Item")
public class Item {
    @Id
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

    public Item() {
    }

    public Item(final String name, final Tag tag, final int amount, final Date date) {
        this(name, tag, amount, false, false, date);
    }

    public Item(final String name, final Tag tag, final int amount, final boolean isIncome, final boolean isEndMonth, final Date date) {
        this.name = name;
        this.tag = tag;
        this.amount = amount;
        this.isIncome = isIncome;
        this.isEndMonth = isEndMonth;
        this.date = date;
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

    public void setEndMonth(final boolean endMonth) {
        this.isEndMonth = endMonth;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("name='").append(name).append('\'');
        sb.append(", tag=").append(tag);
        sb.append(", amount=").append(amount);
        sb.append(", isIncome=").append(isIncome);
        sb.append(", isEndMonth=").append(isEndMonth);
        sb.append(", date=").append(date);
        sb.append('}');
        return sb.toString();
    }
}
