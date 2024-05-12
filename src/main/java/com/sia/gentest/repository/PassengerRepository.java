package com.sia.gentest.repository;

import com.sia.gentest.domain.entity.Passenger;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    List<Passenger> findByFirstName(String firstName);

    // define custom query using Native SQL with index params
    @Query(value = "select * from passengers e where e.first_name =?1 and e.age =?2", nativeQuery = true)
    List<Passenger> findByNativeSQL(String firstName, int age);

    // define custom query using Native SQL with named params
    @Query(value = "select * from passengers e where e.first_name =:firstName and e.age =:age",
            nativeQuery = true)
    List<Passenger> findByNativeSQLNamed(@Param("firstName") String firstName, @Param("age") int age);
}
