package csg.monthly.expensies.domain;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "TagComment")
@Table(name = "tag_comment")
public class TagComment {
    @Id
    private int id;
    private String comment;

    private int tag;

    public TagComment() {
    }

    public TagComment(final String comment, final int tag) {
        this.comment = comment;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(final int tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TagComment that = (TagComment) o;
        return id == that.id && Objects.equals(comment, that.comment) && Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, comment, tag);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TagComment{");
        sb.append("id=").append(id);
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", tag=").append(tag);
        sb.append('}');
        return sb.toString();
    }
}
