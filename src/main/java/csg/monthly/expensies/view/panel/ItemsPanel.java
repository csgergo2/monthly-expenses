package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.util.Name.ITEMS_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.ITEMS_CALCULATE_TABLE_BUTTON;
import static csg.monthly.expensies.view.util.Name.ITEMS_MONTH_SELECTOR;
import static csg.monthly.expensies.view.util.Name.ITEMS_TABLE;
import static csg.monthly.expensies.view.util.Name.ITEMS_YEAR_SELECTOR;

import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.date.Month;
import csg.monthly.expensies.domain.repository.ItemRepository;
import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGComboBox;
import csg.swing.CsGHtmlBuilder;
import csg.swing.CsGPanel;
import csg.swing.CsGScrollableLabel;

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
    private CsGScrollableLabel table = new CsGScrollableLabel(ITEMS_TABLE, "");

    private ItemsPanel() {
        super(Name.ITEMS_PANEL, MELayout.LAYOUT);

        add(yearSelector);
        add(monthSelector);

        add(new CsGButton(ITEMS_BACK_BUTTON, "Vissza", this::backToMenuPanel));//todo english
        add(new CsGButton(ITEMS_CALCULATE_TABLE_BUTTON, "Mutasd", this::calculateItemsTable));//todo english

        add(table);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            final ItemRepository itemRepository = Application.getBean(ItemRepository.class);
            yearSelector.reset(itemRepository.findAllYear());
            calculateItemsTable(null);
        }
        super.setVisible(visible);
    }

    private void calculateItemsTable(ActionEvent event) {
        ItemRepository itemRepository = Application.getBean(ItemRepository.class);
        List<Item> items = itemRepository.findAllByYearAndMonth((int) yearSelector.getSelectedItem(), (Month) monthSelector.getSelectedItem());
        String[][] rows = new String[items.size()][4];
        for (int i = 0; i < items.size(); i++) {
            rows[i][0] = items.get(i).getDate().toLocalDate().format(DATE_TIME_FORMATTER);
            rows[i][1] = items.get(i).getName();
            rows[i][2] = Integer.toString(items.get(i).getAmount());
            rows[i][3] = items.get(i).getTag().getName();
        }
        table.setText(CsGHtmlBuilder.createHtmlTable(TABLE_HEADERS, rows));
    }

    private void backToMenuPanel(ActionEvent event) {
        setVisible(false);
        MenuPanel.MENU_PANEL.setVisible(true);
    }

}
