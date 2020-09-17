package csg.monthly.expensies.domain.repository;

import org.springframework.data.repository.CrudRepository;

import csg.monthly.expensies.domain.Tag;

public interface TagRepository extends CrudRepository<Tag, String> {
}
