package csg.monthly.expensies.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void save(final Item item) {
        itemRepository.save(item);
    }

    public List<Item> getItems() {
        final List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add);
        return items;
    }
}
