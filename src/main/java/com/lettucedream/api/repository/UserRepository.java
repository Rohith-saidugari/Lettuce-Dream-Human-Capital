package com.lettucedream.api.repository;

import com.lettucedream.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;


@Repository
public interface UserRepository extends JpaRepository<User, String> {



    @Query("select t from User t where t.user_id = :id")
    User findByUserId(@Param("id") String id);


    @Query("select t from User t where (t.firstName = :firstname and t.lastName = :lastname and t.dateOfBirth = :dateOfBirth and t.phoneNumber= :phonenumber)")
    User findByNameBirthday(@Param("firstname") String firstname,@Param("lastname") String lastname, @Param("dateOfBirth") Date dateOfBirth, @Param("phonenumber") long phonenumber);
}
