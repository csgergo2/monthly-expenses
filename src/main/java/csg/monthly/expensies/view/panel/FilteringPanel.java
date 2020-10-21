package csg.monthly.expensies.view.panel;

import java.awt.Rectangle;
import java.util.List;

import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.view.panel.items.ItemsTablePanel;
import csg.monthly.expensies.view.panel.items.TableItem;
import csg.monthly.expensies.view.panel.items.filter.DetailedFiltersPanel;
import csg.swing.CsGButton;
import csg.swing.CsGLabel;
import csg.swing.CsGLayout;
import csg.swing.CsGPanel;

public class FilteringPanel extends CsGPanel {
    public static final FilteringPanel FILTERING_PANEL = new FilteringPanel();

    private ItemsTablePanel items = new ItemsTablePanel(Name.FILTERING_ITEMS);
    private DetailedFiltersPanel filters = new DetailedFiltersPanel(Name.FILTERING_FILTER_PANEL, event -> setVisible(true));

    private final CsGLabel summarize = new CsGLabel(Name.FILTERING_SUMMARIZE_LABEL, "");

    private FilteringPanel() {
        super(MenuPanel.Name.FILTERING_PANEL, (CsGLayout) name -> Name.valueOf(name).getRectangle());

        add(new CsGButton(Name.FILTERING_PANEL_BACK_BUTTON, "Vissza", event -> back()));//todo english

        add(filters);
        add(summarize);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            filter();
            filters.setVisible(true);
        }
        super.setVisible(visible);
    }

    private void filter() {
        final List<Item> filteredItems = filters.filter();
        if (items != null) {
            items.setVisible(false);
            items.setEnabled(false);
            remove(items);
        }
        int sum = 0;
        items = new ItemsTablePanel(Name.FILTERING_ITEMS);
        for (Item item : filteredItems) {
            items.add(TableItem.of(item));
            sum += item.getAmount();
        }
        items.setScrollBarToBottom();
        add(items);
        int avg = filteredItems.size() == 0 || sum == 0 ? 0 : sum / filteredItems.size();
        summarize.setText("Darab: " + filteredItems.size() + "; Összeg: " + sum + "; Átlag: " + avg);//todo english
    }

    private void back() {
        setVisible(false);
        MenuPanel.MENU_PANEL.setVisible(true);
    }

    private enum Name {
        FILTERING_ITEMS(10, 10, 555, 500),
        FILTERING_PANEL_BACK_BUTTON(575, 10, 100, 25),
        FILTERING_FILTER_PANEL(575, 45, 555, 500),
        FILTERING_SUMMARIZE_LABEL(10, 520, 500, 25);

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
