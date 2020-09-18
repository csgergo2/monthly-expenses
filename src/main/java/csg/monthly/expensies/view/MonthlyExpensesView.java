package csg.monthly.expensies.view;

import csg.monthly.expensies.view.panel.ItemsPanel;
import csg.monthly.expensies.view.panel.MenuPanel;
import csg.monthly.expensies.view.panel.NewItemPanel;
import csg.monthly.expensies.view.panel.NewTagPanel;
import csg.swing.CsGFrameWindow;

public class MonthlyExpensesView extends CsGFrameWindow {
    private static final String TITLE = "Monthly Expenses";//todo english

    public MonthlyExpensesView() {
        super(TITLE, MenuPanel.MENU_PANEL, NewItemPanel.NEW_ITEM_PANEL, NewTagPanel.NEW_TAG_PANEL, ItemsPanel.ITEMS_PANEL);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
