package com.lettucedream.api.repository;

import com.lettucedream.api.model.Attendance;
import com.lettucedream.api.model.User;
import com.lettucedream.api.model.enums.AttendaneStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {


    @Query("select t from Attendance t where (t.user.user_id = :id and t.clockOutTime is null)")
    Attendance findLastClockin(@Param("id") String id);
}
