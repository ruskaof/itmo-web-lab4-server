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
     *
     * @param offset
     * @param size
     * @param id
     * @param x
     * @param y
     * @param r
     * @param result
     * @param time
     * @param processingTime
     * @return
     */
    @Query(value = """
            select * from attempt
            where (:my_id="null" or id like :my_id)
            and (:my_x="null" or x like :my_x)
            and (:my_y="null" or y like :my_y)
            and (:my_r="null" or r like :my_r)
            and (:my_result="null" or result like :my_result)
            and (:my_time="null" or attempt_    time like :my_time)
            and (:my_proc_time="null" or processing_time_nanos like :my_proc_time)
            order by id limit :size_n offset :offset_n
            """, nativeQuery = true)
    List<Attempt> getPartAttempts(@Param("offset_n") Integer offset, @Param("size_n") Integer size,
                                  @Param("my_id") String id, @Param("my_x") String x, @Param("my_y") String y,
                                  @Param("my_r") String r, @Param("my_result") String result,
                                  @Param("my_time") String time, @Param("my_proc_time") String processingTime);
}
