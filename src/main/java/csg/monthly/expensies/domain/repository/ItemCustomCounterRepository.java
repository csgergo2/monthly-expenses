package csg.monthly.expensies.domain.repository;

import org.springframework.data.repository.CrudRepository;

import csg.monthly.expensies.domain.ItemCustomCounter;

public interface ItemCustomCounterRepository extends CrudRepository<ItemCustomCounter, ItemCustomCounter.ItemCustomCounterId> {
}
