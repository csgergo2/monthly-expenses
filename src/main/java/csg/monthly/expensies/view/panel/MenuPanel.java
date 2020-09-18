package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.util.Name.NEW_ITEM_BUTTON;
import static csg.monthly.expensies.view.util.Name.NEW_TAG_BUTTON;

import java.awt.event.ActionEvent;

import csg.monthly.expensies.view.util.MEButton;
import csg.monthly.expensies.view.util.MEPanel;
import csg.monthly.expensies.view.util.Name;

public class MenuPanel extends MEPanel {
    public static final MenuPanel MENU_PANEL = new MenuPanel();

    private MenuPanel() {
        super(Name.MENU_PANEL);
        add(new MEButton(NEW_ITEM_BUTTON, "Új item", this::newItemAction));//todo english
        add(new MEButton(NEW_TAG_BUTTON, "Új tag", this::newTagAction));//todo english

        setVisible(true);
    }

    private void newItemAction(ActionEvent actionEvent) {
        setVisible(false);
        NewItemPanel.NEW_ITEM_PANEL.setVisible(true);
    }

    private void newTagAction(ActionEvent event) {
        setVisible(false);
        NewTagPanel.NEW_TAG_PANEL.setVisible(true);
    }

}
