package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.panel.CustomCounterPanel.CUSTOM_COUNTER_PANEL;
import static csg.monthly.expensies.view.panel.FilteringPanel.FILTERING_PANEL;
import static csg.monthly.expensies.view.panel.MainPanel.MAIN_PANEL;
import static csg.monthly.expensies.view.panel.MonthlySumPanel.MONTHLY_SUM_PANEL;
import static csg.monthly.expensies.view.panel.tag.TagPanel.TAG_PANEL;
import static csg.swing.CsGFrameWindow.PREFERRED_WIDTH;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGLayout;
import csg.swing.CsGMenu;
import csg.swing.CsGPanel;

public class MenuPanel extends CsGPanel {
    public static final MenuPanel MENU_PANEL = new MenuPanel();

    private MenuPanel() {
        super(Name.MENU_PANEL, (CsGLayout) name -> new Rectangle(0, 1, PREFERRED_WIDTH, 23));

        CsGMenu menuBar = new CsGMenu(Name.MENU_PANEL);
        menuBar.addMenuItem("Itemek", "Itemek kezelése", this::mainPanelStart);//todo english
        menuBar.addMenuSeparator("Itemek");//todo english
        menuBar.addMenuItem("Itemek", "Havi összegző", this::monthlySumPanel);//todo english
        menuBar.addMenuItem("Itemek", "Item szűrő", this::filteringPanel);//todo english
        menuBar.addMenuItem("Egyéb", "Tag kezelés", this::tagPanel);//todo english
        menuBar.addMenuItem("Egyéb", "Egyéni számláló", this::customCounterPanel);//todo english

        add(menuBar);

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
