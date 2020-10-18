package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.CustomCounterPanel.CUSTOM_COUNTER_PANEL;
import static csg.monthly.expensies.view.panel.FilteringPanel.FILTERING_PANEL;
import static csg.monthly.expensies.view.panel.MainPanel.MAIN_PANEL;
import static csg.monthly.expensies.view.panel.MonthlySumPanel.MONTHLY_SUM_PANEL;
import static csg.monthly.expensies.view.panel.tag.TagPanel.TAG_PANEL;
import static csg.monthly.expensies.view.util.Name.MENU_PANEL_CUSTOM_COUNTER_BUTTON;
import static csg.monthly.expensies.view.util.Name.MENU_PANEL_FILTERING_PANEL_BUTTON;
import static csg.monthly.expensies.view.util.Name.MENU_PANEL_MONTHLY_SUM_BUTTON;
import static csg.monthly.expensies.view.util.Name.MENU_PANEL_START_BUTTON;
import static csg.monthly.expensies.view.util.Name.MENU_PANEL_TAG_PANEL_BUTTON;

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
        add(new CsGButton(MENU_PANEL_MONTHLY_SUM_BUTTON, "Havi összegző", this::monthlySumPanel));//todo english
        add(new CsGButton(MENU_PANEL_TAG_PANEL_BUTTON, "Tag kezelés", this::tagPanel));//todo english
        add(new CsGButton(MENU_PANEL_CUSTOM_COUNTER_BUTTON, "Egyéni számláló", this::customCounterPanel));//todo english

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
}
