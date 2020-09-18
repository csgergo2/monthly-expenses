package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.util.Name.ITEMS_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.ITEMS_CALCULATE_TABLE_BUTTON;
import static csg.monthly.expensies.view.util.Name.ITEMS_MONTH_SELECTOR;
import static csg.monthly.expensies.view.util.Name.ITEMS_YEAR_SELECTOR;

import java.awt.event.ActionEvent;
import java.util.Arrays;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.date.Month;
import csg.monthly.expensies.domain.repository.ItemRepository;
import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGComboBox;
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

    private ItemsPanel() {
        super(Name.ITEMS_PANEL, MELayout.LAYOUT);

        add(yearSelector);
        add(monthSelector);

        add(new CsGButton(ITEMS_BACK_BUTTON, "Vissza", this::backToMenuPanel));//todo english
        add(new CsGButton(ITEMS_CALCULATE_TABLE_BUTTON, "Mutasd", this::calculateItemsTable));//todo english
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            final ItemRepository itemRepository = Application.getBean(ItemRepository.class);
            yearSelector.reset(itemRepository.findAllYear());
        }
        super.setVisible(visible);
    }

    private void calculateItemsTable(ActionEvent event) {

    }

    private void backToMenuPanel(ActionEvent event) {
        setVisible(false);
        MenuPanel.MENU_PANEL.setVisible(true);
    }

}
