package csg.monthly.expensies.view;

import csg.monthly.expensies.view.panel.CustomCounterPanel;
import csg.monthly.expensies.view.panel.FilteringPanel;
import csg.monthly.expensies.view.panel.MainPanel;
import csg.monthly.expensies.view.panel.MenuPanel;
import csg.monthly.expensies.view.panel.MonthlySumPanel;
import csg.monthly.expensies.view.panel.tag.TagPanel;
import csg.swing.CsGFrameWindow;

public class MonthlyExpensesView extends CsGFrameWindow {
    private static final String TITLE = "Monthly Expenses";//todo english

    public MonthlyExpensesView() {
        //@formatter:off
        super(TITLE, MenuPanel.MENU_PANEL,
                MainPanel.MAIN_PANEL,
                FilteringPanel.FILTERING_PANEL,
                MonthlySumPanel.MONTHLY_SUM_PANEL,
                TagPanel.TAG_PANEL,
                CustomCounterPanel.CUSTOM_COUNTER_PANEL);
        //@formatter:on
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
