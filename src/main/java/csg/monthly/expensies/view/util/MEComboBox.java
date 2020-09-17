package csg.monthly.expensies.view.util;

import java.util.Arrays;

import javax.swing.*;

public class MEComboBox<E> extends JComboBox<E> {

    public MEComboBox(Name name) {
        super();
        setName(name.name());
    }

    public MEComboBox(Name name, E[] items) {
        super(items);
        setName(name.name());
    }
}
