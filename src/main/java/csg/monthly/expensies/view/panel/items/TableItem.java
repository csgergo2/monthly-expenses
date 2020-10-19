package csg.monthly.expensies.view.panel.items;


import java.awt.Color;
import java.awt.Rectangle;
import java.lang.reflect.Array;
import java.util.List;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.CustomCounter;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.service.CustomCounterServcie;
import csg.monthly.expensies.domain.service.ItemService;
import csg.monthly.expensies.domain.service.TagService;
import csg.monthly.expensies.view.util.DateParser;
import csg.swing.CsGButton;
import csg.swing.CsGComboBox;
import csg.swing.CsGLayout;
import csg.swing.CsGPanel;
import csg.swing.CsGTextField;

public class TableItem extends CsGPanel {

    public static TableItem of(Item item) {
        final List<Tag> tags = Application.getBean(TagService.class).findAllOrderedByFrequency();
        final List<CustomCounter> customCounters = Application.getBean(CustomCounterServcie.class).findAll();
        return new TableItem(item, tags, customCounters);
    }

    private Item item;

    private CsGTextField dateField;
    private CsGTextField nameField;
    private CsGTextField amountField;
    private CsGComboBox<Tag> tagBox;
    private CsGComboBox<CustomCounter> customCounterBox;

    private ItemService itemService;
    private CustomCounterServcie customCounterServcie;

    private TableItem(Item item, List<Tag> tags, List<CustomCounter> customCounters) {
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
        customCounterBox = new CsGComboBox<>(TableItemName.CUSTOM_COUNTER,
                customCounters.toArray((CustomCounter[]) Array.newInstance(CustomCounter.class, customCounters.size())));

        add(dateField);
        add(nameField);
        add(amountField);
        add(tagBox);
        add(new CsGButton(TableItemName.SAVE, "Mentés", event -> save()));//todo english
        add(new CsGButton(TableItemName.NOT_IS_INCOME, "NotIsIncome", event -> notIsIncome()));//todo english
        add(new CsGButton(TableItemName.DELETE, "Delete", event -> delete()));//todo english
        add(customCounterBox);
        add(new CsGButton(TableItemName.ADD_CUSTOM_COUNTER, "Add Cus.Count.", event -> addCustomCounter()));//todo english

        itemService = Application.getBean(ItemService.class);
        customCounterServcie = Application.getBean(CustomCounterServcie.class);
        setBackground(Color.BLACK);
        setVisible(true);
    }

    private void save() {
        item.setDate(DateParser.stringToDate(dateField.getText()));
        item.setName(nameField.getText());
        item.setAmount(Integer.parseInt(amountField.getText()));
        item.setTag((Tag) tagBox.getSelectedItem());
        itemService.save(item);
    }

    private void addCustomCounter() {
        final List<Item> itemsByCustomCounter = itemService.findAllByCustomCounter((CustomCounter) customCounterBox.getSelectedItem());
        if (itemsByCustomCounter.contains(item)) {
            return;
        }
        customCounterServcie.saveItemForCustomCounter(item, (CustomCounter) customCounterBox.getSelectedItem());
    }

    private void notIsIncome() {
        item.setIncome(!item.isIncome());
        itemService.save(item);
    }

    private void delete() {
        itemService.deleteItem(item);
    }

    private enum TableItemName {
        PANEL(0, 0, 0, 0),
        DATE(0, 0, 70, 25),
        NAME(71, 0, 200, 25),
        AMOUNT(272, 0, 60, 25),
        TAG(333, 0, 100, 25),
        SAVE(434, 0, 100, 25),
        NOT_IS_INCOME(535, 0, 100, 25),
        DELETE(636, 0, 100, 25),
        CUSTOM_COUNTER(737, 0, 100, 25),
        ADD_CUSTOM_COUNTER(838, 0, 100, 25);
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
