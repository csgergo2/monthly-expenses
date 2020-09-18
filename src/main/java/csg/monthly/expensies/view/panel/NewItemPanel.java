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
import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGCheckBox;
import csg.swing.CsGComboBox;
import csg.swing.CsGDatePicker;
import csg.swing.CsGLabel;
import csg.swing.CsGPanel;
import csg.swing.CsGTextField;

public class NewItemPanel extends CsGPanel {
    public static final NewItemPanel NEW_ITEM_PANEL = new NewItemPanel();

    private static CsGTextField getYearTextField() {
        CsGTextField itemYear = new CsGTextField(ITEM_YEAR, true);
        itemYear.setText(Integer.toString(LocalDate.now().getYear()));
        return itemYear;
    }

    private static CsGComboBox<Month> getMonthComboBox() {
        CsGComboBox<Month> itemMonth = new CsGComboBox<>(ITEM_MONTH);
        Arrays.stream(Month.values()).forEach(itemMonth::addItem);
        itemMonth.setSelectedItem(Month.getCurrent());
        return itemMonth;
    }

    private CsGTextField itemName = new CsGTextField(ITEM_NAME);
    private CsGComboBox<Tag> itemTags = new CsGComboBox<>(ITEM_TAG_LIST);
    private CsGTextField itemAmount = new CsGTextField(ITEM_AMOUNT, true);
    private CsGDatePicker itemDate = new CsGDatePicker(ITEM_DATE);

    private CsGCheckBox itemNewMonth = new CsGCheckBox(ITEM_NEW_MONTH_FLAG);
    private CsGCheckBox itemIncome = new CsGCheckBox(ITEM_INCOME_FLAG);
    private CsGTextField itemYear = getYearTextField();
    private CsGComboBox<Month> itemMonth = getMonthComboBox();

    private NewItemPanel() {
        super(Name.NEW_ITEM_PANEL, MELayout.LAYOUT);

        add(itemName);
        add(new CsGLabel(ITEM_NAME_LABEL, "Item:"));//todo english
        add(itemTags);
        add(new CsGLabel(ITEM_TAG_LIST_LABEL, "Tag-ek:"));//todo english
        add(itemAmount);
        add(new CsGLabel(ITEM_AMOUNT_LABEL, "Összeg:"));//todo english
        add(itemDate);
        add(new CsGLabel(ITEM_DATE_LABEL, "Dátum:"));//todo english
        add(itemNewMonth);
        add(new CsGLabel(ITEM_NEW_MONTH_FLAG_LABEL, "Új hónap"));//todo english
        add(itemIncome);
        add(new CsGLabel(ITEM_INCOME_FLAG_LABEL, "Bevétel"));//todo english
        add(itemYear);
        add(new CsGLabel(ITEM_YEAR_LABEL, "Év:"));//todo english
        add(itemMonth);
        add(new CsGLabel(ITEM_MONTH_LABEL, "Hónap:"));//todo english
        add(new CsGButton(ITEM_SAVE_BUTTON, "Mentés", this::saveItem));//todo english
        add(new CsGButton(ITEM_BACK_BUTTON, "Vissza", this::backToMenuPanel));//todo english
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
        final ItemRepository itemRepository = Application.getBean(ItemRepository.class);
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
            final Iterable<Tag> tags = Application.getBean(TagRepository.class).findAll();
            itemTags.removeAllItems();
            tags.forEach(itemTags::addItem);
        }
        if (itemName != null && itemDate != null) {
            itemName.setText(itemDate.getDateAsString());
        }
        super.setVisible(visible);
    }

}
