package com.ruskaof.itmoweblab4server.repository;

import com.ruskaof.itmoweblab4server.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttemptsRepository extends JpaRepository<Attempt, Integer> {
    /**
     * Makes a search in the database by the given parameters with the given offset and size
     * Can accept nulls as size, offset, id, x, y, r, result, time, username parameters
     *
     * @param offset the offset of the search, must not be null, must be >= 0
     * @param size   the size of the search, must not be null, must be >= 0
     */
    @Query(value = """
            select * from attempt
            where (:my_id is null or :my_id="" or id like :my_id)
            and (:my_x is null or :my_x="" or x like :my_x%)
            and (:my_y is null or :my_y="" or y like :my_y%)
            and (:my_r is null or :my_r="" or r like :my_r)
            and (:my_result is null or :my_result="" or result=0 and (:my_result="false" or :my_result="miss" or :my_result="0") or result=1 and (:my_result="true" or :my_result="hit" or :my_result="1"))
            and (:my_time is null or :my_time="" or attempt_time like %:my_time%)
            and (:my_proc_time is null or :my_proc_time="" or processing_time_nanos like :my_proc_time%)
            order by id limit :size_n offset :offset_n
            """, nativeQuery = true)
    // The if a value is null it is an empty string
    List<Attempt> getPartAttempts(@Param("offset_n") Integer offset, @Param("size_n") Integer size,
                                  @Param("my_id") String id, @Param("my_x") String x, @Param("my_y") String y,
                                  @Param("my_r") String r, @Param("my_result") String result,
                                  @Param("my_time") String time, @Param("my_proc_time") String processingTime);
}
