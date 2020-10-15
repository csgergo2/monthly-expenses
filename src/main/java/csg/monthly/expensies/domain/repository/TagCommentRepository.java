package csg.monthly.expensies.domain.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import csg.monthly.expensies.domain.TagComment;

public interface TagCommentRepository extends CrudRepository<TagComment, Integer> {

    @Query("select * from tag_comment tc where tc.tag = &tag")
    TagComment findByTag(@Param("tag") int tag);

    void deleteByTag(@Param("tag") int tag);
}
