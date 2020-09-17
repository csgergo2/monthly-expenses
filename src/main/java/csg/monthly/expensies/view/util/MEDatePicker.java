package csg.monthly.expensies.view.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class MEDatePicker extends JDatePickerImpl {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy. MM. dd.");

    public MEDatePicker(Name name) {
        super(getJDatePanelImpl(), new DateLabelFormatter());
        setName(name.name());
    }

    public String getDateAsString() {
        final LocalDate date = LocalDate.of(getModel().getYear(), getModel().getMonth(), getModel().getDay());
        return date.format(DATE_TIME_FORMATTER);
    }

    public Date getDate() {
        return Date.valueOf(LocalDate.of(getModel().getYear(), getModel().getMonth(), getModel().getDay()));
    }

    private static JDatePanelImpl getJDatePanelImpl() {
        UtilDateModel model = new UtilDateModel();
        model.setDate(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        // Need this...
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        return new JDatePanelImpl(model, p);
    }

    public static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "yyyy-MM-dd";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }
            return "";
        }

    }
}
