package com.sia.gentest.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.sia.gentest.IntegrationTest;
import com.sia.gentest.domain.dto.PassengerDTO;
import com.sia.gentest.domain.entity.Passenger;
import com.sia.gentest.repository.PassengerRepository;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;

@IntegrationTest
@Transactional
@AutoConfigureMockMvc
@Slf4j
public class PassengerControllerIT {

    private static final String ENTITY_API_URL = "/api/passengers";


    private static final String FIRSTNAME = "TXT";
    private static final String UPDATED_FIRSTNAME = "TXT";

    private static final int AGE = 1;
    private static final int UPDATED_AGE = 2;

    private static final boolean GENDER = false;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private MockMvc restPassengerMockMvc;

    public static Passenger getMockPassenger() {
        return new Passenger()
                .setFirstName(FIRSTNAME)
                .setAge(AGE)
                .setGender(GENDER);
    }

    public static PassengerDTO getMockPassengerDTO() {
        return new PassengerDTO()
                .setFirstName(FIRSTNAME)
                .setAge(AGE)
                .setGender(GENDER);
    }

    @Test
    void getPassenger() throws Exception {
        // Initialize the database
        Passenger passenger = getMockPassenger();
        passengerRepository.saveAndFlush(passenger);
        int databaseSizeBeforeGet = passengerRepository.findAll().size();

        // Get the Passenger
        restPassengerMockMvc
                .perform(get(ENTITY_API_URL + "/{uid}", passenger.getId()))
                .andExpect(status().isOk());

        // Validate the Passenger in the database
        List<Passenger> passengerList = passengerRepository.findAll();
        assertThat(passengerList).hasSize(databaseSizeBeforeGet);
        Passenger testPassenger = passengerList.get(passengerList.size() - 1);
        assertNotNull(testPassenger.getId());
        assertThat(testPassenger.getFirstName()).isEqualTo(passenger.getFirstName());
        assertThat(testPassenger.getAge()).isEqualTo(passenger.getAge());
        assertThat(testPassenger.isGender()).isEqualTo(passenger.isGender());
    }

    @Test
    void createPassenger() throws Exception {
        int databaseSizeBeforeCreate = passengerRepository.findAll().size();
        PassengerDTO passengerDTO = getMockPassengerDTO();

        // Create the Passenger
        restPassengerMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtil.convertObjectToJsonBytes(passengerDTO)))
                .andExpect(status().isCreated());

        // Validate the Passenger in the database
        List<Passenger> passengerList = passengerRepository.findAll();
        assertThat(passengerList).hasSize(databaseSizeBeforeCreate + 1);
        Passenger testPassenger = passengerList.get(passengerList.size() - 1);
        assertNotNull(testPassenger.getId());
        assertThat(testPassenger.getFirstName()).isEqualTo(passengerDTO.getFirstName());
        assertThat(testPassenger.getAge()).isEqualTo(passengerDTO.getAge());
        assertThat(testPassenger.isGender()).isEqualTo(passengerDTO.isGender());
    }

    @Test
    void updatePassenger() throws Exception {
        // Initialize the database
        Passenger passenger = getMockPassenger();
        passengerRepository.saveAndFlush(passenger);

        int databaseSizeBeforeUpdate = passengerRepository.findAll().size();

        Passenger retrievedPassenger = passengerRepository.findById(passenger.getId()).orElseThrow();
        PassengerDTO passengerDTO = new PassengerDTO()
                .setFirstName(FIRSTNAME)
                .setAge(AGE)
                .setGender(GENDER);


        // Update the Passenger
        restPassengerMockMvc
                .perform(
                        put(ENTITY_API_URL + "/{id}", retrievedPassenger.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(passengerDTO)))
                .andExpect(status().isOk());

        // Validate the Passenger in the database
        List<Passenger> passengerList = passengerRepository.findAll();
        assertThat(passengerList).hasSize(databaseSizeBeforeUpdate);
        Passenger testPassenger = passengerList.get(passengerList.size() - 1);
        assertNotNull(testPassenger.getId());
        assertThat(testPassenger.getFirstName()).isEqualTo(passengerDTO.getFirstName());
        assertThat(testPassenger.getAge()).isEqualTo(passengerDTO.getAge());
        assertThat(testPassenger.isGender()).isEqualTo(passengerDTO.isGender());
    }

    @Test
    void partialUpdatePassenger() throws Exception {
        // Initialize the database
        Passenger passenger = getMockPassenger();
        passengerRepository.saveAndFlush(passenger);
        int databaseSizeBeforeUpdate = passengerRepository.findAll().size();

        Passenger retrievedPassenger = passengerRepository.findById(passenger.getId()).orElseThrow();
        PassengerDTO passengerDTO = new PassengerDTO();
        passengerDTO
                .setFirstName(UPDATED_FIRSTNAME)
                .setAge(UPDATED_AGE);

        // Update the Passenger
        restPassengerMockMvc
                .perform(
                        put(ENTITY_API_URL + "/{id}", retrievedPassenger.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtil.convertObjectToJsonBytes(passengerDTO)))
                .andExpect(status().isOk());

        // Validate the Passenger in the database
        List<Passenger> passengerList = passengerRepository.findAll();
        assertThat(passengerList).hasSize(databaseSizeBeforeUpdate);
        Passenger testPassenger = passengerList.get(passengerList.size() - 1);
        assertThat(testPassenger.getFirstName()).isEqualTo(passengerDTO.getFirstName());
        assertThat(testPassenger.getAge()).isEqualTo(passengerDTO.getAge());
    }

    @Test
    void deletePassenger() throws Exception {
        // Initialize the database
        Passenger passenger = getMockPassenger();
        passengerRepository.saveAndFlush(passenger);
        int databaseSizeBeforeDelete = passengerRepository.findAll().size();

        // Delete the Passenger
        restPassengerMockMvc
            .perform(
                delete(ENTITY_API_URL + "/{id}", passenger.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Passenger> passengerList = passengerRepository.findAll();
        assertThat(passengerList).hasSize(databaseSizeBeforeDelete - 1);
    }

}
