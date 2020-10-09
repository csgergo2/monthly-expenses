package csg.monthly.expensies.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.date.Month;

@Repository
public interface ItemRepository extends CrudRepository<Item, String> {

    List<Item> findAllByYearAndMonth(@Param("year") int year, @Param("month") Month month);

    @Query(value = "select year from item group by year", nativeQuery = true)
    List<Integer> findAllYear();

    //@formatter:off
    @Query(value = "select * from item i where " +
            "(:year IS NULL OR i.year = :year) AND " +
            "(:name IS NULL OR i.name LIKE %:name%) AND " +
            "(:tagId IS NULL OR i.tag = :tagId)", nativeQuery = true)
    //@formatter:on
    List<Item> findByFilters(@Param("year") Integer year, @Param("name") String name, @Param("tagId") Integer tagId);
}
