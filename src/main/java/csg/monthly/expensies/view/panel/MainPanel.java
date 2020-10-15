package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.panel.ItemsPanel.ITEMS_PANEL;
import static csg.monthly.expensies.view.panel.MenuPanel.MENU_PANEL;
import static csg.monthly.expensies.view.util.Name.ITEMS_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.TAG_NEW_TAG_PANEL;

import java.awt.event.ActionEvent;

import csg.monthly.expensies.view.panel.tag.NewTagPanel;
import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGPanel;

public class MainPanel extends CsGPanel {
    public static final MainPanel MAIN_PANEL = new MainPanel();

    private NewItemPanel newItemPanel = new NewItemPanel(ITEMS_PANEL::refresh);
    private NewTagPanel newTagPanel = new NewTagPanel(TAG_NEW_TAG_PANEL, this::refresh);

    private MainPanel() {
        super(Name.MAIN_PANEL, MELayout.LAYOUT);

        add(ITEMS_PANEL);
        add(newItemPanel);
        add(newTagPanel);
        add(new CsGButton(ITEMS_BACK_BUTTON, "Vissza", this::back));//todo english
    }

    private void refresh(ActionEvent event) {
        ITEMS_PANEL.refresh();
        newItemPanel.refresh();
    }

    private void back(ActionEvent event) {
        setVisible(false);
        MENU_PANEL.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        ITEMS_PANEL.setVisible(visible);
        if (newItemPanel != null) {
            newItemPanel.setVisible(visible);
        }
        if (newTagPanel != null) {
            newTagPanel.setVisible(visible);
        }
        super.setVisible(visible);
    }
}
