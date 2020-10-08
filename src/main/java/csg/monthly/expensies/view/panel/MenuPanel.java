package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.panel.MainPanel.MAIN_PANEL;
import static csg.monthly.expensies.view.panel.MonthlySumPanel.MONTHLY_SUM_PANEL;
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
        add(new CsGButton(MONTHLY_SUM_PANEL_BUTTON, "Havi összegzés", this::monthlySumPanel));//todo english

        setVisible(true);
    }

    private void mainPanelStart(ActionEvent event) {
        setVisible(false);
        MAIN_PANEL.setVisible(true);
    }

    private void monthlySumPanel(ActionEvent event) {
        setVisible(false);
        MONTHLY_SUM_PANEL.setVisible(true);
    }
}
