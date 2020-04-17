package com.lettucedream.api.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table (name ="attendance")

/**************************************************************************
 * @Author: Rohit Saidugari
 * Description: Model Class Attendance, Each member variable represents an attribute in database table
 * NOTES:
 * REVISION HISTORY : None
 * Date:                           By: Rohit Saidugari          Description:
 ***************************************************************************/
public class Attendance {

    /**
     * A unique primary key is required for every entity
     * @Id is used to dennote primay key in models
     * we dont need any custom id for attendance id, We can use springs inbuilt
     * auto generation strategy
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long attendanceId;
    private Date clockInTime;
    private Date clockOutTime;
    /**
     * Many to one relationship between attendance and user
     * @JsonBackReference is the back part of reference – it will be omitted from serialization
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

   public Attendance(User user){
        this.user = user;
    }

    public Attendance(){

    }

    public long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Date getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(Date clockInTime) {
        this.clockInTime = clockInTime;
    }

    public Date getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(Date clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
