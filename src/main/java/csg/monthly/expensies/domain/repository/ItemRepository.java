package csg.monthly.expensies.domain.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import csg.monthly.expensies.domain.Item;
import csg.monthly.expensies.domain.date.Month;

@Repository
public interface ItemRepository extends CrudRepository<Item, String> {

    List<Item> findAll();

    List<Item> findAllByYearAndMonth(@Param("year") int year, @Param("month") Month month);

    @Query(value = "select year from item group by year", nativeQuery = true)
    List<Integer> findAllYear();

    @Query(value = "select count(*) from item i where i.tag = :tagId", nativeQuery = true)
    int findByTagId(@Param("tagId") int tagId);

    //@formatter:off
    @Query(value = "select * from item i where " +
            "(:year IS NULL OR i.year = :year) AND " +
            "(:month IS NULL OR i.month = :month) AND " +
            "(:name IS NULL OR i.name LIKE %:name%) AND " +
            "(:tagId IS NULL OR i.tag = :tagId) AND " +
            "(:isIncome IS NULL OR i.is_income = :isIncome) AND " +
            "(:startDate IS NULL OR i.date >= :startDate) AND " +
            "(:endDate IS NULL OR i.date <= :endDate)", nativeQuery = true)
    //@formatter:on
    List<Item> findByFilters(@Param("year") Integer year, @Param("month") String month, @Param("name") String name, @Param("tagId") Integer tagId,
                             @Param("isIncome") Boolean isIncome, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
