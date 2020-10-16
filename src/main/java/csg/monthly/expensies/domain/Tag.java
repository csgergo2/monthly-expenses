package csg.monthly.expensies.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "Tag")
public class Tag implements Comparable<Tag> {
    @Id
    private int id;
    private String name;
    private int prio;
    @OneToOne
    @JoinColumn(name = "prio_group", foreignKey = @ForeignKey(name = "tag_on_prio_group"))
    private PrioGroup prioGroup;

    public Tag() {
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
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, prio);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(final Tag o) {
        final int prioCompared = Integer.compare(prio, o.prio);
        return prioCompared != 0 ? prioCompared : name.compareTo(o.name);
    }
}
