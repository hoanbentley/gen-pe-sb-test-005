package com.sia.gentest.repository;

import com.sia.gentest.domain.entity.Passenger;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {


}
