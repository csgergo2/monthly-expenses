package csg.monthly.expensies.view.panel;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Arrays;

import javax.swing.BorderFactory;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.date.Month;
import csg.monthly.expensies.domain.service.ItemService;
import csg.monthly.expensies.domain.service.TagService;
import csg.monthly.expensies.exception.MonthlyExpensesException;
import csg.monthly.expensies.view.util.DateParser;
import csg.swing.CsGButton;
import csg.swing.CsGCheckBox;
import csg.swing.CsGComboBox;
import csg.swing.CsGLabel;
import csg.swing.CsGLayout;
import csg.swing.CsGPanel;
import csg.swing.CsGTextField;

public class NewItemPanel extends CsGPanel {

    private static CsGTextField getYearTextField() {
        CsGTextField itemYear = new CsGTextField(Name.ITEM_YEAR, true);
        itemYear.setText(Integer.toString(LocalDate.now().getYear()));
        return itemYear;
    }

    private static CsGComboBox<Month> getMonthComboBox() {
        CsGComboBox<Month> itemMonth = new CsGComboBox<>(Name.ITEM_MONTH);
        Arrays.stream(Month.values()).forEach(itemMonth::addItem);
        itemMonth.setSelectedItem(Month.getCurrent());
        return itemMonth;
    }

    private final Runnable refreshAction;

    private CsGTextField itemName = new CsGTextField(Name.ITEM_NAME);
    private CsGComboBox<Tag> itemTags = new CsGComboBox<>(Name.ITEM_TAG_LIST);
    private CsGTextField itemAmount = new CsGTextField(Name.ITEM_AMOUNT, true);
    private CsGTextField itemDate = new CsGTextField(Name.ITEM_DATE);

    private CsGCheckBox itemNewMonth = new CsGCheckBox(Name.ITEM_NEW_MONTH_FLAG);
    private CsGCheckBox itemIncome = new CsGCheckBox(Name.ITEM_INCOME_FLAG);
    private CsGTextField itemYear = getYearTextField();
    private CsGComboBox<Month> itemMonth = getMonthComboBox();

    public NewItemPanel(Runnable refreshAction) {
        super(csg.monthly.expensies.view.util.Name.NEW_ITEM_PANEL, (CsGLayout) name -> Name.valueOf(name).getRectangle());
        this.refreshAction = refreshAction;
        setBorder(BorderFactory.createLineBorder(Color.black));

        add(itemName);
        add(new CsGLabel(Name.ITEM_NAME_LABEL, "Item:"));//todo english
        add(itemTags);
        add(new CsGLabel(Name.ITEM_TAG_LIST_LABEL, "Tag-ek:"));//todo english
        add(itemAmount);
        add(new CsGLabel(Name.ITEM_AMOUNT_LABEL, "Összeg:"));//todo english
        add(itemDate);
        add(new CsGLabel(Name.ITEM_DATE_LABEL, "Dátum:"));//todo english
        add(itemNewMonth);
        add(new CsGLabel(Name.ITEM_NEW_MONTH_FLAG_LABEL, "Hó vége"));//todo english
        add(itemIncome);
        add(new CsGLabel(Name.ITEM_INCOME_FLAG_LABEL, "Bevétel"));//todo english
        add(itemYear);
        add(new CsGLabel(Name.ITEM_YEAR_LABEL, "Év:"));//todo english
        add(itemMonth);
        add(new CsGLabel(Name.ITEM_MONTH_LABEL, "Hónap:"));//todo english
        add(new CsGButton(Name.ITEM_SAVE_BUTTON, "Mentés", this::saveItem));//todo english
    }

    private void saveItem(ActionEvent event) {
        if (itemName.getText().isEmpty() || itemAmount.getText().isEmpty() || itemYear.getText().isEmpty()) {
            throw new MonthlyExpensesException(
                    "Empty field at new item; name: " + itemName.getText() + "; amount: " + itemAmount.getText() + "; year: " + itemYear.getText());
        }
        final Item item = new Item.ItemBuilder().setName(itemName.getText()).setTag((Tag) itemTags.getSelectedItem())
                                                .setAmount(Integer.valueOf(itemAmount.getText())).setIncome(itemIncome.isSelected())
                                                .setEndMonth(itemNewMonth.isSelected()).setDate(DateParser.stringToDate(itemDate.getText()))
                                                .setYear(Integer.valueOf(itemYear.getText())).setMonth((Month) itemMonth.getSelectedItem()).build();
        final ItemService itemService = Application.getBean(ItemService.class);
        itemService.save(item);
        itemName.setText("");
        itemAmount.setText("");
        itemNewMonth.setSelected(false);
        itemIncome.setSelected(false);
        refreshAction.run();
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            if (itemDate.getText().isEmpty()) {
                itemDate.setText(LocalDate.now().toString());
            }
            //tags
            final Iterable<Tag> tags = Application.getBean(TagService.class).findAllOrderedByFrequency();
            itemTags.removeAllItems();
            tags.forEach(itemTags::addItem);
        }
        super.setVisible(visible);
    }

    private enum Name {
        ITEM_NAME(10, 45, 200, 25),
        ITEM_NAME_LABEL(10, 10, 200, 25),
        ITEM_TAG_LIST(220, 45, 100, 25),
        ITEM_TAG_LIST_LABEL(220, 10, 100, 25),
        ITEM_AMOUNT(330, 45, 100, 25),
        ITEM_AMOUNT_LABEL(330, 10, 100, 25),
        ITEM_DATE(440, 44, 80, 26),
        ITEM_DATE_LABEL(440, 10, 80, 25),
        ITEM_NEW_MONTH_FLAG(10, 80, 25, 25),
        ITEM_NEW_MONTH_FLAG_LABEL(45, 80, 100, 25),
        ITEM_INCOME_FLAG(155, 80, 25, 25),
        ITEM_INCOME_FLAG_LABEL(190, 80, 100, 25),
        ITEM_YEAR(45, 115, 50, 25),
        ITEM_YEAR_LABEL(10, 115, 25, 25),
        ITEM_MONTH(155, 115, 100, 25),
        ITEM_MONTH_LABEL(105, 115, 40, 25),
        ITEM_SAVE_BUTTON(10, 150, 100, 50);

        private final int x;
        private final int y;
        private final int width;
        private final int height;

        Name(final int x, final int y, final int width, final int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public Rectangle getRectangle() {
            return new Rectangle(x, y, width, height);
        }
    }
}
