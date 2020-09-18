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
import csg.monthly.expensies.view.util.MELayout;
import csg.swing.CsGButton;
import csg.swing.CsGLabel;
import csg.swing.CsGPanel;
import csg.swing.CsGTextField;

//import csg.monthly.expensies.view.util.MEHtmlBuilder;

//import csg.monthly.expensies.view.util.MEScrollableLabel;

public class NewTagPanel extends CsGPanel {
    public static final NewTagPanel NEW_TAG_PANEL = new NewTagPanel();

    private CsGTextField tagName = new CsGTextField(TAG_NAME);
    private CsGTextField tagPrio = new CsGTextField(TAG_PRIO, true);
    //    private MEScrollableLabel tagTags = new MEScrollableLabel(TAG_LIST_LABEL, "");

    private NewTagPanel() {
        super(TAG_PANEL, MELayout.LAYOUT);

        add(tagName);
        add(new CsGLabel(TAG_NAME_LABEL, "Név:"));
        add(tagPrio);
        add(new CsGLabel(TAG_PRIO_LABEL, "Prio:"));

        add(new CsGButton(TAG_SAVE_BUTTON, "Mentés", this::saveTag));//todo english
        add(new CsGButton(TAG_BACK_BUTTON, "Vissza", this::backToMenuPanel));//todo english
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