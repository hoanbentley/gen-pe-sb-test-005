package com.sia.gentest.service;

import com.sia.gentest.domain.entity.Passenger;
import com.sia.gentest.domain.dto.PassengerDTO;
import com.sia.gentest.repository.PassengerRepository;

import com.sia.gentest.service.exception.EntityNotFoundException;
import com.sia.gentest.util.jackson.JsonViewPage;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

@Service
@Transactional
@Slf4j
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Optional<PassengerDTO> findPassengerById(Long id) {
        Optional<Passenger> passenger = passengerRepository.findById(id);
        return passenger.map(this::convertToPassengerDTO);
    }
    

    public JsonViewPage<PassengerDTO> findAllPassengers(Pageable pageable) {

        Page<Passenger> passengersPage = passengerRepository.findAll(pageable);

        modelMapper.typeMap(Passenger.class, PassengerDTO.class);
        Page<PassengerDTO> passengerDTOPage = passengersPage
                .map(passenger -> modelMapper.map(passenger, PassengerDTO.class));

        JsonViewPage<PassengerDTO> pagedResult = new JsonViewPage<PassengerDTO>(
                passengerDTOPage.getContent(),
                pageable,
                passengerDTOPage.getTotalElements());
        return pagedResult;
    }

    /**
     * Persist a Passenger entity
     * 
     * @param passengerDTO
     * @return PassengerDTO
     */
    public PassengerDTO savePassenger(PassengerDTO passengerDTO) {
        Passenger passenger = new Passenger();
        modelMapper.map(passengerDTO, passenger);
        return modelMapper.map(passengerRepository.save(passenger), PassengerDTO.class);
    }

    /** 
     * Update an existing Passenger entity
     * 
     * @param passengerDTO
     * @return PassengerDTO
     */
    public PassengerDTO updatePassenger(PassengerDTO passengerDTO) {
        Optional<Passenger> optPassenger = passengerRepository.findById(passengerDTO.getId());
        if (optPassenger.isEmpty())
            throw new EntityNotFoundException();

        Passenger passenger = optPassenger.get();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(passengerDTO, passenger);
        Passenger updatedPassenger = passengerRepository.save(passenger);
        return modelMapper.map(updatedPassenger, PassengerDTO.class);
    }

    public void deletePassengerById(Long id) {
        passengerRepository.deleteById(id);
    }


    private PassengerDTO convertToPassengerDTO(Passenger passenger) {
        return modelMapper.map(passenger, PassengerDTO.class);
    }
}
