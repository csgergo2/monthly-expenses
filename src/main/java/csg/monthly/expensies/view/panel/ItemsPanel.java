package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.util.Name.ITEMS_CALCULATE_MONTH_BUTTON;
import static csg.monthly.expensies.view.util.Name.ITEMS_INCOMES_TABLE;
import static csg.monthly.expensies.view.util.Name.ITEMS_MONTH_COMMENT;
import static csg.monthly.expensies.view.util.Name.ITEMS_MONTH_SELECTOR;
import static csg.monthly.expensies.view.util.Name.ITEMS_OUTGOINGS_TABLE;
import static csg.monthly.expensies.view.util.Name.ITEMS_SAVE_COMMENT_BUTTON;
import static csg.monthly.expensies.view.util.Name.ITEMS_SUM_INCOMES;
import static csg.monthly.expensies.view.util.Name.ITEMS_SUM_OUTGOINGS;
import static csg.monthly.expensies.view.util.Name.ITEMS_YEAR_SELECTOR;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.MonthComment;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.date.Month;
import csg.monthly.expensies.domain.service.ItemService;
import csg.monthly.expensies.domain.service.TagService;
import csg.monthly.expensies.view.panel.items.ItemsTablePanel;
import csg.monthly.expensies.view.panel.items.TableItem;
import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGComboBox;
import csg.swing.CsGLabel;
import csg.swing.CsGPanel;
import csg.swing.CsGScrollableTextArea;

public class ItemsPanel extends CsGPanel {
    public static final ItemsPanel ITEMS_PANEL = new ItemsPanel();

    private static CsGComboBox<Month> createMonthSelector() {
        CsGComboBox<Month> monthSelector = new CsGComboBox<>(ITEMS_MONTH_SELECTOR);
        monthSelector.reset(Arrays.asList(Month.values()));
        monthSelector.setSelectedItem(Month.getCurrent());
        return monthSelector;
    }

    private CsGComboBox<Integer> yearSelector = new CsGComboBox<>(ITEMS_YEAR_SELECTOR);
    private CsGComboBox<Month> monthSelector = createMonthSelector();

    private ItemsTablePanel tableOfOutgoings = new ItemsTablePanel(ITEMS_OUTGOINGS_TABLE);
    private ItemsTablePanel tableOfIncomes = new ItemsTablePanel(ITEMS_INCOMES_TABLE);

    private CsGLabel outgoingsSum = new CsGLabel(ITEMS_SUM_OUTGOINGS, "");
    private CsGLabel incomesSum = new CsGLabel(ITEMS_SUM_INCOMES, "");
    private CsGScrollableTextArea comment = new CsGScrollableTextArea(ITEMS_MONTH_COMMENT);

    private ItemsPanel() {
        super(Name.ITEMS_PANEL, MELayout.LAYOUT);
        setBorder(BorderFactory.createLineBorder(Color.black));

        add(yearSelector);
        add(monthSelector);

        add(new CsGButton(ITEMS_CALCULATE_MONTH_BUTTON, "Mutasd", event -> setVisible(true)));//todo english
        add(new CsGButton(ITEMS_SAVE_COMMENT_BUTTON, "Megjegyzés mentése", event -> saveComment()));//todo english

        add(outgoingsSum);
        add(incomesSum);
        add(comment);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            final ItemService itemService = Application.getBean(ItemService.class);
            yearSelector.reset(itemService.findAllYear());
            calculateMonth();
        }
        super.setVisible(visible);
    }

    private void calculateMonth() {
        final ItemService itemService = Application.getBean(ItemService.class);
        final TagService tagService = Application.getBean(TagService.class);
        List<Item> items = itemService.findAllByYearAndMonth((int) yearSelector.getSelectedItem(), (Month) monthSelector.getSelectedItem());
        final List<Tag> tags = tagService.findAllOrderedByFrequency();

        calculateOutgoings(items, tags);
        calculateIncomes(items, tags);
        comment.setText(
                itemService.getComment((Month) monthSelector.getSelectedItem()).orElse(new MonthComment((Month) monthSelector.getSelectedItem(), ""))
                           .getComment());
    }

    private void calculateOutgoings(List<Item> items, List<Tag> tags) {
        final List<Item> outgoings = items.stream().filter(item -> !item.isIncome()).sorted().collect(Collectors.toList());
        if (tableOfOutgoings != null) {
            tableOfOutgoings.setVisible(false);
            tableOfOutgoings.setEnabled(false);
            remove(tableOfOutgoings);
        }
        tableOfOutgoings = new ItemsTablePanel(ITEMS_OUTGOINGS_TABLE);
        int sum = 0;
        for (Item item : outgoings) {
            tableOfOutgoings.add(new TableItem(item, tags));
            sum += item.getAmount();
        }
        outgoingsSum.setText("Összesen: " + Integer.toString(sum));//todo english
        tableOfOutgoings.setScrollBarToBottom();
        add(tableOfOutgoings);
    }

    private void calculateIncomes(List<Item> items, List<Tag> tags) {
        final List<Item> incomes = items.stream().filter(Item::isIncome).sorted().collect(Collectors.toList());
        if (tableOfIncomes != null) {
            tableOfIncomes.setVisible(false);
            tableOfIncomes.setEnabled(false);
            remove(tableOfIncomes);
        }
        tableOfIncomes = new ItemsTablePanel(ITEMS_INCOMES_TABLE);
        int sum = 0;
        for (Item item : incomes) {
            tableOfIncomes.add(new TableItem(item, tags));
            sum += item.getAmount();
        }
        incomesSum.setText("Összesen: " + Integer.toString(sum));//todo english
        tableOfIncomes.setScrollBarToBottom();
        add(tableOfIncomes);
    }

    private void saveComment() {
        Application.getBean(ItemService.class).saveComment((Month) monthSelector.getSelectedItem(), comment.getText());
    }
}
