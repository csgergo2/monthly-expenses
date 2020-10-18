package csg.monthly.expensies.view.panel;

import static csg.monthly.expensies.view.panel.MenuPanel.MENU_PANEL;
import static csg.monthly.expensies.view.util.Name.MONTHLY_SUM_BACK_BUTTON;
import static csg.monthly.expensies.view.util.Name.MONTHLY_SUM_MAIN_LABEL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import csg.monthly.expensies.Application;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.PrioGroup;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.date.MonthInfo;
import csg.monthly.expensies.domain.service.MonthInfoService;
import csg.monthly.expensies.domain.service.PrioGroupService;
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
        buildTable(tableHeaders, rows);
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
        if (tags.isEmpty()) {
            return new String[][]{};
        }
        Map<PrioGroup, List<Tag>> prioGroups = new TreeMap<>();
        List<Tag> noPrioGroup = new ArrayList<>();
        for (Tag tag : tags) {
            if (tag.getPrioGroup() == null) {
                noPrioGroup.add(tag);
            } else if (prioGroups.containsKey(tag.getPrioGroup())) {
                prioGroups.get(tag.getPrioGroup()).add(tag);
            } else {
                List<Tag> newTagList = new ArrayList<>();
                newTagList.add(tag);
                prioGroups.put(tag.getPrioGroup(), newTagList);
            }
        }
        prioGroups.put(new PrioGroup("-", 10000000, "#FFFFFF", "#000000"), noPrioGroup);
        final List<String[][]> collectedRows =
                prioGroups.entrySet().stream().map(entry -> getRowsByPrioGroup(entry.getKey(), entry.getValue(), monthInfo))
                          .collect(Collectors.toList());
        int allRows = collectedRows.stream().mapToInt(row -> row.length).sum() + 1;
        String[][] rows = new String[allRows][monthInfo.size() + 1];
        int j = 1;
        for (int i = 0; i < collectedRows.size(); i++) {
            for (int k = 0; k < collectedRows.get(i).length; k++) {
                rows[j++] = collectedRows.get(i)[k];
            }
        }
        rows[0][0] = "Össz";
        for (int i = 1; i <= monthInfo.size(); i++) {
            final MonthInfo month = monthInfo.get(i - 1);
            final String sum =
                    Integer.toString(month.getItems().values().stream().mapToInt(items -> items.stream().mapToInt(Item::getAmount).sum()).sum());
            rows[0][i] = sum;
        }
        return rows;
    }

    private String[][] getRowsByPrioGroup(PrioGroup prioGroup, List<Tag> tagsOfPrioGroup, List<MonthInfo> monthInfo) {
        final String[][] rows = new String[tagsOfPrioGroup.size() + 2][monthInfo.size() + 1];
        rows[0][0] = prioGroup != null ? prioGroup.getName() : "";
        for (int i = 1; i <= monthInfo.size(); i++) {
            rows[0][i] = "";
        }
        for (int i = 1; i <= tagsOfPrioGroup.size(); i++) {
            rows[i][0] = tagsOfPrioGroup.get(i - 1).getName();
            for (int j = 1; j <= monthInfo.size(); j++) {
                final Optional<List<Item>> items = Optional.ofNullable(monthInfo.get(j - 1).getItems().get(tagsOfPrioGroup.get(i - 1)));
                rows[i][j] = items.map(items1 -> Integer.toString(items1.stream().mapToInt(Item::getAmount).sum())).orElse("");
            }
        }
        rows[tagsOfPrioGroup.size() + 1][0] = "";
        for (int i = 1; i <= monthInfo.size(); i++) {
            final MonthInfo month = monthInfo.get(i - 1);
            final List<List<Item>> collectedItems = tagsOfPrioGroup.stream().map(tag -> month.getItems().get(tag)).collect(Collectors.toList());
            int sum = 0;
            for (List<Item> items : collectedItems) {
                if (items != null) {
                    sum += items.stream().mapToInt(Item::getAmount).sum();
                }
            }
            final String monthSum = Integer.toString(sum);
            rows[tagsOfPrioGroup.size() + 1][i] = monthSum;
        }
        return rows;
    }

    private void buildTable(String[] tableHeaders, String[][] rows) {
        StringBuilder sb = new StringBuilder("<html><table style=\"border:1px solid black;\">");

        sb.append("<tr>");
        for (String header : tableHeaders) {
            sb.append("<th style=\"border:1px solid black;\">").append(header).append("</th>");
        }
        sb.append("</tr>");
        String background = "#FFFFFF";
        String color = "#000000";
        for (int i = 0; i < rows.length; i++) {
            final Optional<PrioGroup> prioGroup = Application.getBean(PrioGroupService.class).getPrioGroupByName(rows[i][0]);
            background = prioGroup.isPresent() ? prioGroup.get().getColor() : background;
            color = prioGroup.isPresent() ? prioGroup.get().getTextColor() : color;
            sb.append("<tr style=\"background-color:").append(background).append(";color:").append(color).append(";\"");
            for (int j = 0; j < rows[i].length; j++) {
                sb.append("<td style=\"border:1px solid black;\">").append(rows[i][j]).append("</td>");
            }
            sb.append("</tr>");
            if (rows[i][0] == null || rows[i][0].isEmpty()) {
                background = "#FFFFFF";
                color = "#000000";
            }
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
