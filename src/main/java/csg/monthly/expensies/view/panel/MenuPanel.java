package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.panel.FilteringPanel.FILTERING_PANEL;
import static csg.monthly.expensies.view.panel.MainPanel.MAIN_PANEL;
import static csg.monthly.expensies.view.panel.MonthlySumPanel.MONTHLY_SUM_PANEL;
import static csg.monthly.expensies.view.util.Name.MENU_PANEL_FILTERING_PANEL_BUTTON;
import static csg.monthly.expensies.view.util.Name.MENU_PANEL_START_BUTTON;
import static csg.monthly.expensies.view.util.Name.MONTHLY_SUM_PANEL_BUTTON;

import java.awt.event.ActionEvent;

import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGPanel;

public class MenuPanel extends CsGPanel {
    public static final MenuPanel MENU_PANEL = new MenuPanel();

    private MenuPanel() {
        super(Name.MENU_PANEL, MELayout.LAYOUT);
        add(new CsGButton(MENU_PANEL_START_BUTTON, "Itemek", this::mainPanelStart));//todo english
        add(new CsGButton(MENU_PANEL_FILTERING_PANEL_BUTTON, "Szűrő panel", this::filteringPanel));//todo english

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
}
