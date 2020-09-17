package csg.monthly.expensies.view.util;

import static csg.monthly.expensies.view.MonthlyExpensesView.MEV_WINDOW_HEIGHT;
import static csg.monthly.expensies.view.MonthlyExpensesView.MEV_WINDOW_WIDTH;

import java.awt.*;

public enum Name {
    //    MONTHLY_EXPENSES_VIEW,
    MENU_PANEL(0, 0, MEV_WINDOW_WIDTH, MEV_WINDOW_HEIGHT),
    NEW_ITEM_BUTTON(10, 10, 100, 30),

    NEW_ITEM_PANEL(0, 0, MEV_WINDOW_WIDTH, MEV_WINDOW_HEIGHT),
    ITEM_NAME(10, 45, 200, 25),
    ITEM_NAME_LABEL(10, 10, 200, 25),
    ITEM_TAG_LIST(220, 45, 100, 25),
    ITEM_TAG_LIST_LABEL(220, 10, 100, 25),
    ITEM_AMOUNT(330, 45, 100, 25),
    ITEM_AMOUNT_LABEL(330, 10, 100, 25),
    ITEM_DATE(440, 44, 200, 26),
    ITEM_DATE_LABEL(440, 10, 200, 25),
    ITEM_NEW_MONTH_FLAG(10, 80, 25, 25),
    ITEM_NEW_MONTH_FLAG_LABEL(45, 80, 100, 25),
    ITEM_INCOME_FLAG(155, 80, 25, 25),
    ITEM_INCOME_FLAG_LABEL(190, 80, 100, 25),
    ITEM_YEAR(45, 115, 50, 25),
    ITEM_YEAR_LABEL(10, 115, 25, 25),
    ITEM_MONTH(155, 115, 100, 25),
    ITEM_MONTH_LABEL(105, 115, 40, 25),
    ITEM_SAVE_BUTTON(10, 150, 100, 50),
    ITEM_BACK_BUTTON(120, 150, 100, 50),

    ;

    private final int x;
    private final int y;
    private final int width;
    private final int height;

    Name(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

}
