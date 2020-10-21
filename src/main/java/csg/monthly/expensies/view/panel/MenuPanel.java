package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.panel.CustomCounterPanel.CUSTOM_COUNTER_PANEL;
import static csg.monthly.expensies.view.panel.FilteringPanel.FILTERING_PANEL;
import static csg.monthly.expensies.view.panel.MainPanel.MAIN_PANEL;
import static csg.monthly.expensies.view.panel.MonthlySumPanel.MONTHLY_SUM_PANEL;
import static csg.monthly.expensies.view.panel.tag.TagPanel.TAG_PANEL;
import static csg.swing.CsGFrameWindow.PREFERRED_HEIGHT;
import static csg.swing.CsGFrameWindow.PREFERRED_WIDTH;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import csg.monthly.expensies.view.panel.tag.TagPanel;
import csg.swing.CsGLayout;
import csg.swing.CsGMenu;
import csg.swing.CsGPanel;

public class MenuPanel extends CsGPanel {
    public static final MenuPanel MENU_PANEL = new MenuPanel();

    private MenuPanel() {
        super(Name.MENU_PANEL, (CsGLayout) name -> Name.valueOf(name).getRectangle());

        CsGMenu menuBar = new CsGMenu(Name.MENU_LANE);
        menuBar.addMenuItem("Itemek", "Itemek kezelése", this::mainPanelStart);//todo english
        menuBar.addMenuSeparator("Itemek");//todo english
        menuBar.addMenuItem("Itemek", "Havi összegző", this::monthlySumPanel);//todo english
        menuBar.addMenuItem("Itemek", "Item szűrő", this::filteringPanel);//todo english
        menuBar.addMenuItem("Egyéb", "Tag kezelés", this::tagPanel);//todo english
        menuBar.addMenuItem("Egyéb", "Egyéni számláló", this::customCounterPanel);//todo english

        add(menuBar);

        add(MainPanel.MAIN_PANEL);
        add(FilteringPanel.FILTERING_PANEL);
        add(MonthlySumPanel.MONTHLY_SUM_PANEL);
        add(TagPanel.TAG_PANEL);
        add(CustomCounterPanel.CUSTOM_COUNTER_PANEL);

        setVisible(true);
    }

    private void mainPanelStart(ActionEvent event) {
        panelSwitch(true, false, false, false, false);
    }

    private void filteringPanel(ActionEvent event) {
        panelSwitch(false, true, false, false, false);
    }

    private void monthlySumPanel(ActionEvent event) {
        panelSwitch(false, false, true, false, false);
    }

    private void tagPanel(ActionEvent event) {
        panelSwitch(false, false, false, true, false);
    }

    private void customCounterPanel(ActionEvent event) {
        panelSwitch(false, false, false, false, true);
    }

    private void panelSwitch(boolean main, boolean filtering, boolean monthlySum, boolean tag, boolean custom) {
        MAIN_PANEL.setVisible(main);
        FILTERING_PANEL.setVisible(filtering);
        MONTHLY_SUM_PANEL.setVisible(monthlySum);
        TAG_PANEL.setVisible(tag);
        CUSTOM_COUNTER_PANEL.setVisible(custom);
    }

    public enum Name {

        MENU_PANEL(0, 0, PREFERRED_WIDTH, PREFERRED_HEIGHT),
        MENU_LANE(0, 0, PREFERRED_WIDTH, 23),
        FILTERING_PANEL(0, 25, PREFERRED_WIDTH, PREFERRED_HEIGHT),
        MAIN_PANEL(0, 25, PREFERRED_WIDTH, PREFERRED_HEIGHT),
        MONTHLY_SUM_PANEL(0, 25, PREFERRED_WIDTH, PREFERRED_HEIGHT),
        TAG_PANEL(0, 25, PREFERRED_WIDTH, PREFERRED_HEIGHT),
        CUSTOM_COUNTER(0, 25, PREFERRED_WIDTH, PREFERRED_HEIGHT);

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
