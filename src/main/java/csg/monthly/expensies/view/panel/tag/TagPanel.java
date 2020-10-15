package csg.monthly.expensies.view.panel.tag;

import static csg.monthly.expensies.view.panel.MenuPanel.MENU_PANEL;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_DELETE_BUTTON;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_DELETE_LABEL;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_FILTER_PANEL;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_ITEMS;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_NEW_TAG_PANEL;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_OVERWRITE_BUTTON;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_SHOW_BUTTON;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_TAG_NAME;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_TAG_PRIO;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_TAG_SELECTOR;

import java.awt.event.ActionEvent;
import java.util.List;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.service.ItemService;
import csg.monthly.expensies.domain.service.TagService;
import csg.monthly.expensies.view.panel.items.ItemsTablePanel;
import csg.monthly.expensies.view.panel.items.TableItem;
import csg.monthly.expensies.view.panel.items.filter.DefaultFiltersPanel;
import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGComboBox;
import csg.swing.CsGLabel;
import csg.swing.CsGPanel;
import csg.swing.CsGTextField;

public class TagPanel extends CsGPanel {
    public static final TagPanel TAG_PANEL = new TagPanel();

    private CsGComboBox<Tag> tagSelector = new CsGComboBox<>(TAG_PANEL_TAG_SELECTOR);

    private CsGTextField name = new CsGTextField(TAG_PANEL_TAG_NAME);
    private CsGTextField prio = new CsGTextField(TAG_PANEL_TAG_PRIO, true);

    private ItemsTablePanel items = new ItemsTablePanel(TAG_PANEL_ITEMS);
    private DefaultFiltersPanel filters = new DefaultFiltersPanel(TAG_PANEL_FILTER_PANEL, this::filter);

    private NewTagPanel newTagPanel = new NewTagPanel(TAG_PANEL_NEW_TAG_PANEL);

    private TagPanel() {
        super(Name.TAG_PANEL, MELayout.LAYOUT);

        add(tagSelector);
        add(new CsGButton(TAG_PANEL_SHOW_BUTTON, "Frissítés", this::manageItems));//todo english
        add(new CsGButton(TAG_PANEL_BACK_BUTTON, "Vissza", this::back));//todo english

        add(name);
        add(prio);
        add(new CsGButton(TAG_PANEL_OVERWRITE_BUTTON, "Felülír", this::overwrite));//todo english

        add(new CsGLabel(TAG_PANEL_DELETE_LABEL, "Figyelem, csak akkor lehet törölni ha nem tartozik hozzá item!"));//todo english
        add(new CsGButton(TAG_PANEL_DELETE_BUTTON, "Törlés", this::delete));//todo english

        add(filters);
        add(newTagPanel);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            setTagSelector();
            setItems();
            filters.setVisible(visible);
        }
        if (newTagPanel != null) {
            newTagPanel.setVisible(visible);
        }
        super.setVisible(visible);
    }

    private void manageItems(ActionEvent event) {
        doLayout();
        setVisible(true);
    }

    private void setTagSelector() {
        Tag selectedTag = (Tag) tagSelector.getSelectedItem();
        final List<Tag> tags = Application.getBean(TagService.class).findAll();
        tagSelector.reset(tags);
        if (selectedTag != null) {
            name.setText(selectedTag.getName());
            tagSelector.setSelectedItem(selectedTag);
            prio.setText(Integer.toString(selectedTag.getPrio()));
        } else if (tags.size() > 0) {
            tagSelector.setSelectedItem(tags.get(0));
            name.setText(tags.get(0).getName());
            prio.setText(Integer.toString(tags.get(0).getPrio()));
        }
    }

    private void filter(ActionEvent event) {
        doLayout();
        setVisible(true);
    }

    private void setItems() {
        //@formatter:off
        final List<Item> filteredItems = Application.getBean(ItemService.class).findAllByFilter(
                filters.getYear(),
                filters.getMonth(),
                filters.getNameFilter(),
                (Tag) tagSelector.getSelectedItem(),
                null,
                filters.getStartDate(),
                filters.getEndDate()
        );
        //@formatter:on
        if (items != null) {
            items.setVisible(false);
            items.setEnabled(false);
            remove(items);
        }
        final List<Tag> tags = Application.getBean(TagService.class).findAll();
        items = new ItemsTablePanel(TAG_PANEL_ITEMS);
        for (Item item : filteredItems) {
            items.add(new TableItem(item, tags));
        }
        items.setScrollBarToBottom();
        add(items);
    }

    private void overwrite(ActionEvent event) {
        Tag selectedTag = (Tag) tagSelector.getSelectedItem();
        if (selectedTag != null && !name.getText().isEmpty() && !prio.getText().isEmpty()) {
            selectedTag.setName(name.getText());
            selectedTag.setPrio(prio.getTextAsInteger());
            Application.getBean(TagService.class).save(selectedTag);
            setVisible(true);
        }
    }

    private void delete(ActionEvent event) {
        Tag selectedTag = (Tag) tagSelector.getSelectedItem();
        final TagService tagService = Application.getBean(TagService.class);
        if (tagService.isTagDeletable(selectedTag)) {
            tagService.delete(selectedTag);
        }
    }

    private void back(ActionEvent event) {
        setVisible(false);
        MENU_PANEL.setVisible(true);
    }
}
