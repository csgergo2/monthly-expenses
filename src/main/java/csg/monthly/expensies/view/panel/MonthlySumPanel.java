package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.panel.MenuPanel.MENU_PANEL;
import static csg.monthly.expensies.view.util.Name.MONTHLY_SUM_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.MONTHLY_SUM_MAIN_LABEL;

import java.util.Collections;
import java.util.List;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.PrioGroup;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.date.MonthInfo;
import csg.monthly.expensies.domain.service.MonthInfoService;
import csg.monthly.expensies.domain.service.TagService;
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
        List<MonthInfo> monthInfo = Application.getBean(MonthInfoService.class).getMonthInfo();
        Collections.sort(monthInfo);
        String[] tableHeaders = getHeaders(monthInfo);
        List<Tag> tags = Application.getBean(TagService.class).findAllOrderedByPrioGroup();
        String[][] rows = getRows(tags, monthInfo);
        buildTable(tableHeaders, rows, tags);
    }

    private String[] getHeaders(List<MonthInfo> monthInfo) {
        String[] headers = new String[monthInfo.size() + 1];
        headers[0] = "";
        for (int i = 1; i <= monthInfo.size(); i++) {
            headers[i] = monthInfo.get(i - 1).getHtmlName();
        }
        return headers;
    }

    private String[][] getRows(List<Tag> tags, List<MonthInfo> monthInfo) {
        String[][] rows = new String[tags.size()][monthInfo.size() + 1];
        rows[0][0] = "";
        for (int i = 0; i < tags.size(); i++) {
            final Tag tag = tags.get(i);
            rows[i][0] = tag.getName();
            for (int j = 1; j <= monthInfo.size(); j++) {
                final MonthInfo month = monthInfo.get(j - 1);
                rows[i][j] =
                        month.getItems().containsKey(tag) ? Integer.toString(month.getItems().get(tag).stream().mapToInt(Item::getAmount).sum()) : "";
            }
        }
        return rows;
    }

    private void buildTable(String[] tableHeaders, String[][] rows, List<Tag> tags) {
        StringBuilder sb = new StringBuilder("<html><table style=\"border:1px solid black;\">");

        sb.append("<tr>");
        for (String header : tableHeaders) {
            sb.append("<th style=\"border:1px solid black;\">").append(header).append("</th>");
        }
        sb.append("</tr>");
        for (int i = 0; i < rows.length; i++) {
            final PrioGroup prioGroup = tags.get(i).getPrioGroup();
            String background = prioGroup == null ? "#FFFFFF" : prioGroup.getColor();
            String color = prioGroup == null ? "#000000" : prioGroup.getTextColor();
            sb.append("<tr style=\"background-color:").append(background).append(";color:").append(color).append(";\"");
            for (int j = 0; j < rows[i].length; j++) {
                sb.append("<td style=\"border:1px solid black;\">").append(rows[i][j]).append("</td>");
            }
            sb.append("</tr>");

        }
        table.setText(sb.append("</table>").append("</html>").toString());
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
