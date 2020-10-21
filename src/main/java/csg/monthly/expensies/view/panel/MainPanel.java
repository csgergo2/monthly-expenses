package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.panel.ItemsPanel.ITEMS_PANEL;
import static csg.monthly.expensies.view.util.Name.TAG_NEW_TAG_PANEL;

import csg.monthly.expensies.view.panel.tag.NewTagPanel;
import csg.monthly.expensies.view.util.MELayout;
import csg.swing.CsGPanel;

public class MainPanel extends CsGPanel {
    public static final MainPanel MAIN_PANEL = new MainPanel();

    private NewItemPanel newItemPanel = new NewItemPanel(() -> ITEMS_PANEL.setVisible(true));
    private NewTagPanel newTagPanel = new NewTagPanel(TAG_NEW_TAG_PANEL, this::refresh);

    private MainPanel() {
        super(MenuPanel.Name.MAIN_PANEL, MELayout.LAYOUT);

        add(ITEMS_PANEL);
        add(newItemPanel);
        add(newTagPanel);
    }

    private void refresh() {
        ITEMS_PANEL.setVisible(true);
        newItemPanel.setVisible(true);
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
