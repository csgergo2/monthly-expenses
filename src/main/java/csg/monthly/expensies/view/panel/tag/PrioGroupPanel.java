package csg.monthly.expensies.view.panel.tag;

import static csg.monthly.expensies.view.util.ColorParser.isTextColorParsable;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.BorderFactory;
import javax.swing.event.ListSelectionEvent;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.PrioGroup;
import csg.monthly.expensies.domain.service.PrioGroupService;
import csg.swing.CsGButton;
import csg.swing.CsGLabel;
import csg.swing.CsGLayout;
import csg.swing.CsGListBox;
import csg.swing.CsGPanel;
import csg.swing.CsGTextField;
import csg.swing.listener.CsGKeyReleasedListener;

public class PrioGroupPanel extends CsGPanel {

    private CsGListBox<PrioGroup> prioGroups = new CsGListBox<>(Name.PRIO_GROUPS, this::listSelectionEvent);
    private CsGTextField name = new CsGTextField(Name.NAME);
    private CsGTextField prio = new CsGTextField(Name.PRIO, true);
    private CsGTextField color = new CsGTextField(Name.COLOR);
    private CsGTextField textColor = new CsGTextField(Name.TEXT_COLOR);
    private CsGLabel colorSample = new CsGLabel(Name.COLOR_SAMPLE, "Teszt szöveg");
    private PrioGroupTagPanel tags = new PrioGroupTagPanel(Name.TAGS);

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

        add(new CsGLabel(Name.TEXT_COLOR_LABEL, "Szöveg:"));//todo english
        add(textColor);

        color.addActionListener(event -> setColorSample(color::getText, textColor::getText));
        color.addKeyListener((CsGKeyReleasedListener) (event -> setColorSample(color::getText, textColor::getText)));
        textColor.addActionListener(event -> setColorSample(color::getText, textColor::getText));
        textColor.addKeyListener((CsGKeyReleasedListener) (event -> setColorSample(color::getText, textColor::getText)));
        add(colorSample);

        add(new CsGButton(Name.OVERWRITE, "Felülír", event -> overwritePrioGroup()));//todo english
        add(new CsGButton(Name.SAVE_NEW, "Mentés másnként", event -> saveAs()));//todo english

        add(tags);
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
        listSelectionEvent();
    }

    private void listSelectionEvent(ListSelectionEvent event) {
        if (!event.getValueIsAdjusting()) {
            listSelectionEvent();
        }
    }

    private void listSelectionEvent() {
        if (prioGroups.getSelectedValue().isPresent()) {
            final PrioGroup prioGroup = prioGroups.getSelectedValue().get();
            name.setText(prioGroup.getName());
            prio.setText(Integer.toString(prioGroup.getPrio()));
            color.setText(prioGroup.getColor());
            textColor.setText(prioGroup.getTextColor());
            setColorSample(prioGroup::getColor, prioGroup::getTextColor);
            tags.setTags(prioGroup);
        } else {
            name.setText("");
            prio.setText("");
            color.setText("");
            textColor.setText("");
            setColorSample(() -> getBackground().toString(), () -> "#000");
        }
    }

    private void setColorSample(Supplier<String> color, Supplier<String> textColor) {
        try {
            final Color decodedBackground = Color.decode(color.get());
            final Color decodedForeground = Color.decode(textColor.get());
            colorSample.setOpaque(true);
            colorSample.setBackground(decodedBackground);
            colorSample.setForeground(decodedForeground);
        } catch (NumberFormatException e) {
            //todo
        }
    }

    private void overwritePrioGroup() {
        if (prioGroups.getSelectedValue().isPresent() && !name.getText().isEmpty() && !prio.getText().isEmpty() &&
                isTextColorParsable(color.getText()) && isTextColorParsable(textColor.getText())) {
            final PrioGroup prioGroup = prioGroups.getSelectedValue().get();
            prioGroup.setName(name.getText());
            prioGroup.setPrio(prio.getTextAsInteger());
            prioGroup.setColor(color.getText());
            prioGroup.setTextColor(textColor.getText());
            Application.getBean(PrioGroupService.class).save(prioGroup);
            setPrioGroups();
            prioGroups.setSelectedValue(prioGroup);
        }
    }

    private void saveAs() {
        if (prioGroups.getSelectedValue().isPresent() && !name.getText().isEmpty() && !prio.getText().isEmpty() &&
                isTextColorParsable(color.getText()) && isTextColorParsable(textColor.getText())) {
            PrioGroup prioGroup = new PrioGroup(name.getText(), prio.getTextAsInteger(), color.getText(), textColor.getText());
            Application.getBean(PrioGroupService.class).save(prioGroup);
            prioGroup = Application.getBean(PrioGroupService.class).getPrioGroupByName(prioGroup.getName());
            setPrioGroups();
            prioGroups.setSelectedValue(prioGroup);
        }
    }

    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(new Rectangle((int) r.getX(), (int) r.getY(), 365, 500));
    }

    private enum Name {
        TITLE(10, 10, 100, 25),
        PRIO_GROUPS(10, 45, 150, 180),
        NAME_LABEL(170, 45, 25, 25),
        NAME(205, 45, 150, 25),
        PRIO_LABEL(170, 80, 30, 20),
        COLOR_LABEL(230, 80, 80, 20),
        TEXT_COLOR_LABEL(295, 80, 80, 20),
        PRIO(170, 102, 35, 25),
        COLOR(230, 102, 55, 25),
        TEXT_COLOR(295, 102, 55, 25),
        COLOR_SAMPLE(230, 129, 120, 25),
        OVERWRITE(170, 165, 150, 25),
        SAVE_NEW(170, 200, 150, 25),
        TAGS(10, 235, 345, 215);

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
