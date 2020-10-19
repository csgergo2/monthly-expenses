package csg.monthly.expensies.view.panel.items;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JScrollPane;

import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGPanel;

public class ItemsTablePanel extends JScrollPane {
    private static final int RANGE_X = 1;
    private static final int RANGE_Y = 1;

    private CsGPanel panel = new CsGPanel(Name.MENU_PANEL, new ItemsTableLayout()) {
    };

    private int width = 737;

    public ItemsTablePanel(final Enum<?> name) {
        panel.setPreferredSize(new Dimension(width, 10));
        panel.setBackground(Color.BLACK);
        panel.setLayout(new ItemsTableLayout());
        panel.setVisible(true);

        setViewportView(panel);
        setName(name.name());
        setVisible(true);
    }

    public void setScrollBarToBottom() {
        getVerticalScrollBar().setMaximum(getHeight() - 50);
        getVerticalScrollBar().setValue(getVerticalScrollBar().getMaximum());
    }

    @Override
    public Component add(final Component comp) {
        if (comp instanceof TableItem) {
            panel.add(comp);
            panel.setPreferredSize(new Dimension(width, (int) panel.getPreferredSize().getHeight() + 26));
        } else {
            super.add(comp);
        }
        return comp;
    }

    private static final class ItemsTableLayout implements LayoutManager {
        @Override
        public void addLayoutComponent(final String name, final Component comp) {
        }

        @Override
        public void removeLayoutComponent(final Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(final Container parent) {
            return null;
        }

        @Override
        public Dimension minimumLayoutSize(final Container parent) {
            return null;
        }

        @Override
        public void layoutContainer(final Container parent) {
            int currentY = RANGE_Y;
            for (Component component : parent.getComponents()) {
                component.setBounds(RANGE_X, currentY, 797, 25);
                currentY = currentY + 25 + RANGE_Y;
            }
        }
    }
}
