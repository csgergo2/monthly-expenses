package csg.monthly.expensies.view.util;

import static csg.swing.CsGFrameWindow.PREFERRED_HEIGHT;
import static csg.swing.CsGFrameWindow.PREFERRED_WIDTH;

import java.awt.Rectangle;

public enum Name {
    MENU_PANEL(0, 0, PREFERRED_WIDTH, PREFERRED_HEIGHT),
    MENU_PANEL_START_BUTTON(0, 0, 100, 25),

    MAIN_PANEL(0, 0, PREFERRED_WIDTH, PREFERRED_HEIGHT),
    MAIN_PANEL_REFRESH_BUTTON(1152, 688, 200, 30),

    ITEMS_PANEL(2, 2, 1140, 680),
    NEW_ITEM_PANEL(1152, 2, 530, 210),
    TAG_PANEL(1152, 222, 530, 458),

    ITEM_NAME(10, 45, 200, 25),
    ITEM_NAME_LABEL(10, 10, 200, 25),
    ITEM_TAG_LIST(220, 45, 100, 25),
    ITEM_TAG_LIST_LABEL(220, 10, 100, 25),
    ITEM_AMOUNT(330, 45, 100, 25),
    ITEM_AMOUNT_LABEL(330, 10, 100, 25),
    ITEM_DATE(440, 44, 80, 26),
    ITEM_DATE_LABEL(440, 10, 80, 25),
    ITEM_NEW_MONTH_FLAG(10, 80, 25, 25),
    ITEM_NEW_MONTH_FLAG_LABEL(45, 80, 100, 25),
    ITEM_INCOME_FLAG(155, 80, 25, 25),
    ITEM_INCOME_FLAG_LABEL(190, 80, 100, 25),
    ITEM_YEAR(45, 115, 50, 25),
    ITEM_YEAR_LABEL(10, 115, 25, 25),
    ITEM_MONTH(155, 115, 100, 25),
    ITEM_MONTH_LABEL(105, 115, 40, 25),
    ITEM_SAVE_BUTTON(10, 150, 100, 50),

    TAG_NAME_LABEL(10, 10, 200, 25),
    TAG_NAME(10, 45, 200, 25),
    TAG_PRIO_LABEL(220, 10, 200, 25),
    TAG_PRIO(220, 45, 35, 25),
    TAG_SAVE_BUTTON(10, 80, 100, 50),
    TAG_LIST_LABEL(10, 140, 200, 308),

    ITEMS_YEAR_SELECTOR(10, 10, 100, 25),
    ITEMS_MONTH_SELECTOR(120, 10, 100, 25),
    ITEMS_CALCULATE_MONTH_BUTTON(230, 10, 100, 25),
    ITEMS_OUTGOINGS_TABLE(10, 45, 555, 600),
    ITEMS_INCOMES_TABLE(575, 45, 555, 600),
    ITEMS_SUM_OUTGOINGS(10, 655, 500, 25),
    ITEMS_SUM_INCOMES(575, 655, 500, 25);

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

    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

}
