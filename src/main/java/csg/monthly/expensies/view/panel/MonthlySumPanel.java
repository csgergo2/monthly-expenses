package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.panel.MenuPanel.MENU_PANEL;

import java.awt.Rectangle;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.service.MonthlySumService;
import csg.swing.CsGButton;
import csg.swing.CsGLayout;
import csg.swing.CsGPanel;
import csg.swing.CsGScrollableLabel;

public class MonthlySumPanel extends CsGPanel {

    public static final MonthlySumPanel MONTHLY_SUM_PANEL = new MonthlySumPanel();

    private CsGScrollableLabel table = new CsGScrollableLabel(Name.MONTHLY_SUM_MAIN_LABEL, "");

    private MonthlySumPanel() {
        super(csg.monthly.expensies.view.util.Name.MONTHLY_SUM_PANEL, (CsGLayout) name -> Name.valueOf(name).getRectangle());

        add(new CsGButton(Name.MONTHLY_SUM_BACK_BUTTON, "Vissza", event -> back()));//todo english
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

    private enum Name {
        MONTHLY_SUM_BACK_BUTTON(0, 0, 100, 25),
        MONTHLY_SUM_TAGS_LABEL(0, 25, 150, 485),
        MONTHLY_SUM_MAIN_LABEL(MONTHLY_SUM_TAGS_LABEL.x + MONTHLY_SUM_TAGS_LABEL.width, MONTHLY_SUM_TAGS_LABEL.y, 1000, 500);

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
