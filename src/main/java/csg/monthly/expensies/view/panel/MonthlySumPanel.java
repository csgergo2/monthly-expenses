package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.panel.MenuPanel.MENU_PANEL;
import static csg.monthly.expensies.view.util.Name.MONTHLY_SUM_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.MONTHLY_SUM_MAIN_LABEL;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.service.MonthlySumService;
import csg.monthly.expensies.view.util.MELayout;
import csg.monthly.expensies.view.util.Name;
import csg.swing.CsGButton;
import csg.swing.CsGPanel;
import csg.swing.CsGScrollableLabel;

public class MonthlySumPanel extends CsGPanel {

    public static final MonthlySumPanel MONTHLY_SUM_PANEL = new MonthlySumPanel();

    private CsGScrollableLabel table = new CsGScrollableLabel(MONTHLY_SUM_MAIN_LABEL, "");

    private MonthlySumPanel() {
        super(Name.MONTHLY_SUM_PANEL, MELayout.LAYOUT);

        add(new CsGButton(MONTHLY_SUM_BACK_BUTTON, "Vissza", event -> back()));//todo english
        add(table);
    }

    private void calculateTable() {
        table.setText(Application.getBean(MonthlySumService.class).getTable());
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            calculateTable();
        }
        super.setVisible(visible);
    }

    private void back() {
        setVisible(false);
        MENU_PANEL.setVisible(true);
    }
}
