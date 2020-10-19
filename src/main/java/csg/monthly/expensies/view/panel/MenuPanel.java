package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.CustomCounterPanel.CUSTOM_COUNTER_PANEL;
import static csg.monthly.expensies.view.panel.FilteringPanel.FILTERING_PANEL;
import static csg.monthly.expensies.view.panel.MainPanel.MAIN_PANEL;
import static csg.monthly.expensies.view.panel.MonthlySumPanel.MONTHLY_SUM_PANEL;
import static csg.monthly.expensies.view.panel.tag.TagPanel.TAG_PANEL;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import csg.swing.CsGButton;
import csg.swing.CsGLayout;
import csg.swing.CsGPanel;

public class MenuPanel extends CsGPanel {
    public static final MenuPanel MENU_PANEL = new MenuPanel();

    private MenuPanel() {
        super(csg.monthly.expensies.view.util.Name.MENU_PANEL, (CsGLayout) name -> Name.valueOf(name).getRectangle());
        add(new CsGButton(Name.MENU_PANEL_START_BUTTON, "Itemek", this::mainPanelStart));//todo english
        add(new CsGButton(Name.MENU_PANEL_FILTERING_PANEL_BUTTON, "Szűrő panel", this::filteringPanel));//todo english
        add(new CsGButton(Name.MENU_PANEL_MONTHLY_SUM_BUTTON, "Havi összegző", this::monthlySumPanel));//todo english
        add(new CsGButton(Name.MENU_PANEL_TAG_PANEL_BUTTON, "Tag kezelés", this::tagPanel));//todo english
        add(new CsGButton(Name.MENU_PANEL_CUSTOM_COUNTER_BUTTON, "Egyéni számláló", this::customCounterPanel));//todo english

        setVisible(true);
    }

    private void mainPanelStart(ActionEvent event) {
        setVisible(false);
        MAIN_PANEL.setVisible(true);
    }

    private void filteringPanel(ActionEvent event) {
        setVisible(false);
        FILTERING_PANEL.setVisible(true);
    }

    private void monthlySumPanel(ActionEvent event) {
        setVisible(false);
        MONTHLY_SUM_PANEL.setVisible(true);
    }

    private void tagPanel(ActionEvent event) {
        setVisible(false);
        TAG_PANEL.setVisible(true);
    }

    private void customCounterPanel(ActionEvent event) {
        setVisible(false);
        CUSTOM_COUNTER_PANEL.setVisible(true);
    }

    private enum Name {
        MENU_PANEL_START_BUTTON(0, 0, 150, 25),
        MENU_PANEL_FILTERING_PANEL_BUTTON(150, 0, 150, 25),
        MENU_PANEL_MONTHLY_SUM_BUTTON(300, 0, 150, 25),
        MENU_PANEL_TAG_PANEL_BUTTON(450, 0, 150, 25),
        MENU_PANEL_CUSTOM_COUNTER_BUTTON(600, 0, 150, 25);

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
}
