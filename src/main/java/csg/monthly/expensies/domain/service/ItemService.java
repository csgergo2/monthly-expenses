package csg.monthly.expensies.domain.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.MonthComment;
import csg.monthly.expensies.domain.Tag;
import csg.monthly.expensies.domain.date.Month;
import csg.monthly.expensies.domain.repository.ItemRepository;
import csg.monthly.expensies.domain.repository.MonthCommentRepository;
import csg.monthly.expensies.view.util.DateParser;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private MonthCommentRepository monthCommentRepository;

    public void save(final Item item) {
        itemRepository.save(item);
    }

    public List<Item> findAllByYearAndMonth(@Param("year") int year, @Param("month") Month month) {
        return itemRepository.findAllByYearAndMonth(year, month);
    }

    public List<Integer> findAllYear() {
        return itemRepository.findAllYear();
    }

    public void deleteItem(Item item) {
        itemRepository.delete(item);
    }

    public Optional<MonthComment> getComment(Month month) {
        return Optional.ofNullable(monthCommentRepository.findByMonth(month));
    }

    @Transactional
    public void saveComment(Month month, String comment) {
        monthCommentRepository.deleteByMonth(month);
        if (comment != null && !comment.isEmpty()) {
            monthCommentRepository.save(new MonthComment(month, comment));
        }
    }

    public List<Item> findAllByFilter(String yearFilter, String nameFilter, Tag tag, boolean isIncome, String rawStartDate, String rawEndDate) {
        Integer year = yearFilter == null || yearFilter.isEmpty() ? null : Integer.valueOf(yearFilter);
        String name = nameFilter == null || nameFilter.isEmpty() ? null : nameFilter;
        Integer tagId = tag.getId();
        Date startDate = rawStartDate == null || rawStartDate.isEmpty() ? null : DateParser.stringToDate(rawStartDate);
        Date endDate = rawEndDate == null || rawEndDate.isEmpty() ? null : DateParser.stringToDate(rawEndDate);
        final Iterable<Item> all = itemRepository.findByFilters(year, name, tagId, isIncome, startDate, endDate);
        List<Item> items = new ArrayList<>();
        all.forEach(items::add);
        Collections.sort(items);
        return items;
    }
}
