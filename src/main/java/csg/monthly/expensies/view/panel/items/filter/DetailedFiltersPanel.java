package csg.monthly.expensies.view.panel.items.filter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.service.TagService;
import csg.swing.CsGCheckBox;
import csg.swing.CsGComboBox;
import csg.swing.CsGLabel;

public class DetailedFiltersPanel extends DefaultFiltersPanel {

    private final CsGComboBox<Tag> tag = new CsGComboBox<>(Name.TAG);
    private final CsGCheckBox isIncome = new CsGCheckBox(Name.INCOME);


    public DetailedFiltersPanel(final Enum<?> panelName, final ActionListener filterAction) {
        super(panelName, filterAction);

        add(new CsGLabel(Name.TAG_LABEL, "Tag:"));//todo english
        add(tag);

        add(new CsGLabel(Name.INCOME_LABEL, "Bevétel:"));//todo english
        add(isIncome);
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            setTagSelector();
        }
        super.setVisible(visible);
    }

    private void setTagSelector() {
        Tag selectedTag = (Tag) tag.getSelectedItem();
        final List<Tag> tags = Application.getBean(TagService.class).findAll();
        tags.add(new Tag());
        tag.reset(tags);
        tag.setSelectedItem(selectedTag);
    }

    public Tag getTag() {
        return (Tag) tag.getSelectedItem();
    }

    public boolean getIsIncome() {
        return isIncome.isSelected();
    }

    @Override
    protected void resetFilters(ActionEvent event) {
        final List<Tag> tags = Collections.singletonList(new Tag());
        tag.reset(tags);
        setTagSelector();
        isIncome.setSelected(false);
        super.resetFilters(event);
    }
}
