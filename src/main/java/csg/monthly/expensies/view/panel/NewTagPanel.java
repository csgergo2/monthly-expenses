package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.util.Name.TAG_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.TAG_NAME;
import static csg.monthly.expensies.view.util.Name.TAG_NAME_LABEL;
import static csg.monthly.expensies.view.util.Name.TAG_PANEL;
import static csg.monthly.expensies.view.util.Name.TAG_PRIO;
import static csg.monthly.expensies.view.util.Name.TAG_PRIO_LABEL;
import static csg.monthly.expensies.view.util.Name.TAG_SAVE_BUTTON;

import java.awt.event.ActionEvent;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.repository.TagRepository;
import csg.monthly.expensies.exception.MonthlyExpensesException;
import csg.monthly.expensies.view.util.MEButton;
import csg.monthly.expensies.view.util.MELabel;
import csg.monthly.expensies.view.util.MEPanel;
import csg.monthly.expensies.view.util.METextField;

//import csg.monthly.expensies.view.util.MEHtmlBuilder;

//import csg.monthly.expensies.view.util.MEScrollableLabel;

public class NewTagPanel extends MEPanel {
    public static final NewTagPanel NEW_TAG_PANEL = new NewTagPanel();

    private METextField tagName = new METextField(TAG_NAME);
    private METextField tagPrio = new METextField(TAG_PRIO, true);
    //    private MEScrollableLabel tagTags = new MEScrollableLabel(TAG_LIST_LABEL, "");

    private NewTagPanel() {
        super(TAG_PANEL);

        add(tagName);
        add(new MELabel(TAG_NAME_LABEL, "Név:"));
        add(tagPrio);
        add(new MELabel(TAG_PRIO_LABEL, "Prio:"));

        add(new MEButton(TAG_SAVE_BUTTON, "Mentés", this::saveTag));//todo english
        add(new MEButton(TAG_BACK_BUTTON, "Vissza", this::backToMenuPanel));//todo english
    }

    private void saveTag(ActionEvent event) {
        if (tagName.getText().isEmpty() || tagPrio.getText().isEmpty()) {
            throw new MonthlyExpensesException("Empty value via creating tag; name: " + tagName.getText() + "; prio: " + tagPrio.getText());
        }
        final Tag tag = new Tag(tagName.getText(), Integer.valueOf(tagPrio.getText()));
        Application.getApplicationContext().getBean(TagRepository.class).save(tag);
        tagName.setText("");
        tagPrio.setText("");
    }

    //    private String listTagsSeparatedByLines() {
    //        final MEHtmlBuilder builder = new MEHtmlBuilder();
    //        builder.listToLines(Application.getApplicationContext().getBean(TagRepository.class).findAll());
    //        return builder.build();
    //    }

    private void backToMenuPanel(ActionEvent event) {
        setVisible(false);
        MenuPanel.MENU_PANEL.setVisible(true);
    }

    @Override
    public void setVisible(boolean visible) {
        //        if (visible) {
        //            tagTags.setText(listTagsSeparatedByLines());
        //        }
        super.setVisible(visible);
    }
}
