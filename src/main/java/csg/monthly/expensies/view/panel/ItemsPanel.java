package csg.monthly.expensies.view.panel;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.MonthComment;
import csg.monthly.expensies.domain.date.Month;
import csg.monthly.expensies.domain.service.ItemService;
import csg.monthly.expensies.view.panel.items.ItemsTablePanel;
import csg.monthly.expensies.view.panel.items.TableItem;
import csg.swing.CsGButton;
import csg.swing.CsGComboBox;
import csg.swing.CsGLabel;
import csg.swing.CsGLayout;
import csg.swing.CsGPanel;
import csg.swing.CsGScrollableTextArea;

public class ItemsPanel extends CsGPanel {
    public static final ItemsPanel ITEMS_PANEL = new ItemsPanel();

    private static CsGComboBox<Month> createMonthSelector() {
        CsGComboBox<Month> monthSelector = new CsGComboBox<>(Name.ITEMS_MONTH_SELECTOR);
        monthSelector.reset(Arrays.asList(Month.values()));
        monthSelector.setSelectedItem(Month.getCurrent());
        return monthSelector;
    }

    private CsGComboBox<Integer> yearSelector = new CsGComboBox<>(Name.ITEMS_YEAR_SELECTOR);
    private CsGComboBox<Month> monthSelector = createMonthSelector();

    private ItemsTablePanel tableOfOutgoings = new ItemsTablePanel(Name.ITEMS_OUTGOINGS_TABLE);
    private ItemsTablePanel tableOfIncomes = new ItemsTablePanel(Name.ITEMS_INCOMES_TABLE);

    private CsGLabel outgoingsSum = new CsGLabel(Name.ITEMS_SUM_OUTGOINGS, "");
    private CsGLabel incomesSum = new CsGLabel(Name.ITEMS_SUM_INCOMES, "");
    private CsGScrollableTextArea comment = new CsGScrollableTextArea(Name.ITEMS_MONTH_COMMENT);

    private ItemsPanel() {
        super(csg.monthly.expensies.view.util.Name.ITEMS_PANEL, (CsGLayout) name -> Name.valueOf(name).getRectangle());
        setBorder(BorderFactory.createLineBorder(Color.black));

        add(yearSelector);
        add(monthSelector);

        add(new CsGButton(Name.ITEMS_CALCULATE_MONTH_BUTTON, "Mutasd", event -> setVisible(true)));//todo english
        add(new CsGButton(Name.ITEMS_SAVE_COMMENT_BUTTON, "Megjegyzés mentése", event -> saveComment()));//todo english

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
        List<Item> items = itemService.findAllByYearAndMonth((int) yearSelector.getSelectedItem(), (Month) monthSelector.getSelectedItem());

        calculateOutgoings(items);
        calculateIncomes(items);
        comment.setText(
                itemService.getComment((Month) monthSelector.getSelectedItem()).orElse(new MonthComment((Month) monthSelector.getSelectedItem(), ""))
                           .getComment());
    }

    private void calculateOutgoings(List<Item> items) {
        final List<Item> outgoings = items.stream().filter(item -> !item.isIncome()).sorted().collect(Collectors.toList());
        if (tableOfOutgoings != null) {
            tableOfOutgoings.setVisible(false);
            tableOfOutgoings.setEnabled(false);
            remove(tableOfOutgoings);
        }
        tableOfOutgoings = new ItemsTablePanel(Name.ITEMS_OUTGOINGS_TABLE);
        int sum = 0;
        for (Item item : outgoings) {
            tableOfOutgoings.add(TableItem.of(item));
            sum += item.getAmount();
        }
        outgoingsSum.setText("Összesen: " + Integer.toString(sum));//todo english
        tableOfOutgoings.setScrollBarToBottom();
        add(tableOfOutgoings);
    }

    private void calculateIncomes(List<Item> items) {
        final List<Item> incomes = items.stream().filter(Item::isIncome).sorted().collect(Collectors.toList());
        if (tableOfIncomes != null) {
            tableOfIncomes.setVisible(false);
            tableOfIncomes.setEnabled(false);
            remove(tableOfIncomes);
        }
        tableOfIncomes = new ItemsTablePanel(Name.ITEMS_INCOMES_TABLE);
        int sum = 0;
        for (Item item : incomes) {
            tableOfIncomes.add(TableItem.of(item));
            sum += item.getAmount();
        }
        incomesSum.setText("Összesen: " + Integer.toString(sum));//todo english
        tableOfIncomes.setScrollBarToBottom();
        add(tableOfIncomes);
    }

    private void saveComment() {
        Application.getBean(ItemService.class).saveComment((Month) monthSelector.getSelectedItem(), comment.getText());
    }

    private enum Name {
        ITEMS_YEAR_SELECTOR(10, 10, 100, 25),
        ITEMS_MONTH_SELECTOR(120, 10, 100, 25),
        ITEMS_CALCULATE_MONTH_BUTTON(230, 10, 100, 25),
        ITEMS_OUTGOINGS_TABLE(10, 45, 555, 600),
        ITEMS_INCOMES_TABLE(575, 45, 555, 600),
        ITEMS_SUM_OUTGOINGS(10, 655, 500, 25),
        ITEMS_SUM_INCOMES(575, 655, 500, 25),
        ITEMS_MONTH_COMMENT(10, 690, 900, 50),
        ITEMS_SAVE_COMMENT_BUTTON(920, 690, 200, 50);

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
