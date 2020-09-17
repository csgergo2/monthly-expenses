package csg.monthly.expensies.view.panel;

import java.awt.event.ActionEvent;

import csg.monthly.expensies.view.util.MEButton;
import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.MEPanel;
import csg.monthly.expensies.view.util.Name;

public class MenuPanel extends MEPanel implements MELayout {
    public static final MenuPanel MENU_PANEL = new MenuPanel();

    private MenuPanel() {
        super(Name.MENU_PANEL);
        setLayout(this);
        add(new MEButton(Name.NEW_ITEM_BUTTON, "Új item", this::newItemAction));//todo english

        setVisible(true);
    }

    private void newItemAction(ActionEvent actionEvent) {
        setVisible(false);
        NewItemPanel.NEW_ITEM_PANEL.setVisible(true);
    }

}
