package csg.monthly.expensies.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import csg.monthly.expensies.domain.PrioGroup;
import csg.monthly.expensies.domain.Tag;

public interface TagRepository extends CrudRepository<Tag, String> {

    List<Tag> findAll();

    //@formatter:off
    @Query(value = "select a.item_name, count(a.item_name) from ("
            + "select i.name item_name from item i"
            + " left join tag t on i.tag = t.id where i.name = null"
            + " union all"
            + " select t.name tag_name from item i"
            + " right join tag t on i.tag = t.id) a"
            + " group by (a.item_name)"
            + " order by count(a.item_name) desc;", nativeQuery = true)
    //@formatter:on
    List<String[]> findTagsByTheirFrequency();

    List<Tag> findByPrioGroup(@Param("prioGroup") PrioGroup prioGroup);
}
