package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.panel.MenuPanel.MENU_PANEL;
import static csg.monthly.expensies.view.util.Name.MONTHLY_SUM_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.MONTHLY_SUM_MAIN_LABEL;
import static csg.monthly.expensies.view.util.Name.MONTHLY_SUM_TAGS_LABEL;

import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.util.Random;

import javax.swing.JScrollBar;

import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGPanel;
import csg.swing.CsGScrollableLabel;

public class MonthlySumPanel extends CsGPanel {

    public static final MonthlySumPanel MONTHLY_SUM_PANEL = new MonthlySumPanel();

    private CsGScrollableLabel tags = new CsGScrollableLabel(MONTHLY_SUM_TAGS_LABEL, "");
    private CsGScrollableLabel table = new CsGScrollableLabel(MONTHLY_SUM_MAIN_LABEL, "");

    private MonthlySumPanel() {
        super(Name.MONTHLY_SUM_PANEL, MELayout.LAYOUT);

        add(new CsGButton(MONTHLY_SUM_BACK_BUTTON, "Vissza", this::back));//todo english

        StringBuilder sb = new StringBuilder("<html>");
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                sb.append(new Random().nextInt());
            }
            sb.append("<br/>");
        }
        sb.append("</html>");
        table.setText(sb.toString());
        add(table);

        sb = new StringBuilder("<html>");
        for (int i = 0; i < 100; i++) {
            sb.append(new Random().nextInt());
            sb.append("<br/>");
        }
        sb.append("</html>");
        tags.setText(sb.toString());
        tags.getVerticalScrollBar().addAdjustmentListener(this::changeTagsScroll);
        add(tags);
    }

    private void back(ActionEvent event) {
        setVisible(false);
        MENU_PANEL.setVisible(true);
    }

    private void changeTagsScroll(AdjustmentEvent event) {
        if (!event.getValueIsAdjusting()) {
            JScrollBar source = (JScrollBar) event.getAdjustable();
            table.getVerticalScrollBar().getModel().setValue(source.getModel().getValue());
        }
    }
}
