package csg.monthly.expensies.domain.repository;

import org.springframework.data.repository.CrudRepository;

import csg.monthly.expensies.domain.Item;

public interface ItemRepository extends CrudRepository<Item, String> {
}
