package csg.monthly.expensies.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "PrioGroup")
@Table(name = "prio_group")
public class PrioGroup {

    @Id
    private int id;

    private String name;
    private int prio;
    private String color;
    private String textColor;

    public PrioGroup() {
    }

    public PrioGroup(final String name, final int prio, final String color, final String textColor) {
        this.name = name;
        this.prio = prio;
        this.color = color;
        this.textColor = textColor;
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

    public int getPrio() {
        return prio;
    }

    public void setPrio(final int prio) {
        this.prio = prio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(final String textColor) {
        this.textColor = textColor;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PrioGroup prioGroup = (PrioGroup) o;
        return id == prioGroup.id && prio == prioGroup.prio && Objects.equals(name, prioGroup.name) && Objects.equals(color, prioGroup.color) &&
                Objects.equals(textColor, prioGroup.textColor);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, prio, color, textColor);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PrioGroup{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", prio=").append(prio);
        sb.append(", color='").append(color).append('\'');
        sb.append(", textColor='").append(textColor).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
