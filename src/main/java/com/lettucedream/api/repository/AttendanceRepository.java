package com.lettucedream.api.repository;

import com.lettucedream.api.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**************************************************************************
 * @Author: Rohit Saidugari
 * Description: JpaRepository contains api methods to perform CRUD operation in DB.
 * user @Repository and extend to your interface
 * JpaRepository<Attendance, Long>  -- Attendance is model class Long is primary key type
 * NOTES:
 * REVISION HISTORY : None
 * Date:                           By: Rohit Saidugari          Description:
 ***************************************************************************/

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {


    /**
     * A custom query to return an attendance record which does not have clockout time
     * (latest pending attendance record of a user)
     * We need this to perform clockout
     * @param userID
     * @return Attendane Record
     */
    @Query("select t from Attendance t where (t.user.user_id = :id and t.clockOutTime is null)")
    Attendance findLastClockin(@Param("id") String id);
}
