package com.lettucedream.api.service.impl;

import com.lettucedream.api.model.Attendance;
import com.lettucedream.api.repository.AttendanceRepository;
import com.lettucedream.api.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**************************************************************************
 * @Author: Rohit Saidugari
 * Description: Serviec class actually performs operations
 * NOTES:
 * REVISION HISTORY : None
 * Date:                           By: Rohit Saidugari          Description:
 ***************************************************************************/
@Service
public class AttendanceServiceImpl implements AttendanceService {

    /**
     * Injecting bean AttendanceRepository
     */
    @Autowired
    private AttendanceRepository attendanceRepository;

    /**
     * This method inserts an attendance record in the database
     * @param attendance
     * @return created attendance record
     */
    @Override
    public Attendance addAttendance(Attendance attendance) {
        return attendanceRepository.saveAndFlush(attendance);
    }

    /**
     * This method retrives an attendance record by its ID
     * At this stange I dont need it so returning null;
     * @param id
     * @return attendance record
     */
    @Override
    // TO Do
    public Attendance getById(String id) {
        return null;
    }

    /**
     * This method Updates existing attendance record
     * @param attendance
     * @return updated attendance record
     */
    @Override
    public Attendance editAttendance(Attendance attendance) {
      return  attendanceRepository.saveAndFlush(attendance);
    }

    /**
     * Gets the last updated record by user
     * This method uses a custom query and fetches the record that needs to be clocked out
     * @param userId
     * @return last updated record
     */
    @Override
    public Attendance getLastUpdatedRecord(String userId){
        return attendanceRepository.findLastClockin(userId);
    }
}
