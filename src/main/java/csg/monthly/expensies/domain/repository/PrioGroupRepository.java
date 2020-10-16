package csg.monthly.expensies.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import csg.monthly.expensies.domain.PrioGroup;

public interface PrioGroupRepository extends CrudRepository<PrioGroup, Integer> {
    List<PrioGroup> findAll();

    PrioGroup findByName(@Param("name") String name);
}
