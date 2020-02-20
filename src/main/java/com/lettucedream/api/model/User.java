package com.lettucedream.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lettucedream.api.model.enums.RoleType;
import com.lettucedream.api.model.enums.AttendaneStatus;
import com.lettucedream.api.model.enums.State;
import com.lettucedream.api.util.StringPrefixedSequenceIdGenerator;
// import lombok.Data;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;



import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;


@Entity
// Lombok data can be used to autogenerate getter setters
@Data
@Table (name ="users")
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @GenericGenerator(
            name = "emp_seq",
            strategy = "com.lettucedream.api.util.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "LD_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
    private String user_id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    private long phoneNumber;
    private String password;
    // TO DO add organization (fraternity)
    private String streetAddress;
    private String city;
    private State state;
    private int zipCode;
    private AttendaneStatus attendaneStatus = AttendaneStatus.COMPLETE;
    private Date lastLogin = new Date(System.currentTimeMillis());
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Attendance> attandance ;

    /*@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="user_role",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set roles;



    public Set getRoles() {
        return roles;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }

    public User() {
    }





   /* public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }




    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }



    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public List<Attendance> getAttandance() {
        return attandance;
    }

    public void setAttandance(List<Attendance> attandance) {
        this.attandance = attandance;
    }

    public AttendaneStatus getAttendaneStatus() {
        return attendaneStatus;
    }

    public void setAttendaneStatus(AttendaneStatus attendaneStatus) {
        this.attendaneStatus = attendaneStatus;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set getRoles() {
        return roles;
    }

    public void setRoles(Set roles) {
        this.roles = roles;
    }*/
}
