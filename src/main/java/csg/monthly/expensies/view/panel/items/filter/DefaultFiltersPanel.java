package csg.monthly.expensies.view.panel.items.filter;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.date.Month;
import csg.monthly.expensies.domain.service.ItemService;
import csg.swing.CsGButton;
import csg.swing.CsGComboBox;
import csg.swing.CsGLabel;
import csg.swing.CsGLayout;
import csg.swing.CsGPanel;
import csg.swing.CsGTextField;

public class DefaultFiltersPanel extends CsGPanel {

    private final CsGComboBox<String> year = new CsGComboBox<>(Name.YEAR_SELECTOR);
    private final CsGComboBox<String> month = new CsGComboBox<>(Name.MONTH_SELECTOR);
    private final CsGTextField startDate = new CsGTextField(Name.DATE_START);
    private final CsGTextField endDate = new CsGTextField(Name.DATE_END);
    private final CsGTextField name = new CsGTextField(Name.NAME);

    public DefaultFiltersPanel(final Enum<?> panelName, final ActionListener filterAction) {
        super(panelName, new FiltersPanelLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));

        add(new CsGLabel(Name.TITLE, "Item szűrő:"));//todo english

        add(new CsGLabel(Name.YEAR_SELECTOR_LABEL, "Év:"));//todo english
        final List<String> years = Collections.singletonList("");
        year.reset(years);
        add(year);

        add(new CsGLabel(Name.MONTH_SELECTOR_LABEL, "Hó:"));//todo english
        List<String> months = Collections.singletonList("");
        month.reset(months);
        add(month);

        add(new CsGLabel(Name.DATE_START_LABEL, "Kezdő dátum:"));//todo english
        startDate.setText(LocalDate.now().minusYears(1).toString());
        add(startDate);

        add(new CsGLabel(Name.DATE_END_LABEL, "Vég dátum:"));//todo english
        endDate.setText(LocalDate.now().toString());
        add(endDate);

        add(new CsGLabel(Name.NAME_LABEL, "Név:"));//todo english
        add(name);

        add(new CsGButton(Name.FILTER_BUTTON, "Szűrés", filterAction));//todo english
        add(new CsGButton(Name.RESET_BUTTON, "Reset", this::resetFilters));//todo english
    }

    public String getYear() {
        return (String) year.getSelectedItem();
    }

    public String getMonth() {
        return (String) month.getSelectedItem();
    }

    public String getStartDate() {
        return startDate.getText();
    }

    public String getEndDate() {
        return endDate.getText();
    }

    public String getNameFilter() {
        return name.getText();
    }

    private void setYearSelector() {
        final String selectedYear = (String) year.getSelectedItem();
        final List<String> years =
                Application.getBean(ItemService.class).findAllYear().stream().map(year -> Integer.toString(year)).collect(Collectors.toList());
        years.add("");
        year.reset(years);
        year.setSelectedItem(selectedYear);
    }

    private void setMonthSelector() {
        String selectedMonth = (String) month.getSelectedItem();
        final List<String> months = Arrays.stream(Month.values()).map(Month::name).collect(Collectors.toList());
        months.add(0, "");
        month.reset(months);
        month.setSelectedItem(selectedMonth);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            setYearSelector();
            setMonthSelector();
        }
        super.setVisible(visible);
    }

    protected void resetFilters(ActionEvent event) {
        final List<String> years = Collections.singletonList("");
        year.reset(years);
        List<String> months = Collections.singletonList("");
        month.reset(months);
        startDate.setText(LocalDate.now().minusYears(1).toString());
        endDate.setText(LocalDate.now().toString());
        name.setText("");
        setVisible(true);
    }

    @Override
    public void setBounds(Rectangle r) {
        super.setBounds(new Rectangle((int) r.getX(), (int) r.getY(), 230, 325));
    }

    protected enum Name {
        TITLE(10, 10, 100, 25),
        FILTER_BUTTON(10, 45, 100, 25),
        RESET_BUTTON(120, 45, 100, 25),
        YEAR_SELECTOR_LABEL(10, 80, 25, 25),
        YEAR_SELECTOR(45, 80, 100, 25),
        MONTH_SELECTOR_LABEL(10, 115, 25, 25),
        MONTH_SELECTOR(45, 115, 100, 25),
        DATE_START_LABEL(10, 150, 80, 25),
        DATE_START(100, 150, 80, 25),
        DATE_END_LABEL(10, 185, 65, 25),
        DATE_END(100, 185, 80, 25),
        NAME_LABEL(10, 220, 25, 25),
        NAME(45, 220, 175, 25),
        TAG_LABEL(10, 255, 25, 25),
        TAG(45, 255, 100, 25),
        INCOME_LABEL(10, 290, 50, 25),
        INCOME(70, 290, 25, 25);

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

    private static class FiltersPanelLayout implements CsGLayout {
        @Override
        public Rectangle getRectangleForComponentName(final String name) {
            return Name.valueOf(name).getRectangle();
        }
    }

}
