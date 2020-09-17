package csg.monthly.expensies.view.util;

import java.awt.*;
import java.util.Arrays;

public interface MELayout extends LayoutManager {
    default void addLayoutComponent(final String name, final Component comp) {
    }

    default void removeLayoutComponent(final Component comp) {
    }

    default Dimension preferredLayoutSize(final Container parent) {
        return null;
    }

    default Dimension minimumLayoutSize(final Container parent) {
        return null;
    }

    default void layoutContainer(final Container parent) {
        Arrays.stream(parent.getComponents()).forEach(component -> component.setBounds(Name.valueOf(component.getName()).getRectangle()));
    }
}
