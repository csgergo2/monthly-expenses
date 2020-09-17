package csg.monthly.expensies.practice;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity(name = "prio_group")
public class PrioGroup {
    @Id
    private int id;
    private String name;
    private int prio;

    public PrioGroup() {
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PrioGroup{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", prio=").append(prio);
        sb.append('}');
        return sb.toString();
    }
}
