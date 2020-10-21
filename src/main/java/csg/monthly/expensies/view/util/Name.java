package csg.monthly.expensies.view.util;

import java.awt.Rectangle;

public enum Name {
    ITEMS_BACK_BUTTON(1372, 698, 200, 30),
    ITEMS_PANEL(10, 10, 1140, 750),
    NEW_ITEM_PANEL(1162, 10, 530, 210),
    TAG_NEW_TAG_PANEL(1162, 232, 530, 458);

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
