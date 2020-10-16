package csg.monthly.expensies.view.panel.tag;

import java.awt.Rectangle;
import java.util.List;
import java.util.Optional;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.PrioGroup;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.service.TagService;
import csg.swing.CsGButton;
import csg.swing.CsGComboBox;
import csg.swing.CsGLabel;
import csg.swing.CsGLayout;
import csg.swing.CsGListBox;
import csg.swing.CsGPanel;

public class PrioGroupTagPanel extends CsGPanel {
    private CsGListBox<Tag> tags = new CsGListBox<>(Name.TAGS, event -> {
    });
    private CsGComboBox<Tag> tagSelector = new CsGComboBox<>(Name.TAG_SELECTOR);

    private PrioGroup prioGroup = null;

    PrioGroupTagPanel(final Enum<?> name) {
        super(name, new PrioGroupTagPanelLayout());
        add(new CsGLabel(Name.TITLE, "Prio csoport tag-jei:"));//todo english

        add(tags);

        add(tagSelector);
        add(new CsGButton(Name.ADD_TAG, "Ad Tag", event -> addTag()));//todo english
        add(new CsGButton(Name.REMOVE_TAG, "Tag eltávolítása", event -> removeTag()));//todo english

        setVisible(true);
    }

    public void setTags(PrioGroup prioGroup) {
        this.prioGroup = prioGroup;
        if (this.tags != null) {
            remove(this.tags);
        }
        this.tags = new CsGListBox<>(Name.TAGS, event -> {
        });
        final TagService tagService = Application.getBean(TagService.class);
        final List<Tag> tags = tagService.findByPrioGroup(prioGroup);
        this.tags.reset(tags);
        add(this.tags);
        final List<Tag> allTag = tagService.findAll();
        tagSelector.reset(allTag);
        tagSelector.setSelectedIndex(0);
    }

    private void addTag() {
        if (prioGroup != null) {
            final Optional<Tag> selectedTag = Optional.ofNullable((Tag) tagSelector.getSelectedItem());
            if (selectedTag.isPresent()) {
                final Tag tag = selectedTag.get();
                tag.setPrioGroup(prioGroup);
                Application.getBean(TagService.class).save(tag);
                setTags(prioGroup);
            }
        }
    }

    private void removeTag() {
        final Optional<Tag> selectedTag = Optional.ofNullable((Tag) tagSelector.getSelectedItem());
        if (selectedTag.isPresent()) {
            final Tag tag = selectedTag.get();
            tag.setPrioGroup(null);
            Application.getBean(TagService.class).save(tag);
            setTags(prioGroup);
        }
    }

    private enum Name {
        TITLE(0, 0, 150, 25),
        TAGS(0, 35, 150, 180),
        TAG_SELECTOR(160, 35, 100, 25),
        ADD_TAG(270, 35, 75, 25),
        REMOVE_TAG(160, 190, 185, 25);

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

    private static class PrioGroupTagPanelLayout implements CsGLayout {
        @Override
        public Rectangle getRectangleForComponentName(final String name) {
            return Name.valueOf(name).getRectangle();
        }
    }
}
