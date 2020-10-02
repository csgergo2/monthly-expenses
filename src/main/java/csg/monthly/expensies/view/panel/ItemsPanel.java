package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.util.Name.ITEMS_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.ITEMS_CALCULATE_MONTH_BUTTON;
import static csg.monthly.expensies.view.util.Name.ITEMS_INCOMES_TABLE;
import static csg.monthly.expensies.view.util.Name.ITEMS_MONTH_SELECTOR;
import static csg.monthly.expensies.view.util.Name.ITEMS_OUTGOINGS_TABLE;
import static csg.monthly.expensies.view.util.Name.ITEMS_SUM_INCOMES;
import static csg.monthly.expensies.view.util.Name.ITEMS_SUM_OUTGOINGS;
import static csg.monthly.expensies.view.util.Name.ITEMS_YEAR_SELECTOR;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.date.Month;
import csg.monthly.expensies.domain.repository.ItemRepository;
import csg.monthly.expensies.domain.repository.TagRepository;
import csg.monthly.expensies.view.panel.items.ItemsTablePanel;
import csg.monthly.expensies.view.panel.items.TableItem;
import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGComboBox;
import csg.swing.CsGLabel;
import csg.swing.CsGPanel;

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

    private ItemsPanel() {
        super(Name.ITEMS_PANEL, MELayout.LAYOUT);

        add(yearSelector);
        add(monthSelector);

        add(new CsGButton(ITEMS_BACK_BUTTON, "Vissza", this::backToMenuPanel));//todo english
        add(new CsGButton(ITEMS_CALCULATE_MONTH_BUTTON, "Mutasd", this::calculateMonth));//todo english

        add(outgoingsSum);
        add(incomesSum);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            final ItemRepository itemRepository = Application.getBean(ItemRepository.class);
            yearSelector.reset(itemRepository.findAllYear());
        }
        super.setVisible(visible);
    }

    private void calculateMonth(ActionEvent event) {
        final ItemRepository itemRepository = Application.getBean(ItemRepository.class);
        final TagRepository tagRepository = Application.getBean(TagRepository.class);
        List<Item> items = itemRepository.findAllByYearAndMonth((int) yearSelector.getSelectedItem(), (Month) monthSelector.getSelectedItem());
        final List<Tag> tags = tagRepository.findAll();

        calculateOutgoings(items, tags);
        calculateIncomes(items, tags);

        doLayout();
        setVisible(true);
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
        add(tableOfIncomes);
    }

    private void backToMenuPanel(ActionEvent event) {
        setVisible(false);
        MenuPanel.MENU_PANEL.setVisible(true);
    }

}
