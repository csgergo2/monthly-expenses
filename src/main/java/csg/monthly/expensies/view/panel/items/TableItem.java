package csg.monthly.expensies.view.panel.items;


import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.repository.ItemRepository;
import csg.swing.CsGButton;
import csg.swing.CsGComboBox;
import csg.swing.CsGLayout;
import csg.swing.CsGPanel;
import csg.swing.CsGTextField;

public class TableItem extends CsGPanel {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final long HOURS_12 = 12L * 60L * 60L * 1000L;
    private Item item;

    private CsGTextField dateField;
    private CsGTextField nameField;
    private CsGTextField amountField;
    private CsGComboBox<Tag> tagBox;

    private ItemRepository itemRepository;

    public TableItem(Item item, List<Tag> tags) {
        super(TableItemName.PANEL, new TableItemLayout());
        this.item = item;
        dateField = new CsGTextField(TableItemName.DATE);
        dateField.setText(item.getDate().toString());
        nameField = new CsGTextField(TableItemName.NAME);
        nameField.setText(item.getName());
        amountField = new CsGTextField(TableItemName.AMOUNT, true);
        amountField.setText(Integer.toString(item.getAmount()));
        tagBox = new CsGComboBox<>(TableItemName.TAG, tags.toArray((Tag[]) Array.newInstance(Tag.class, tags.size())));
        tagBox.setSelectedItem(item.getTag());

        add(dateField);
        add(nameField);
        add(amountField);
        add(tagBox);
        add(new CsGButton(TableItemName.SAVE, "Mentés", this::save));//todo english

        itemRepository = Application.getBean(ItemRepository.class);
        setBackground(Color.BLACK);
        setVisible(true);
    }

    private void save(ActionEvent event) {
        item.setDate(convertStringToDate(dateField.getText()));
        item.setName(nameField.getText());
        item.setAmount(Integer.parseInt(amountField.getText()));
        item.setTag((Tag) tagBox.getSelectedItem());
        itemRepository.save(item);
    }

    private Date convertStringToDate(String date) {
        try {
            java.util.Date parsed = SIMPLE_DATE_FORMAT.parse(date);
            return new Date(parsed.getTime() + HOURS_12);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private enum TableItemName {
        PANEL(0, 0, 0, 0),
        DATE(0, 0, 70, 25),
        NAME(71, 0, 200, 25),
        AMOUNT(272, 0, 60, 25),
        TAG(333, 0, 100, 25),
        SAVE(434, 0, 100, 25);
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        TableItemName(final int x, final int y, final int width, final int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        Rectangle getRectangle() {
            return new Rectangle(x, y, width, height);
        }
    }

    private static class TableItemLayout implements CsGLayout {
        @Override
        public Rectangle getRectangleForComponentName(final String name) {
            return TableItemName.valueOf(name).getRectangle();
        }
    }
}
