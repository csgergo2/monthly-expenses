package csg.monthly.expensies.view.util;

import javax.swing.*;

public class MELabel extends JLabel {
    public MELabel(Name name, String text) {
        super(text);
        setName(name.name());
    }
}
