package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.util.Name.FILTERING_FILTER_BUTTON;
import static csg.monthly.expensies.view.util.Name.FILTERING_INCOME_FILTER;
import static csg.monthly.expensies.view.util.Name.FILTERING_INCOME_FILTER_LABEL;
import static csg.monthly.expensies.view.util.Name.FILTERING_ITEMS;
import static csg.monthly.expensies.view.util.Name.FILTERING_NAME_FILTER;
import static csg.monthly.expensies.view.util.Name.FILTERING_NAME_FILTER_LABEL;
import static csg.monthly.expensies.view.util.Name.FILTERING_PANEL_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.FILTERING_TAG_FILTER;
import static csg.monthly.expensies.view.util.Name.FILTERING_TAG_FILTER_LABEL;
import static csg.monthly.expensies.view.util.Name.FILTERING_YEAR_FILTER;
import static csg.monthly.expensies.view.util.Name.FILTERING_YEAR_FILTER_LABEL;

import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.service.ItemService;
import csg.monthly.expensies.domain.service.TagService;
import csg.monthly.expensies.view.panel.items.ItemsTablePanel;
import csg.monthly.expensies.view.panel.items.TableItem;
import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGCheckBox;
import csg.swing.CsGComboBox;
import csg.swing.CsGLabel;
import csg.swing.CsGPanel;
import csg.swing.CsGTextField;

public class FilteringPanel extends CsGPanel {
    public static final FilteringPanel FILTERING_PANEL = new FilteringPanel();

    private CsGComboBox<String> year = new CsGComboBox<>(FILTERING_YEAR_FILTER);

    private final CsGTextField name = new CsGTextField(FILTERING_NAME_FILTER);
    private final CsGComboBox<Tag> tag = new CsGComboBox<>(FILTERING_TAG_FILTER);
    private final CsGCheckBox isIncome = new CsGCheckBox(FILTERING_INCOME_FILTER);

    private ItemsTablePanel items = new ItemsTablePanel(FILTERING_ITEMS);

    private FilteringPanel() {
        super(Name.FILTERING_PANEL, MELayout.LAYOUT);

        final List<String> years = Collections.singletonList("");
        year.reset(years);
        add(year);
        add(new CsGLabel(FILTERING_YEAR_FILTER_LABEL, "Év:"));//todo english

        add(name);
        add(new CsGLabel(FILTERING_NAME_FILTER_LABEL, "Név:"));//todo english
        final List<Tag> tags = Collections.singletonList(new Tag());
        tag.reset(tags);
        add(tag);
        add(new CsGLabel(FILTERING_TAG_FILTER_LABEL, "Tag:"));//todo english
        add(isIncome);
        add(new CsGLabel(FILTERING_INCOME_FILTER_LABEL, "Bevétel:"));//todo english

        add(new CsGButton(FILTERING_FILTER_BUTTON, "Szűrés", this::filter));//todo english
        add(new CsGButton(FILTERING_PANEL_BACK_BUTTON, "Vissza", this::back));//todo english
    }

    private void setFilteringFields() {
        //todo if NullPointer thrown
    }

    private void setYearSelector() {
        final String selectedYear = (String) year.getSelectedItem();
        final List<String> years =
                Application.getBean(ItemService.class).findAllYear().stream().map(year -> Integer.toString(year)).collect(Collectors.toList());
        years.add("");
        year.reset(years);
        year.setSelectedItem(selectedYear);
    }

    private void setTagSelector() {
        Tag selectedTag = (Tag) tag.getSelectedItem();
        final List<Tag> tags = Application.getBean(TagService.class).findAll();
        tags.add(new Tag());
        tag.reset(tags);
        tag.setSelectedItem(selectedTag);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            filter();
            setYearSelector();
            setTagSelector();
        }
        super.setVisible(visible);
    }

    private void filter(ActionEvent event) {
        doLayout();
        setVisible(true);
    }

    private void filter() {
        final List<Tag> tags = Application.getBean(TagService.class).findAll();
        //@formatter:off
        final List<Item> filteredItems = Application.getBean(ItemService.class).findAllByFilter(
                (String) year.getSelectedItem(),
                name.getText(),
                (Tag) tag.getSelectedItem(),
                isIncome.isSelected()
        );
        //@formatter:on
        if (items != null) {
            items.setVisible(false);
            items.setEnabled(false);
            remove(items);
        }
        items = new ItemsTablePanel(FILTERING_ITEMS);
        for (Item item : filteredItems) {
            items.add(new TableItem(item, tags));
        }
        items.setScrollBarToBottom();
        add(items);
    }

    private void back(ActionEvent event) {
        setVisible(false);
        MenuPanel.MENU_PANEL.setVisible(true);
    }
}
