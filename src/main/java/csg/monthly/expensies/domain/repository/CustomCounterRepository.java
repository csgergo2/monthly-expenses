package csg.monthly.expensies.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import csg.monthly.expensies.domain.CustomCounter;

public interface CustomCounterRepository extends CrudRepository<CustomCounter, Integer> {
    List<CustomCounter> findAll();

    Optional<CustomCounter> findByName(@Param("name") String name);
}
