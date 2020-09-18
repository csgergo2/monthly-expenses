package csg.monthly.expensies.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Tag")
public class Tag {
    @Id
    private int id;
    private String name;
    private int prio;

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
        return prio == tag.prio && Objects.equals(name, tag.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, prio);
    }

    @Override
    public String toString() {
        return name;
    }
}
