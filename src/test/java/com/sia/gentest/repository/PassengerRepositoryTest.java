package com.sia.gentest.repository;

import com.sia.gentest.domain.entity.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class PassengerRepositoryTest {

    /*@Autowired
    private PassengerRepository passengerRepository;

    private Passenger passenger;

    @BeforeEach
    public void setup() {
        passenger = Passenger.builder()
                .firstName("Ryan")
                .gender(true)
                .age(37)
                .build();
    }

    @DisplayName("JUnit test for save passenger operation")
    @Test
    public void givenPassengerObject_whenSave_thenReturnSavedPassenger() {
        // given

        // when
        Passenger savedPassenger = passengerRepository.save(passenger);

        // then
        assertThat(savedPassenger).isNotNull();
        assertThat(savedPassenger.getId()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for get all passengers operation")
    @Test
    public void givenPassengerList_whenFindAll_thenPassengerList() {
        // given
        Passenger passenger1 = Passenger.builder()
                .firstName("Janny")
                .gender(false)
                .age(25)
                .build();
        passengerRepository.save(passenger);
        passengerRepository.save(passenger1);

        // when
        List<Passenger> passengerList = passengerRepository.findAll();

        // then
        assertThat(passengerList).isNotNull();
        assertThat(passengerList.size()).isEqualTo(2);
    }

    @DisplayName("JUnit test for get passenger by id operation")
    @Test
    public void givenPassengerObject_whenFindById_thenPassengerObject() {
        // given
        passengerRepository.save(passenger);

        // when
        Passenger passengerDB = passengerRepository.findById(passenger.getId()).get();

        // then
        assertThat(passengerDB).isNotNull();
    }

    @DisplayName("JUnit test for get passenger by first name operation")
    @Test
    public void givenPassengerObject_whenFindByFirstName_thenPassengerObject() {
        // given
        passengerRepository.save(passenger);

        // when
        List<Passenger> passengerList = passengerRepository.findByFirstName(passenger.getFirstName());

        // then
        assertThat(passengerList).isNotNull();
        assertThat(passengerList.size()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for update passenger operation")
    @Test
    public void givenPassengerObject_whenUpdatePassenger_thenReturnUpdatedPassenger() {
        // given
        passengerRepository.save(passenger);

        // when
        Passenger passengerDB = passengerRepository.findById(passenger.getId()).get();
        passengerDB.setFirstName("Ryan Update");
        passengerDB.setAge(35);
        Passenger updatedPassenger = passengerRepository.save(passengerDB);

        // then
        assertThat(updatedPassenger.getFirstName()).isEqualTo("Ryan Update");
        assertThat(updatedPassenger.getAge()).isEqualTo(35);
    }

    @DisplayName("JUnit test for delete passenger operation")
    @Test
    public void givenPassengerObject_whenDelete_thenRemovePassenger() {
        // given
        passengerRepository.save(passenger);

        // when
        passengerRepository.delete(passenger);
        Optional<Passenger> passengerOptional = passengerRepository.findById(passenger.getId());

        // then
        assertThat(passengerOptional).isEmpty();
    }

    @DisplayName("JUnit test for custom query using native SQL with index")
    @Test
    public void givenFirstNameAndAge_whenFindByNativeSQL_thenPassengerList() {
        // given
        passengerRepository.save(passenger);

        // when
        List<Passenger> passengerList = passengerRepository.findByNativeSQL(passenger.getFirstName(), passenger.getAge());

        // then
        assertThat(passengerList).isNotNull();
        assertThat(passengerList.size()).isGreaterThan(0);
    }

    @DisplayName("JUnit test for custom query using native SQL with index")
    @Test
    public void givenFirstNameAndAge_whenFindByNativeSQLNamedParams_thenPassengerList() {
        // given
        passengerRepository.save(passenger);

        // when
        List<Passenger> passengerList = passengerRepository.findByNativeSQLNamed(passenger.getFirstName(), passenger.getAge());

        // then
        assertThat(passengerList).isNotNull();
        assertThat(passengerList.size()).isGreaterThan(0);
    }*/
}
