package csg.monthly.expensies.view.util;

import static csg.swing.CsGFrameWindow.PREFERRED_HEIGHT;
import static csg.swing.CsGFrameWindow.PREFERRED_WIDTH;

import java.awt.Rectangle;

public enum Name {
    MENU_PANEL(0, 0, PREFERRED_WIDTH, PREFERRED_HEIGHT),
    MENU_PANEL_START_BUTTON(0, 0, 150, 25),
    MENU_PANEL_FILTERING_PANEL_BUTTON(150, 0, 150, 25),
    MENU_PANEL_MONTHLY_SUM_BUTTON(300, 0, 150, 25),
    MENU_PANEL_TAG_PANEL_BUTTON(450, 0, 150, 25),
    MENU_PANEL_CUSTOM_COUNTER_BUTTON(600, 0, 150, 25),

    FILTERING_PANEL(0, 0, PREFERRED_WIDTH, PREFERRED_HEIGHT),
    FILTERING_ITEMS(10, 10, 555, 500),
    FILTERING_PANEL_BACK_BUTTON(575, 10, 100, 25),
    FILTERING_FILTER_PANEL(575, 45, 555, 500),
    FILTERING_SUMMARIZE_LABEL(10, 520, 500, 25),

    MAIN_PANEL(0, 0, PREFERRED_WIDTH, PREFERRED_HEIGHT),
    ITEMS_BACK_BUTTON(1372, 698, 200, 30),

    ITEMS_PANEL(10, 10, 1140, 750),
    NEW_ITEM_PANEL(1162, 10, 530, 210),
    TAG_NEW_TAG_PANEL(1162, 232, 530, 458),

    MONTHLY_SUM_PANEL(0, 0, PREFERRED_WIDTH, PREFERRED_HEIGHT),
    MONTHLY_SUM_BACK_BUTTON(0, 0, 100, 25),
    MONTHLY_SUM_TAGS_LABEL(0, 25, 150, 485),
    MONTHLY_SUM_MAIN_LABEL(MONTHLY_SUM_TAGS_LABEL.x + MONTHLY_SUM_TAGS_LABEL.width, MONTHLY_SUM_TAGS_LABEL.y, 1000, 500),

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

    ITEMS_YEAR_SELECTOR(10, 10, 100, 25),
    ITEMS_MONTH_SELECTOR(120, 10, 100, 25),
    ITEMS_CALCULATE_MONTH_BUTTON(230, 10, 100, 25),
    ITEMS_OUTGOINGS_TABLE(10, 45, 555, 600),
    ITEMS_INCOMES_TABLE(575, 45, 555, 600),
    ITEMS_SUM_OUTGOINGS(10, 655, 500, 25),
    ITEMS_SUM_INCOMES(575, 655, 500, 25),
    ITEMS_MONTH_COMMENT(10, 690, 900, 50),
    ITEMS_SAVE_COMMENT_BUTTON(920, 690, 200, 50),

    TAG_PANEL(0, 0, PREFERRED_WIDTH, PREFERRED_HEIGHT),
    TAG_PANEL_TAG_SELECTOR(10, 10, 100, 25),
    TAG_PANEL_BACK_BUTTON(265, 10, 100, 25),
    TAG_PANEL_TAG_NAME(10, 45, 100, 25),
    TAG_PANEL_TAG_PRIO(120, 45, 35, 25),
    TAG_PANEL_OVERWRITE_BUTTON(165, 45, 200, 25),
    TAG_PANEL_COMMENT(375, 10, 190, 60),
    TAG_PANEL_SAVE_COMMENT_BUTTON(575, 10, 150, 25),
    TAG_PANEL_ITEMS(10, 80, 555, 600),
    TAG_PANEL_FILTER_PANEL(575, 80, 230, 395),
    TAG_PANEL_NEW_TAG_PANEL(575, 485, 230, 150),
    TAG_PANEL_DELETE_LABEL(10, 700, 500, 25),
    TAG_PANEL_DELETE_BUTTON(10, 735, 100, 25),
    TAG_PANEL_PRIO_GROUP_PANEL(815, 80, 0, 0),

    CUSTOM_COUNTER(0, 0, PREFERRED_WIDTH, PREFERRED_HEIGHT);

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
