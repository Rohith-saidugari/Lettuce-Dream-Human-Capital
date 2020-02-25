package com.lettucedream.api.service.impl;

import com.lettucedream.api.model.Attendance;
import com.lettucedream.api.model.User;
import com.lettucedream.api.repository.AttendanceRepository;
import com.lettucedream.api.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Override
    public Attendance addAttendance(Attendance attendance) {
        Attendance createdattendance = attendanceRepository.saveAndFlush(attendance);
        return createdattendance;
    }

    @Override
    public Attendance getById(String id) {
        return null;
    }

    @Override
    public Attendance editAttendance(Attendance attendance) {
      return  attendanceRepository.saveAndFlush(attendance);
    }

    @Override
    public Attendance getLastUpdatedRecord(String userId){
        return attendanceRepository.findLastClockin(userId);
    }
}
