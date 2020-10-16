package csg.monthly.expensies.view.panel.tag;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.PrioGroup;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.service.TagService;
import csg.swing.CsGLayout;
import csg.swing.CsGPanel;
import csg.swing.CsGScrollableLabel;
import csg.swing.html.CsGHtmlBodyBuilder;
import csg.swing.html.CsGHtmlBuilder;

public class PrioGroupTagPanel extends CsGPanel {
    private CsGScrollableLabel tags = new CsGScrollableLabel(Name.TAGS, "");

    private Optional<PrioGroup> prioGroup = Optional.empty();

    public PrioGroupTagPanel(final Enum<?> name) {
        super(name, new PrioGroupTagPanelLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

        add(tags);
        setVisible(true);
    }

    public void setTags(PrioGroup prioGroup) {
        this.prioGroup = Optional.of(prioGroup);
        final List<Tag> tags = Application.getBean(TagService.class).findByPrioGroup(prioGroup);
        final CsGHtmlBodyBuilder body = new CsGHtmlBodyBuilder();
        tags.forEach(tag -> body.addText("p", tag.getName()));
        final String tagsText = new CsGHtmlBuilder(body).build();
        this.tags.setText(tagsText);
    }

    private enum Name {
        TAGS(0, 0, 150, 180);

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
