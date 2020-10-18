package csg.monthly.expensies.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csg.monthly.expensies.domain.CustomCounter;
import csg.monthly.expensies.domain.repository.CustomCounterRepository;

@Service
public class CustomCounterServcie {

    @Autowired
    private CustomCounterRepository customCounterRepository;

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
}
