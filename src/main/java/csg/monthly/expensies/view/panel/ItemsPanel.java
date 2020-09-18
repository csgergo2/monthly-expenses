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
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.date.Month;
import csg.monthly.expensies.domain.repository.ItemRepository;
import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGComboBox;
import csg.swing.CsGLabel;
import csg.swing.CsGPanel;
import csg.swing.CsGScrollableLabel;
import csg.swing.html.CsGHtmlBuilder;
import csg.swing.html.CsGHtmlHeadBuilder;
import csg.swing.html.CsGHtmlTableBodyBuilder;

public class ItemsPanel extends CsGPanel {
    public static final ItemsPanel ITEMS_PANEL = new ItemsPanel();
    private static final String[] TABLE_HEADERS = new String[]{"Dátum", "Item", "Összeg", "Tag"};//todo english
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy. MM. dd.");

    private static CsGComboBox<Month> createMonthSelector() {
        CsGComboBox<Month> monthSelector = new CsGComboBox<>(ITEMS_MONTH_SELECTOR);
        monthSelector.reset(Arrays.asList(Month.values()));
        monthSelector.setSelectedItem(Month.getCurrent());
        return monthSelector;
    }

    private CsGComboBox<Integer> yearSelector = new CsGComboBox<>(ITEMS_YEAR_SELECTOR);
    private CsGComboBox<Month> monthSelector = createMonthSelector();
    private CsGScrollableLabel tableOfOutgoings = new CsGScrollableLabel(ITEMS_OUTGOINGS_TABLE, "");
    private CsGScrollableLabel tableOfIncomes = new CsGScrollableLabel(ITEMS_INCOMES_TABLE, "");
    private CsGLabel sumOutgoings = new CsGLabel(ITEMS_SUM_OUTGOINGS, "");
    private CsGLabel sumIncomes = new CsGLabel(ITEMS_SUM_INCOMES, "");

    private ItemsPanel() {
        super(Name.ITEMS_PANEL, MELayout.LAYOUT);

        add(yearSelector);
        add(monthSelector);

        add(new CsGButton(ITEMS_BACK_BUTTON, "Vissza", this::backToMenuPanel));//todo english
        add(new CsGButton(ITEMS_CALCULATE_MONTH_BUTTON, "Mutasd", this::calculateMonth));//todo english

        add(tableOfOutgoings);
        add(tableOfIncomes);
        add(sumOutgoings);
        add(sumIncomes);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            final ItemRepository itemRepository = Application.getBean(ItemRepository.class);
            yearSelector.reset(itemRepository.findAllYear());
            calculateMonth(null);
        }
        super.setVisible(visible);
    }

    private void calculateMonth(ActionEvent event) {
        ItemRepository itemRepository = Application.getBean(ItemRepository.class);
        List<Item> items = itemRepository.findAllByYearAndMonth((int) yearSelector.getSelectedItem(), (Month) monthSelector.getSelectedItem());
        setTable(items.stream().filter(item -> !item.isIncome()).collect(Collectors.toList()), tableOfOutgoings, sumOutgoings,
                "Kiadások összesen: ");//todo english
        setTable(items.stream().filter(Item::isIncome).collect(Collectors.toList()), tableOfIncomes, sumIncomes,
                "Bevételek összesen: ");//todo english
    }

    private void setTable(List<Item> items, CsGScrollableLabel itemsTable, CsGLabel sum, String sumTextStart) {
        String[][] rows = new String[items.size()][4];
        int sumAmount = 0;
        for (int i = 0; i < items.size(); i++) {
            rows[i][0] = items.get(i).getDate().toLocalDate().format(DATE_TIME_FORMATTER);
            rows[i][1] = items.get(i).getName();
            rows[i][2] = Integer.toString(items.get(i).getAmount());
            rows[i][3] = items.get(i).getTag().getName();
            sumAmount += items.get(i).getAmount();
        }
        itemsTable.setText(buildHtmlTable(rows));
        itemsTable.getVerticalScrollBar().setValue(itemsTable.getVerticalScrollBar().getMaximum());
        sum.setText(sumTextStart + sumAmount);
    }

    private String buildHtmlTable(String[][] rows) {
        final CsGHtmlHeadBuilder headerBuilder = new CsGHtmlHeadBuilder();
        headerBuilder.addStyle("table", "border", "1px solid black");
        headerBuilder.addStyle("tr", "border-bottom", "1px solid black");
        headerBuilder.addStyle("th", "padding", "5px");
        headerBuilder.addStyle("td", "padding", "5px");
        headerBuilder.addStyle("td", "border-right", "1px solid black");
        final CsGHtmlTableBodyBuilder tableBuilder = new CsGHtmlTableBodyBuilder(TABLE_HEADERS, rows);
        return new CsGHtmlBuilder(headerBuilder, tableBuilder).build();
    }

    private void backToMenuPanel(ActionEvent event) {
        setVisible(false);
        MenuPanel.MENU_PANEL.setVisible(true);
    }

}
