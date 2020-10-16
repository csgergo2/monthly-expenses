package csg.monthly.expensies.view.util;

import java.awt.Color;

public class ColorParser {

    public static boolean isTextColorParsable(String color) {
        if (color == null || color.isEmpty()) {
            return false;
        }
        try {
            Color.decode(color);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
