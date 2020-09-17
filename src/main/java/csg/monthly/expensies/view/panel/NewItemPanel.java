package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.util.Name.ITEM_AMOUNT;
import static csg.monthly.expensies.view.util.Name.ITEM_AMOUNT_LABEL;
import static csg.monthly.expensies.view.util.Name.ITEM_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.ITEM_DATE;
import static csg.monthly.expensies.view.util.Name.ITEM_DATE_LABEL;
import static csg.monthly.expensies.view.util.Name.ITEM_INCOME_FLAG;
import static csg.monthly.expensies.view.util.Name.ITEM_INCOME_FLAG_LABEL;
import static csg.monthly.expensies.view.util.Name.ITEM_MONTH;
import static csg.monthly.expensies.view.util.Name.ITEM_MONTH_LABEL;
import static csg.monthly.expensies.view.util.Name.ITEM_NAME;
import static csg.monthly.expensies.view.util.Name.ITEM_NAME_LABEL;
import static csg.monthly.expensies.view.util.Name.ITEM_NEW_MONTH_FLAG;
import static csg.monthly.expensies.view.util.Name.ITEM_NEW_MONTH_FLAG_LABEL;
import static csg.monthly.expensies.view.util.Name.ITEM_SAVE_BUTTON;
import static csg.monthly.expensies.view.util.Name.ITEM_TAG_LIST;
import static csg.monthly.expensies.view.util.Name.ITEM_TAG_LIST_LABEL;
import static csg.monthly.expensies.view.util.Name.ITEM_YEAR;
import static csg.monthly.expensies.view.util.Name.ITEM_YEAR_LABEL;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Arrays;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.date.Month;
import csg.monthly.expensies.domain.repository.ItemRepository;
import csg.monthly.expensies.domain.repository.TagRepository;
import csg.monthly.expensies.exception.MonthlyExpensesException;
import csg.monthly.expensies.view.util.MEButton;
import csg.monthly.expensies.view.util.MECheckBox;
import csg.monthly.expensies.view.util.MEComboBox;
import csg.monthly.expensies.view.util.MEDatePicker;
import csg.monthly.expensies.view.util.MELabel;
import csg.monthly.expensies.view.util.MEPanel;
import csg.monthly.expensies.view.util.METextField;
import csg.monthly.expensies.view.util.Name;

public class NewItemPanel extends MEPanel {
    public static final NewItemPanel NEW_ITEM_PANEL = new NewItemPanel(Name.NEW_ITEM_PANEL);

    private static METextField getYearTextField() {
        METextField itemYear = new METextField(ITEM_YEAR, true);
        itemYear.setText(Integer.toString(LocalDate.now().getYear()));
        return itemYear;
    }

    private static MEComboBox<Month> getMonthComboBox() {
        MEComboBox<Month> itemMonth = new MEComboBox<>(ITEM_MONTH);
        Arrays.stream(Month.values()).forEach(itemMonth::addItem);
        itemMonth.setSelectedItem(Month.getCurrent());
        return itemMonth;
    }

    private METextField itemName = new METextField(ITEM_NAME);
    private MEComboBox<Tag> itemTags = new MEComboBox<>(ITEM_TAG_LIST);
    private METextField itemAmount = new METextField(ITEM_AMOUNT, true);
    private MEDatePicker itemDate = new MEDatePicker(ITEM_DATE);

    private MECheckBox itemNewMonth = new MECheckBox(ITEM_NEW_MONTH_FLAG);
    private MECheckBox itemIncome = new MECheckBox(ITEM_INCOME_FLAG);
    private METextField itemYear = getYearTextField();
    private MEComboBox<Month> itemMonth = getMonthComboBox();

    private NewItemPanel(final Name name) {
        super(name);

        add(itemName);
        add(new MELabel(ITEM_NAME_LABEL, "Item:"));//todo english
        add(itemTags);
        add(new MELabel(ITEM_TAG_LIST_LABEL, "Tag-ek:"));//todo english
        add(itemAmount);
        add(new MELabel(ITEM_AMOUNT_LABEL, "Összeg:"));//todo english
        add(itemDate);
        add(new MELabel(ITEM_DATE_LABEL, "Dátum:"));//todo english
        add(itemNewMonth);
        add(new MELabel(ITEM_NEW_MONTH_FLAG_LABEL, "Új hónap"));//todo english
        add(itemIncome);
        add(new MELabel(ITEM_INCOME_FLAG_LABEL, "Bevétel"));//todo english
        add(itemYear);
        add(new MELabel(ITEM_YEAR_LABEL, "Év:"));//todo english
        add(itemMonth);
        add(new MELabel(ITEM_MONTH_LABEL, "Hónap:"));//todo english
        add(new MEButton(ITEM_SAVE_BUTTON, "Mentés", this::saveItem));//todo english
        add(new MEButton(ITEM_BACK_BUTTON, "Vissza", this::backToMenuPanel));//todo english
    }

    private void saveItem(ActionEvent event) {
        if (itemName.getText().isEmpty() || itemAmount.getText().isEmpty() || itemYear.getText().isEmpty()) {
            throw new MonthlyExpensesException(
                    "Empty field at new item; name: " + itemName.getText() + "; amount: " + itemAmount.getText() + "; year: " + itemYear.getText());
        }
        final Item item =
                new Item(itemName.getText(), (Tag) itemTags.getSelectedItem(), Integer.valueOf(itemAmount.getText()), itemIncome.isSelected(),
                        itemNewMonth.isSelected(), itemDate.getDate(), Integer.valueOf(itemYear.getText()),
                        ((Month) itemMonth.getSelectedItem()).getMonthOfYear());
        final ItemRepository itemRepository = Application.getApplicationContext().getBean(ItemRepository.class);
        itemRepository.save(item);
        itemName.setText("");
        itemAmount.setText("");
        itemNewMonth.setSelected(false);
        itemIncome.setSelected(false);
    }

    private void backToMenuPanel(ActionEvent event) {
        setVisible(false);
        MenuPanel.MENU_PANEL.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            //tags
            final Iterable<Tag> tags = Application.getApplicationContext().getBean(TagRepository.class).findAll();
            itemTags.removeAllItems();
            tags.forEach(itemTags::addItem);
        }
        itemName.setText(itemDate.getDateAsString());
        super.setVisible(visible);
    }

}
