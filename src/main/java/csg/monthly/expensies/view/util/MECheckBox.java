package csg.monthly.expensies.view.util;

import javax.swing.*;

public class MECheckBox extends JCheckBox {
    public MECheckBox(Name name) {
        setName(name.name());
    }
}
