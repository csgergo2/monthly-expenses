package csg.monthly.expensies.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import csg.monthly.expensies.domain.Tag;

public interface TagRepository extends CrudRepository<Tag, String> {

    List<Tag> findAll();
}
