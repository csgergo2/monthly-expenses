package csg.monthly.expensies.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import csg.monthly.expensies.domain.CustomCounter;
import csg.monthly.expensies.domain.repository.CustomCounterRepositoy;

@Service
public class CustomCounterServcie {

    @Autowired
    private CustomCounterRepositoy customCounterRepositoy;

    public List<CustomCounter> findAll() {
        return customCounterRepositoy.findAll();
    }

    public CustomCounter save(CustomCounter customCounter) {
        return customCounterRepositoy.save(customCounter);
    }
}
