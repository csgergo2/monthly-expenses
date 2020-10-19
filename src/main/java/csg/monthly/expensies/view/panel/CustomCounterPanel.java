package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.util.Name.CUSTOM_COUNTER;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.CustomCounter;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.service.CustomCounterServcie;
import csg.monthly.expensies.domain.service.ItemService;
import csg.monthly.expensies.view.panel.items.ItemsTablePanel;
import csg.monthly.expensies.view.panel.items.TableItem;
import csg.swing.CsGButton;
import csg.swing.CsGLabel;
import csg.swing.CsGLayout;
import csg.swing.CsGListBox;
import csg.swing.CsGPanel;
import csg.swing.CsGTextArea;
import csg.swing.CsGTextField;

public class CustomCounterPanel extends CsGPanel {
    public static final CustomCounterPanel CUSTOM_COUNTER_PANEL = new CustomCounterPanel();

    private static CsGTextArea getTextTextArea() {
        CsGTextArea text = new CsGTextArea(Name.TEXT);
        text.setBorder(BorderFactory.createLineBorder(Color.black));
        return text;
    }

    private CsGListBox<CustomCounter> customCounters = new CsGListBox<>(Name.CUSTOM_COUNTERS, event -> selectCustomCounter());
    private CsGTextField name = new CsGTextField(Name.NAME);
    private CsGTextArea text = getTextTextArea();
    private ItemsTablePanel items = new ItemsTablePanel(Name.ITEMS);
    private CsGLabel sum = new CsGLabel(Name.SUM, "");

    private CustomCounterPanel() {
        super(CUSTOM_COUNTER, new CustomCounterPanelLayout());

        add(customCounters);
        add(name);
        add(new CsGButton(Name.OVERWRITE_BUTTON, "Felül ír", event -> overwrite()));//todo english
        add(new CsGButton(Name.SAVE_NEW_BUTTON, "Új mentés", event -> saveNew()));//todo english
        add(new CsGButton(Name.CUSTOM_COUNTER_BACK_BUTTON, "Vissza", event -> back()));//todo english
        add(new CsGButton(Name.DELETE_CUSTOM_COUNTER_BUTTON, "Törlés", event -> delete()));//todo english
        add(text);
        add(items);
        add(sum);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            setUpCustomCounters();
            setUpItems();
        }
        super.setVisible(visible);
    }

    private void setUpCustomCounters() {
        final CustomCounter nul = new CustomCounter("", "");
        List<CustomCounter> customCounterList = new ArrayList<>();
        customCounterList.add(nul);
        customCounterList.addAll(Application.getBean(CustomCounterServcie.class).findAll());
        customCounters.reset(customCounterList);
    }

    private void selectCustomCounter() {
        if (!customCounters.getSelectedValue().isPresent() || customCounters.getSelectedValue().get().getName().isEmpty()) {
            return;
        }
        final CustomCounter customCounter = customCounters.getSelectedValue().get();
        name.setText(customCounter.getName());
        text.setText(customCounter.getData());
        setUpItems();
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
        setUpItems();
    }

    private void back() {
        setVisible(false);
        MenuPanel.MENU_PANEL.setVisible(true);
    }

    private void setUpItems() {
        if (items != null) {
            items.setVisible(false);
            items.setEnabled(false);
            remove(items);
        }
        final Optional<CustomCounter> selectedCustomCounter = customCounters.getSelectedValue();
        items = new ItemsTablePanel(Name.ITEMS);
        if (selectedCustomCounter.isPresent() && !selectedCustomCounter.get().getName().isEmpty()) {
            final List<Item> itemsOfCustomCounter = Application.getBean(ItemService.class).findAllByCustomCounter(selectedCustomCounter.get());
            for (Item item : itemsOfCustomCounter) {
                items.add(TableItem.of(item));
            }
            items.setScrollBarToBottom();
            if (itemsOfCustomCounter.size() > 0) {
                final String sum = Integer.toString(itemsOfCustomCounter.stream().mapToInt(Item::getAmount).sum());
                final String avg = Integer.toString((int) itemsOfCustomCounter.stream().mapToInt(Item::getAmount).average().getAsDouble());
                this.sum.setText("Összesen: " + sum + ";     Átlag: " + avg);
            }
        }
        add(items);
    }

    private void delete() {
        if (!customCounters.getSelectedValue().isPresent() || customCounters.getSelectedValue().get().getName().isEmpty()) {
            return;
        }
        final CustomCounter selectedCustomCounter = customCounters.getSelectedValue().get();
        final boolean isNoItemForCustomCounter = Application.getBean(ItemService.class).findAllByCustomCounter(selectedCustomCounter).isEmpty();
        if (selectedCustomCounter.getData().isEmpty() && isNoItemForCustomCounter) {
            Application.getBean(CustomCounterServcie.class).delete(selectedCustomCounter);
            name.setText("");
            text.setText("");
            setUpCustomCounters();
        }
    }

    private enum Name {
        CUSTOM_COUNTERS(10, 10, 150, 130),
        NAME(170, 10, 150, 25),
        OVERWRITE_BUTTON(170, 45, 150, 25),
        SAVE_NEW_BUTTON(170, 80, 150, 25),
        DELETE_CUSTOM_COUNTER_BUTTON(170, 115, 150, 25),
        CUSTOM_COUNTER_BACK_BUTTON(330, 10, 150, 25),
        TEXT(10, 150, 690, 500),
        ITEMS(710, 150, 1070, 500),
        SUM(710, 660, 500, 25);

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
