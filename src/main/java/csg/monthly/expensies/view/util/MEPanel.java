package csg.monthly.expensies.view.util;

import static csg.monthly.expensies.view.MonthlyExpensesView.MEV_WINDOW_HEIGHT;
import static csg.monthly.expensies.view.MonthlyExpensesView.MEV_WINDOW_WIDTH;

import java.awt.*;

import javax.swing.*;

public abstract class MEPanel extends JPanel implements MELayout {

    public MEPanel(Name name) {
        this(name, 0, 0, MEV_WINDOW_WIDTH, MEV_WINDOW_HEIGHT, null);
    }

    private MEPanel(Name name, int x, int y, int width, int height, LayoutManager layout) {
        setName(name.name());
        setSize(width, height);
        setLocation(x, y);
        setLayout(layout);
        setBackground(Color.decode("#e3d8b3"));
        setLayout(this);
        super.setVisible(false);
    }
}
