package csg.monthly.expensies.view.panel;

import java.awt.Rectangle;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.service.MonthlySumService;
import csg.swing.CsGLayout;
import csg.swing.CsGPanel;
import csg.swing.CsGScrollableLabel;

public class MonthlySumPanel extends CsGPanel {

    public static final MonthlySumPanel MONTHLY_SUM_PANEL = new MonthlySumPanel();

    private CsGScrollableLabel table = new CsGScrollableLabel(Name.MONTHLY_SUM_MAIN_LABEL, "");

    private MonthlySumPanel() {
        super(MenuPanel.Name.MONTHLY_SUM_PANEL, (CsGLayout) name -> Name.valueOf(name).getRectangle());

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

    private enum Name {
        MONTHLY_SUM_MAIN_LABEL(10, 10, 1000, 500);

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
}
