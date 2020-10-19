package csg.monthly.expensies.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csg.monthly.expensies.domain.CustomCounter;
import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.ItemCustomCounter;
import csg.monthly.expensies.domain.repository.CustomCounterRepository;
import csg.monthly.expensies.domain.repository.ItemCustomCounterRepository;

@Service
public class CustomCounterServcie {

    @Autowired
    private CustomCounterRepository customCounterRepository;
    @Autowired
    private ItemCustomCounterRepository itemCustomCounterRepository;

    public List<CustomCounter> findAll() {
        return customCounterRepository.findAll();
    }

    public CustomCounter save(CustomCounter customCounter) {
        if (customCounter.getName().isEmpty()) {
            throw new NullPointerException("CustomCounter name is empty");
        }
        return customCounterRepository.save(customCounter);
    }

    public Optional<CustomCounter> findByName(String name) {
        return customCounterRepository.findByName(name);
    }

    public void saveItemForCustomCounter(Item item, CustomCounter customCounter) {
        itemCustomCounterRepository.save(new ItemCustomCounter(customCounter.getId(), item.getId()));
    }

    public void removeItemFromCustomCounter(Item item, CustomCounter customCounter) {
        itemCustomCounterRepository.deleteById(new ItemCustomCounter.ItemCustomCounterId(customCounter.getId(), item.getId()));
    }

    public void delete(CustomCounter customCounter) {
        customCounterRepository.delete(customCounter);
    }
}
