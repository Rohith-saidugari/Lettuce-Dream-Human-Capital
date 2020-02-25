package com.lettucedream.api.service;

import com.lettucedream.api.model.Attendance;

public interface AttendanceService {

    Attendance addAttendance(Attendance attendance);

    Attendance getById(String id);
    Attendance editAttendance(Attendance attendance);
    Attendance getLastUpdatedRecord(String userId);
}
