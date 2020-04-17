package com.lettucedream.api.service;

import com.lettucedream.api.model.Attendance;

/**************************************************************************
 * @Author: Rohit Saidugari
 * Description: This interface describes what  operations we can perform
 * for a particular model on database
 * NOTES:
 * REVISION HISTORY : None
 * Date:                           By: Rohit Saidugari          Description:
 ***************************************************************************/
public interface AttendanceService {

    Attendance addAttendance(Attendance attendance);

    Attendance getById(String id);
    Attendance editAttendance(Attendance attendance);
    Attendance getLastUpdatedRecord(String userId);
}
