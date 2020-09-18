package csg.monthly.expensies.view.util;

import java.awt.*;

import csg.swing.CsGLayout;

public final class MELayout implements CsGLayout {

    public static final MELayout LAYOUT = new MELayout();

    private MELayout() {
    }

    @Override
    public Rectangle getRectangleForComponentName(final String name) {
        return Name.valueOf(name).getRectangle();
    }
}
