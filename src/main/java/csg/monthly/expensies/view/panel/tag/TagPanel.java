package csg.monthly.expensies.view.panel.tag;

import static csg.monthly.expensies.view.panel.MenuPanel.MENU_PANEL;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_COMMENT;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_DELETE_BUTTON;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_DELETE_LABEL;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_FILTER_PANEL;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_ITEMS;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_NEW_TAG_PANEL;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_OVERWRITE_BUTTON;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_PRIO_GROUP_PANEL;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_SAVE_COMMENT_BUTTON;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_TAG_NAME;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_TAG_PRIO;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL_TAG_SELECTOR;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.TagComment;
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
import csg.swing.CsGScrollableTextArea;
import csg.swing.CsGTextField;

public class TagPanel extends CsGPanel {
    public static final TagPanel TAG_PANEL = new TagPanel();

    private CsGComboBox<Tag> tagSelector = new CsGComboBox<>(TAG_PANEL_TAG_SELECTOR);

    private CsGTextField name = new CsGTextField(TAG_PANEL_TAG_NAME);
    private CsGTextField prio = new CsGTextField(TAG_PANEL_TAG_PRIO, true);

    private ItemsTablePanel items = new ItemsTablePanel(TAG_PANEL_ITEMS);
    private DefaultFiltersPanel filters = new DefaultFiltersPanel(TAG_PANEL_FILTER_PANEL, event -> setVisible(true));

    private NewTagPanel newTagPanel = new NewTagPanel(TAG_PANEL_NEW_TAG_PANEL, () -> setVisible(true));

    private CsGScrollableTextArea comment = new CsGScrollableTextArea(TAG_PANEL_COMMENT);

    private PrioGroupPanel prioGroupPanel = new PrioGroupPanel(TAG_PANEL_PRIO_GROUP_PANEL);

    private TagPanel() {
        super(Name.TAG_PANEL, MELayout.LAYOUT);

        tagSelector.addActionListener(event -> setVisible(true));
        add(tagSelector);
        add(new CsGButton(TAG_PANEL_BACK_BUTTON, "Vissza", event -> back()));//todo english

        add(name);
        add(prio);
        add(new CsGButton(TAG_PANEL_OVERWRITE_BUTTON, "Felülír", event -> overwrite()));//todo english

        add(new CsGLabel(TAG_PANEL_DELETE_LABEL, "Figyelem, csak akkor lehet törölni ha nem tartozik hozzá item!"));//todo english
        add(new CsGButton(TAG_PANEL_DELETE_BUTTON, "Törlés", event -> delete()));//todo english

        add(filters);
        add(newTagPanel);
        add(comment);
        add(new CsGButton(TAG_PANEL_SAVE_COMMENT_BUTTON, "Komment mentése", event -> saveComment()));//todo english

        add(prioGroupPanel);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            setTagSelector();
            setItems();
            setComment();
            filters.setVisible(true);
            prioGroupPanel.setPrioGroups();
            if (tagSelector.getSelectedItem() != null) {
                prioGroupPanel.setPrioGroupByTag((Tag) tagSelector.getSelectedItem());
            }
        }
        if (newTagPanel != null) {
            newTagPanel.setVisible(visible);
        }
        super.setVisible(visible);
    }

    private void setTagSelector() {
        Tag selectedTag = (Tag) tagSelector.getSelectedItem();
        final List<Tag> tags = Application.getBean(TagService.class).findAllOrderedByFrequency();
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

    private void setComment() {
        final TagService tagService = Application.getBean(TagService.class);
        final Optional<TagComment> commentByTag = tagService.getCommentByTag((Tag) tagSelector.getSelectedItem());
        String comment = commentByTag.map(TagComment::getComment).orElse("");
        this.comment.setText(comment);
    }

    private void saveComment() {
        if (!comment.getText().isEmpty()) {
            Application.getBean(TagService.class).saveComment((Tag) tagSelector.getSelectedItem(), comment.getText());
        }
    }

    private void setItems() {
        final List<Item> filteredItems = filters.filterWithTag((Tag) tagSelector.getSelectedItem());
        if (items != null) {
            items.setVisible(false);
            items.setEnabled(false);
            remove(items);
        }
        final List<Tag> tags = Application.getBean(TagService.class).findAllOrderedByFrequency();
        items = new ItemsTablePanel(TAG_PANEL_ITEMS);
        for (Item item : filteredItems) {
            items.add(new TableItem(item, tags));
        }
        items.setScrollBarToBottom();
        add(items);
    }

    private void overwrite() {
        Tag selectedTag = (Tag) tagSelector.getSelectedItem();
        if (selectedTag != null && !name.getText().isEmpty() && !prio.getText().isEmpty()) {
            selectedTag.setName(name.getText());
            selectedTag.setPrio(prio.getTextAsInteger());
            Application.getBean(TagService.class).save(selectedTag);
            setVisible(true);
        }
    }

    private void delete() {
        Tag selectedTag = (Tag) tagSelector.getSelectedItem();
        final TagService tagService = Application.getBean(TagService.class);
        if (tagService.isTagDeletable(selectedTag)) {
            tagService.delete(selectedTag);
        }
        tagSelector.reset(new ArrayList<>());
        setVisible(true);
    }

    private void back() {
        setVisible(false);
        MENU_PANEL.setVisible(true);
    }
}
