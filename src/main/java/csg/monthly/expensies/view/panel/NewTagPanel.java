package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.util.Name.TAG_LIST_LABEL;
import static csg.monthly.expensies.view.util.Name.TAG_NAME;
import static csg.monthly.expensies.view.util.Name.TAG_NAME_LABEL;
import static csg.monthly.expensies.view.util.Name.TAG_NEW_TAG_PANEL;
import static csg.monthly.expensies.view.util.Name.TAG_PRIO;
import static csg.monthly.expensies.view.util.Name.TAG_PRIO_LABEL;
import static csg.monthly.expensies.view.util.Name.TAG_SAVE_BUTTON;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.service.TagService;
import csg.monthly.expensies.exception.MonthlyExpensesException;
import csg.monthly.expensies.view.util.MELayout;
import csg.swing.CsGButton;
import csg.swing.CsGLabel;
import csg.swing.CsGPanel;
import csg.swing.CsGScrollableLabel;
import csg.swing.CsGTextField;
import csg.swing.html.CsGHtmlBodyBuilder;
import csg.swing.html.CsGHtmlBuilder;

public class NewTagPanel extends CsGPanel {
    public static final NewTagPanel NEW_TAG_PANEL = new NewTagPanel();

    private CsGTextField tagName = new CsGTextField(TAG_NAME);
    private CsGTextField tagPrio = new CsGTextField(TAG_PRIO, true);
    private CsGScrollableLabel tagTags = new CsGScrollableLabel(TAG_LIST_LABEL, "");

    private NewTagPanel() {
        super(TAG_NEW_TAG_PANEL, MELayout.LAYOUT);
        setBorder(BorderFactory.createLineBorder(Color.black));

        add(tagName);
        add(new CsGLabel(TAG_NAME_LABEL, "Tag név:"));
        add(tagPrio);
        add(new CsGLabel(TAG_PRIO_LABEL, "Tag prio:"));

        add(new CsGButton(TAG_SAVE_BUTTON, "Tag Mentés", this::saveTag));//todo english

        add(tagTags);
    }

    public void refresh() {
        tagTags.setText(listTagsSeparatedByLines());
        setVisible(true);
    }

    private void saveTag(ActionEvent event) {
        if (tagName.getText().isEmpty() || tagPrio.getText().isEmpty()) {
            throw new MonthlyExpensesException("Empty value via creating tag; name: " + tagName.getText() + "; prio: " + tagPrio.getText());
        }
        final Tag tag = new Tag();
        tag.setName(tagName.getText());
        tag.setPrio(Integer.valueOf(tagPrio.getText()));
        //todo set prio group
        Application.getBean(TagService.class).save(tag);
        tagName.setText("");
        tagPrio.setText("");
        tagTags.setText(listTagsSeparatedByLines());
    }

    private String listTagsSeparatedByLines() {
        final CsGHtmlBodyBuilder builder = new CsGHtmlBodyBuilder();
        Application.getBean(TagService.class).findAll().forEach(tag -> builder.addText("p", tag.getName()));
        return new CsGHtmlBuilder(builder).build();
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            tagTags.setText(listTagsSeparatedByLines());
        }
        super.setVisible(visible);
    }
}
