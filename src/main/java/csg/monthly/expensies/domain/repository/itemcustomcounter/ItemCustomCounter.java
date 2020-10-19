package csg.monthly.expensies.domain.repository.itemcustomcounter;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity(name = "ItemCustomCounter")
@Table(name = "CUSTOM_COUNTER_ITEM")
@IdClass(ItemCustomCounter.ItemCustomCounterId.class)
public class ItemCustomCounter {

    @Id
    private int customCounterId;
    @Id
    private int itemId;

    public ItemCustomCounter() {
    }

    public ItemCustomCounter(final int customCounterId, final int itemId) {
        this.customCounterId = customCounterId;
        this.itemId = itemId;
    }

    public int getCustomCounterId() {
        return customCounterId;
    }

    public void setCustomCounterId(final int customCounterId) {
        this.customCounterId = customCounterId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(final int itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ItemCustomCounter that = (ItemCustomCounter) o;
        return customCounterId == that.customCounterId && itemId == that.itemId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(customCounterId, itemId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ItemCustomCounter{");
        sb.append("customCounterId=").append(customCounterId);
        sb.append(", itemId=").append(itemId);
        sb.append('}');
        return sb.toString();
    }

    public static class ItemCustomCounterId implements Serializable {
        private int customCounterId;
        private int itemId;

        public ItemCustomCounterId() {
        }

        public ItemCustomCounterId(final int customCounterId, final int itemId) {
            this.customCounterId = customCounterId;
            this.itemId = itemId;
        }

        public int getCustomCounterId() {
            return customCounterId;
        }

        public void setCustomCounterId(final int customCounterId) {
            this.customCounterId = customCounterId;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(final int itemId) {
            this.itemId = itemId;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            final ItemCustomCounterId that = (ItemCustomCounterId) o;
            return customCounterId == that.customCounterId && itemId == that.itemId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(customCounterId, itemId);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ItemCustomCounterId{");
            sb.append("customCounterId=").append(customCounterId);
            sb.append(", itemId=").append(itemId);
            sb.append('}');
            return sb.toString();
        }
    }
}
