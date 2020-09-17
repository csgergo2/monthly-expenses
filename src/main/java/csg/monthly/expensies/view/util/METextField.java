package csg.monthly.expensies.view.util;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class METextField extends JTextField {

    public METextField(Name name) {
        setName(name.name());
    }

    public METextField(Name name, boolean onlyDigits) {
        this(name);
        if (onlyDigits) {
            ((AbstractDocument) getDocument()).setDocumentFilter(new DigitDocumentFilter());
        }
    }

    private static class DigitDocumentFilter extends DocumentFilter {

        @Override
        public void insertString(final FilterBypass fp, final int offset, final String string, final AttributeSet aset) throws BadLocationException {
            int len = string.length();
            boolean isValidInteger = true;

            for (int i = 0; i < len; i++) {
                if (!Character.isDigit(string.charAt(i))) {
                    isValidInteger = false;
                    break;
                }
            }
            if (isValidInteger) {
                super.insertString(fp, offset, string, aset);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fp, int offset, int length, String string, AttributeSet aset) throws BadLocationException {
            int len = string.length();
            boolean isValidInteger = true;

            for (int i = 0; i < len; i++) {
                if (!Character.isDigit(string.charAt(i))) {
                    isValidInteger = false;
                    break;
                }
            }
            if (isValidInteger) {
                super.replace(fp, offset, length, string, aset);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
