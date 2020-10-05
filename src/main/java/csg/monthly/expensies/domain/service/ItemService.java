package csg.monthly.expensies.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.date.Month;
import csg.monthly.expensies.domain.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

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
}
