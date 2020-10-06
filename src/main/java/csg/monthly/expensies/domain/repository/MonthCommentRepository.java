package csg.monthly.expensies.domain.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import csg.monthly.expensies.domain.MonthComment;
import csg.monthly.expensies.domain.date.Month;

public interface MonthCommentRepository extends CrudRepository<MonthComment, Integer> {

    @Query("select * from MonthComment where month = &month")
    MonthComment findByMonth(@Param("month") Month month);

    void deleteByMonth(Month month);
}
