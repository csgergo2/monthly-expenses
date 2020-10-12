package csg.monthly.expensies.domain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.date.Month;
import csg.monthly.expensies.domain.date.MonthDate;
import csg.monthly.expensies.domain.date.MonthInfo;
import csg.monthly.expensies.domain.repository.ItemRepository;

@Service
public class MonthInfoService {

    @Autowired
    private ItemRepository itemRepository;

    public List<MonthInfo> getMonthInfo() {
        final Map<Integer, Map<Month, Map<Tag, List<Item>>>> rawMonthInfo = getRawMonthInfo();
        return agregateRawMonthInfo(rawMonthInfo);
    }

    private List<MonthInfo> agregateRawMonthInfo(Map<Integer, Map<Month, Map<Tag, List<Item>>>> rawMonthInfo) {
        List<MonthInfo> monthInfo = new ArrayList<>();
        for (Integer year : rawMonthInfo.keySet()) {
            for (Month month : rawMonthInfo.get(year).keySet()) {
                monthInfo.add(new MonthInfo(new MonthDate(month, year), rawMonthInfo.get(year).get(month)));
            }
        }
        return monthInfo;
    }

    private Map<Integer, Map<Month, Map<Tag, List<Item>>>> getRawMonthInfo() {
        Map<Integer, Map<Month, Map<Tag, List<Item>>>> monthInfo = new HashMap<>();
        itemRepository.findAll().forEach(item -> setRawYearInfo(monthInfo, item));
        return monthInfo;
    }

    private void setRawYearInfo(Map<Integer, Map<Month, Map<Tag, List<Item>>>> monthInfo, Item item) {
        if (monthInfo.containsKey(item.getYear())) {
            setRawMonthInfo(monthInfo.get(item.getYear()), item);
        } else {
            Map<Tag, List<Item>> tag = new HashMap<>();
            List<Item> items = new ArrayList<>();
            items.add(item);
            tag.put(item.getTag(), items);
            Map<Month, Map<Tag, List<Item>>> month = new HashMap<>();
            month.put(item.getMonth(), tag);
            monthInfo.put(item.getYear(), month);
        }
    }

    private void setRawMonthInfo(Map<Month, Map<Tag, List<Item>>> year, Item item) {
        if (year.containsKey(item.getMonth())) {
            setRawTagInfo(year.get(item.getMonth()), item);
        } else {
            Map<Tag, List<Item>> tag = new HashMap<>();
            List<Item> items = new ArrayList<>();
            items.add(item);
            tag.put(item.getTag(), items);
            year.put(item.getMonth(), tag);
        }
    }

    private void setRawTagInfo(Map<Tag, List<Item>> month, Item item) {
        if (month.containsKey(item.getTag())) {
            month.get(item.getTag()).add(item);
        } else {
            List<Item> items = new ArrayList<>();
            items.add(item);
            month.put(item.getTag(), items);
        }
    }
}
