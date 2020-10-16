package csg.monthly.expensies.view.panel.tag;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.BorderFactory;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.PrioGroup;
import csg.monthly.expensies.domain.service.PrioGroupService;
import csg.swing.CsGLabel;
import csg.swing.CsGLayout;
import csg.swing.CsGListBox;
import csg.swing.CsGPanel;
import csg.swing.CsGTextField;

public class PrioGroupPanel extends CsGPanel {

    private CsGListBox<PrioGroup> prioGroups = new CsGListBox<>(Name.PRIO_GROUPS);
    private CsGTextField name = new CsGTextField(Name.NAME);
    private CsGTextField prio = new CsGTextField(Name.PRIO, true);
    private CsGTextField color = new CsGTextField(Name.COLOR);
    private CsGTextField textColor = new CsGTextField(Name.TEXT_COLOR);

    public PrioGroupPanel(Enum<?> panelName) {
        super(panelName, new PrioGroupPanelLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
        add(new CsGLabel(Name.TITLE, "Prio csoport:"));//todo english

        add(prioGroups);

        add(new CsGLabel(Name.NAME_LABEL, "Név:"));//todo english
        add(name);

        add(new CsGLabel(Name.PRIO_LABEL, "Prio:"));//todo english
        add(prio);

        add(new CsGLabel(Name.COLOR_LABEL, "Szín:"));//todo english
        add(color);

        add(new CsGLabel(Name.TEXT_COLOR_LABEL, "Szöveg szín:"));//todo english
        add(textColor);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            setPrioGroups();
        }
        super.setVisible(visible);
    }

    private void setPrioGroups() {
        final List<PrioGroup> prioGroups = Application.getBean(PrioGroupService.class).getPrioGroups();
        this.prioGroups.reset(prioGroups);
    }

    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(new Rectangle((int) r.getX(), (int) r.getY(), 500, 500));
    }

    private enum Name {
        TITLE(10, 10, 100, 25),
        PRIO_GROUPS(10, 45, 150, 100),
        NAME_LABEL(170, 45, 25, 25),
        NAME(205, 45, 150, 25),
        PRIO_LABEL(170, 80, 30, 20),
        COLOR_LABEL(230, 80, 80, 20),
        TEXT_COLOR_LABEL(295, 80, 80, 20),
        PRIO(170, 102, 35, 25),
        COLOR(230, 102, 55, 25),
        TEXT_COLOR(295, 102, 55, 25);

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

    private static class PrioGroupPanelLayout implements CsGLayout {
        @Override
        public Rectangle getRectangleForComponentName(final String name) {
            return Name.valueOf(name).getRectangle();
        }
    }
}
