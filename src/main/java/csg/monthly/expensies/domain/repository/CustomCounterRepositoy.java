package csg.monthly.expensies.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import csg.monthly.expensies.domain.CustomCounter;

public interface CustomCounterRepositoy extends CrudRepository<CustomCounter, Integer> {
    List<CustomCounter> findAll();
}
