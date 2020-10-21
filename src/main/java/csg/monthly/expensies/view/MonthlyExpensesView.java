package csg.monthly.expensies.view;

import csg.monthly.expensies.view.panel.MenuPanel;
import csg.swing.CsGFrameWindow;

public class MonthlyExpensesView extends CsGFrameWindow {
    private static final String TITLE = "Monthly Expenses";//todo english

    public MonthlyExpensesView() {
        super(TITLE, 1800, 825, MenuPanel.MENU_PANEL);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
