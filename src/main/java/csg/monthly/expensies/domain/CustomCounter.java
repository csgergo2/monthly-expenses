package csg.monthly.expensies.domain;

import java.util.Objects;

import javax.persistence.Id;

public class CustomCounter {
    @Id
    private int id;
    private String name;
    private String comment;

    public CustomCounter() {
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

    public String getText() {
        return comment;
    }

    public void setText(final String text) {
        this.comment = text;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CustomCounter that = (CustomCounter) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return name;
    }
}
