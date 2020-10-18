package csg.monthly.expensies.view;

import static csg.monthly.expensies.view.util.Name.CUSTOM_COUNTER;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.CustomCounter;
import csg.monthly.expensies.domain.service.CustomCounterServcie;
import csg.monthly.expensies.view.panel.MenuPanel;
import csg.swing.CsGButton;
import csg.swing.CsGLayout;
import csg.swing.CsGListBox;
import csg.swing.CsGPanel;
import csg.swing.CsGTextArea;
import csg.swing.CsGTextField;

public class CustomCounterPanel extends CsGPanel {
    public static final CustomCounterPanel CUSTOM_COUNTER_PANEL = new CustomCounterPanel();

    private CsGListBox<CustomCounter> customCounters = new CsGListBox<>(Name.CUSTOM_COUNTERS, event -> selectCustomCounter());
    private CsGTextField name = new CsGTextField(Name.NAME);
    private CsGTextArea text = new CsGTextArea(Name.TEXT);

    private CustomCounterPanel() {
        super(CUSTOM_COUNTER, new CustomCounterPanelLayout());

        add(customCounters);
        add(name);
        add(new CsGButton(Name.OVERWRITE_BUTTON, "Felül ír", event -> overwrite()));
        add(new CsGButton(Name.SAVE_NEW_BUTTON, "Új mentés", event -> saveNew()));
        add(new CsGButton(Name.CUSTOM_COUNTER_BACK_BUTTON, "Vissza", event -> back()));
        add(text);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            setUpCustomCounters();
        }
        super.setVisible(visible);
    }

    private void setUpCustomCounters() {
        Optional<CustomCounter> selectedValue = customCounters.getSelectedValue();
        final CustomCounter nul = new CustomCounter("", "");
        List<CustomCounter> customCounterList = new ArrayList<>();
        customCounterList.add(nul);
        customCounterList.addAll(Application.getBean(CustomCounterServcie.class).findAll());
        customCounters.reset(customCounterList);
        selectedValue.ifPresent(customCounter -> customCounters.setSelectedValue(customCounter));
    }

    private void selectCustomCounter() {
        if (!customCounters.getSelectedValue().isPresent() || customCounters.getSelectedValue().get().getName().isEmpty()) {
            return;
        }
        final CustomCounter customCounter = customCounters.getSelectedValue().get();
        name.setText(customCounter.getName());
        text.setText(customCounter.getData());
    }

    private void overwrite() {
        if (!customCounters.getSelectedValue().isPresent() || customCounters.getSelectedValue().get().getName().isEmpty() ||
                name.getText().isEmpty()) {
            return;
        }
        final CustomCounter customCounter = customCounters.getSelectedValue().get();
        customCounter.setName(name.getText());
        customCounter.setData(text.getText());
        Application.getBean(CustomCounterServcie.class).save(customCounter);
        setUpCustomCounters();
    }

    private void saveNew() {
        if (name.getText().isEmpty()) {
            return;
        }
        CustomCounter customCounter = new CustomCounter(name.getText(), text.getText());
        Application.getBean(CustomCounterServcie.class).save(customCounter);
        final Optional<CustomCounter> created = Application.getBean(CustomCounterServcie.class).findByName(name.getText());
        setUpCustomCounters();
        customCounters.setSelectedValue(created.get());
    }

    private void back() {
        setVisible(false);
        MenuPanel.MENU_PANEL.setVisible(true);
    }

    private enum Name {
        CUSTOM_COUNTERS(10, 10, 150, 130),
        NAME(170, 10, 150, 25),
        OVERWRITE_BUTTON(170, 45, 150, 25),
        SAVE_NEW_BUTTON(170, 80, 150, 25),
        CUSTOM_COUNTER_BACK_BUTTON(170, 115, 150, 25),
        TEXT(10, 150, 500, 500);

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

    private static class CustomCounterPanelLayout implements CsGLayout {
        @Override
        public Rectangle getRectangleForComponentName(final String name) {
            return Name.valueOf(name).getRectangle();
        }
    }
}
