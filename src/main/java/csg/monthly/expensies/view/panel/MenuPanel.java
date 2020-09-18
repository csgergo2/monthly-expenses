package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.util.Name.NEW_ITEM_BUTTON;
import static csg.monthly.expensies.view.util.Name.NEW_TAG_BUTTON;

import java.awt.event.ActionEvent;

import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGPanel;

public class MenuPanel extends CsGPanel {
    public static final MenuPanel MENU_PANEL = new MenuPanel();

    private MenuPanel() {
        super(Name.MENU_PANEL, MELayout.LAYOUT);
        add(new CsGButton(NEW_ITEM_BUTTON, "Új item", this::newItemAction));//todo english
        add(new CsGButton(NEW_TAG_BUTTON, "Új tag", this::newTagAction));//todo english

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
