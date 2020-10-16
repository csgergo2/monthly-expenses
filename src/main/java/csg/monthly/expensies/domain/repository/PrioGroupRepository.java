package csg.monthly.expensies.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import csg.monthly.expensies.domain.PrioGroup;

public interface PrioGroupRepository extends CrudRepository<PrioGroup, Integer> {
    List<PrioGroup> findAll();
}
