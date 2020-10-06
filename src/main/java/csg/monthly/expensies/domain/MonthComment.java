package csg.monthly.expensies.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import csg.monthly.expensies.domain.date.Month;

@Entity(name = "MonthComment")
@Table(name = "month_comment")
public class MonthComment {

    @Id
    private int id;
    @Enumerated(EnumType.STRING)
    private Month month;
    private String comment;

    public MonthComment() {
    }

    public MonthComment(final Month month, final String comment) {
        this.month = month;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(final Month month) {
        this.month = month;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }
}
