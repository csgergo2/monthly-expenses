package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.util.Name.TAG_PANEL_ITEMS;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_SHOW_BUTTON;
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
import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGComboBox;
import csg.swing.CsGPanel;

public class TagPanel extends CsGPanel {
    public static final TagPanel TAG_PANEL = new TagPanel();

    private CsGComboBox<Tag> tagSelector = new CsGComboBox<>(TAG_PANEL_TAG_SELECTOR);
    private ItemsTablePanel items = new ItemsTablePanel(TAG_PANEL_ITEMS);

    private TagPanel() {
        super(Name.TAG_PANEL, MELayout.LAYOUT);

        add(tagSelector);
        add(new CsGButton(TAG_PANEL_SHOW_BUTTON, "Frissítés", this::manageItems));

    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            setTagSelector();
            setItems();
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
        if (selectedTag == null && tags.size() > 0) {
            tagSelector.setSelectedItem(tags.get(0));
        } else {
            tagSelector.setSelectedItem(selectedTag);
        }
    }

    private void setItems() {
        //@formatter:off
        final List<Item> filteredItems = Application.getBean(ItemService.class).findAllByFilter(
                null,
                null,
                null,
                (Tag) tagSelector.getSelectedItem(),
                null,
                null,
                null
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
}
