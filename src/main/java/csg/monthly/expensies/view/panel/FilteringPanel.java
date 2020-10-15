package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.util.Name.FILTERING_FILTER_PANEL;
import static csg.monthly.expensies.view.util.Name.FILTERING_ITEMS;
import static csg.monthly.expensies.view.util.Name.FILTERING_PANEL_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.FILTERING_SUMMARIZE_LABEL;

import java.awt.event.ActionEvent;
import java.util.List;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.service.TagService;
import csg.monthly.expensies.view.panel.items.ItemsTablePanel;
import csg.monthly.expensies.view.panel.items.TableItem;
import csg.monthly.expensies.view.panel.items.filter.DetailedFiltersPanel;
import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGLabel;
import csg.swing.CsGPanel;

public class FilteringPanel extends CsGPanel {
    public static final FilteringPanel FILTERING_PANEL = new FilteringPanel();

    private ItemsTablePanel items = new ItemsTablePanel(FILTERING_ITEMS);
    private DetailedFiltersPanel filters = new DetailedFiltersPanel(FILTERING_FILTER_PANEL, this::filter);

    private final CsGLabel summarize = new CsGLabel(FILTERING_SUMMARIZE_LABEL, "");

    private FilteringPanel() {
        super(Name.FILTERING_PANEL, MELayout.LAYOUT);

        add(new CsGButton(FILTERING_PANEL_BACK_BUTTON, "Vissza", this::back));//todo english

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

    private void filter(ActionEvent event) {
        doLayout();
        setVisible(true);
    }

    private void filter() {
        final List<Tag> tags = Application.getBean(TagService.class).findAll();
        final List<Item> filteredItems = filters.filter();
        if (items != null) {
            items.setVisible(false);
            items.setEnabled(false);
            remove(items);
        }
        int sum = 0;
        items = new ItemsTablePanel(FILTERING_ITEMS);
        for (Item item : filteredItems) {
            items.add(new TableItem(item, tags));
            sum += item.getAmount();
        }
        items.setScrollBarToBottom();
        add(items);
        int avg = filteredItems.size() == 0 || sum == 0 ? 0 : sum / filteredItems.size();
        summarize.setText("Darab: " + filteredItems.size() + "; Összeg: " + sum + "; Átlag: " + avg);//todo english
    }

    private void back(ActionEvent event) {
        setVisible(false);
        MenuPanel.MENU_PANEL.setVisible(true);
    }
}
