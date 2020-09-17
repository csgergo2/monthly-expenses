package csg.monthly.expensies.view;

import javax.swing.*;

import csg.monthly.expensies.view.panel.MenuPanel;
import csg.monthly.expensies.view.panel.NewItemPanel;

public class MonthlyExpensesView extends JFrame {
    private static final String TITLE = "Monthly Expenses";//todo english
    public static final int MEV_WINDOW_WIDTH = 1800;
    public static final int MEV_WINDOW_HEIGHT = 800;

    public MonthlyExpensesView() {
        this.setTitle(TITLE);
        this.setSize(MEV_WINDOW_WIDTH, MEV_WINDOW_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(MenuPanel.MENU_PANEL);
        add(NewItemPanel.NEW_ITEM_PANEL);

        this.setVisible(true);
    }
}
