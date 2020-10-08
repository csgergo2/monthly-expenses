package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.panel.ItemsPanel.ITEMS_PANEL;
import static csg.monthly.expensies.view.panel.MenuPanel.MENU_PANEL;
import static csg.monthly.expensies.view.panel.NewItemPanel.NEW_ITEM_PANEL;
import static csg.monthly.expensies.view.panel.NewTagPanel.NEW_TAG_PANEL;
import static csg.monthly.expensies.view.util.Name.ITEMS_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.MAIN_PANEL_REFRESH_BUTTON;

import java.awt.event.ActionEvent;

import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGPanel;

public class MainPanel extends CsGPanel {
    public static final MainPanel MAIN_PANEL = new MainPanel();

    private MainPanel() {
        super(Name.MAIN_PANEL, MELayout.LAYOUT);

        add(ITEMS_PANEL);
        add(NEW_ITEM_PANEL);
        add(NEW_TAG_PANEL);
        add(new CsGButton(MAIN_PANEL_REFRESH_BUTTON, "Frissítés", this::refresh));//todo english
        add(new CsGButton(ITEMS_BACK_BUTTON, "Vissza", this::back));//todo english
    }

    private void refresh(ActionEvent event) {
        ITEMS_PANEL.refresh();
        NEW_ITEM_PANEL.refresh();
        NEW_TAG_PANEL.refresh();
    }

    private void back(ActionEvent event) {
        setVisible(false);
        MENU_PANEL.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        ITEMS_PANEL.setVisible(visible);
        NEW_ITEM_PANEL.setVisible(visible);
        NEW_TAG_PANEL.setVisible(visible);
        super.setVisible(visible);
    }
}
