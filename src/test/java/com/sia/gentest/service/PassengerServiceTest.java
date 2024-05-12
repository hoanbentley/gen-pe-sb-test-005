package com.sia.gentest.service;

import com.sia.gentest.domain.dto.PassengerDTO;
import com.sia.gentest.domain.entity.Passenger;
import com.sia.gentest.repository.PassengerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class PassengerServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private PassengerService passengerService;

    @DisplayName("JUnit test for saveEmployee method")
    @Test
    public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject(){

        //given
        PassengerDTO passengerDTO = PassengerDTO.builder()
                .firstName("Tony")
                .age(40)
                .gender(true)
                .build();

        Passenger passenger = Passenger.builder()
                .firstName("Ryan")
                .gender(true)
                .age(37)
                .build();
        given(passengerRepository.save(passenger)).willReturn(passenger);

        //when
        PassengerDTO passenger1 = passengerService.savePassenger(passengerDTO);

        //then
        assertThat(passenger1).isNotNull();
    }
}
