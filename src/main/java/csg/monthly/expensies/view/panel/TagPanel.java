package csg.monthly.expensies.view.panel;

import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGPanel;

public class TagPanel extends CsGPanel {
    public static final TagPanel TAG_PANEL = new TagPanel();

    private TagPanel() {
        super(Name.TAG_PANEL, MELayout.LAYOUT);
    }
}
