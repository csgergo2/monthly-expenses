package csg.monthly.expensies.view.panel.tag;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.service.TagService;
import csg.monthly.expensies.exception.MonthlyExpensesException;
import csg.swing.CsGButton;
import csg.swing.CsGLabel;
import csg.swing.CsGLayout;
import csg.swing.CsGPanel;
import csg.swing.CsGTextField;

public class NewTagPanel extends CsGPanel {
    private CsGTextField tagName = new CsGTextField(Name.NAME);
    private CsGTextField tagPrio = new CsGTextField(Name.PRIO, true);

    public NewTagPanel(final Enum<?> panelName) {
        super(panelName, new NewTagPanelLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

        add(new CsGLabel(Name.TITLE, "Új Tag:"));//todo english

        add(new CsGLabel(Name.NAME_LABEL, "Név:"));//todo english
        add(tagName);

        add(new CsGLabel(Name.PRIO_LABEL, "Prio:"));//todo english
        add(tagPrio);

        add(new CsGButton(Name.SAVE_BUTTON, "Tag Mentés", this::saveTag));//todo english
    }

    private void saveTag(ActionEvent event) {
        if (tagName.getText().isEmpty() || tagPrio.getText().isEmpty()) {
            throw new MonthlyExpensesException("Empty value via creating tag; name: " + tagName.getText() + "; prio: " + tagPrio.getText());
        }
        final Tag tag = new Tag();
        tag.setName(tagName.getText());
        tag.setPrio(tagPrio.getTextAsInteger());
        Application.getBean(TagService.class).save(tag);
        tagName.setText("");
        tagPrio.setText("");
    }

    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(new Rectangle((int) r.getX(), (int) r.getY(), 230, 150));
    }

    protected enum Name {
        TITLE(10, 10, 100, 25),
        NAME_LABEL(10, 45, 40, 25),
        NAME(60, 45, 100, 25),
        PRIO_LABEL(10, 80, 40, 25),
        PRIO(60, 80, 35, 25),
        SAVE_BUTTON(10, 115, 100, 25);

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

    private static class NewTagPanelLayout implements CsGLayout {
        @Override
        public Rectangle getRectangleForComponentName(final String name) {
            return NewTagPanel.Name.valueOf(name).getRectangle();
        }
    }
}
