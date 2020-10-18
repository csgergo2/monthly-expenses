package csg.monthly.expensies.view;

import static csg.monthly.expensies.view.util.Name.CUSTOM_COUNTER;

import java.awt.Rectangle;

import csg.monthly.expensies.view.panel.MenuPanel;
import csg.swing.CsGButton;
import csg.swing.CsGLayout;
import csg.swing.CsGPanel;

public class CustomCounterPanel extends CsGPanel {
    public static final CustomCounterPanel CUSTOM_COUNTER_PANEL = new CustomCounterPanel();

    private CustomCounterPanel() {
        super(CUSTOM_COUNTER, new CustomCounterPanelLayout());

        add(new CsGButton(Name.CUSTOM_COUNTER_BACK_BUTTON, "Vissza", event -> back()));

        setVisible(true);
    }

    private void back() {
        setVisible(false);
        MenuPanel.MENU_PANEL.setVisible(true);
    }

    private enum Name {
        CUSTOM_COUNTER_BACK_BUTTON(10, 10, 150, 25);

        private final int x;
        private final int y;
        private final int width;
        private final int height;

        Name(final int x, final int y, final int width, final int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public Rectangle getRectangle() {
            return new Rectangle(x, y, width, height);
        }
    }

    private static class CustomCounterPanelLayout implements CsGLayout {
        @Override
        public Rectangle getRectangleForComponentName(final String name) {
            return Name.valueOf(name).getRectangle();
        }
    }
}
