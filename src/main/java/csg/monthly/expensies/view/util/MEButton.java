package csg.monthly.expensies.view.util;

import java.awt.event.ActionListener;

import javax.swing.*;

public class MEButton extends JButton {

    public MEButton(Name name, String text, ActionListener actionListener) {
        setName(name.name());
        setText(text);
        addActionListener(actionListener);
        setVisible(true);
    }
}
