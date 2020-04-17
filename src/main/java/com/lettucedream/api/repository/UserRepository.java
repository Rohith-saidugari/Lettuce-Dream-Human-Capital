package com.lettucedream.api.repository;

import com.lettucedream.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;

/**************************************************************************
 * @Author: Rohit Saidugari
 * Description: JpaRepository contains api methods to perform CRUD operation in DB.
 * user @Repository and extend to your interface
 * JpaRepository<User, String>  -- User is model class String is primary key type
 * NOTES:
 * REVISION HISTORY : None
 * Date:                           By: Rohit Saidugari          Description:
 ***************************************************************************/
@Repository
public interface UserRepository extends JpaRepository<User, String> {


    /**
     * a custom query to find user by User ID
     * @param  UserID
     * @return user Record
     */
    @Query("select t from User t where t.user_id = :id")
    User findByUserId(@Param("id") String id);


    /**
     * a custom query to find user by User firstname, Lastname, dateof birth and phone number
     * I dont want  to insert a duplicate record  having same username dob and phone number
     * @param firstname
     * @param lastname
     * @param dateOfBirth
     * @param phonenumber
     * @return user Record
     */

    @Query("select t from User t where (t.firstName = :firstname and t.lastName = :lastname and t.dateOfBirth = :dateOfBirth and t.phoneNumber= :phonenumber)")
    User findByNameBirthday(@Param("firstname") String firstname,@Param("lastname") String lastname, @Param("dateOfBirth") Date dateOfBirth, @Param("phonenumber") long phonenumber);



}
