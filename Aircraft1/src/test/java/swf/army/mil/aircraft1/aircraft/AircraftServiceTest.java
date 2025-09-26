package swf.army.mil.aircraft1.aircraft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import swf.army.mil.aircraft1.Pilot.Pilot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AircraftServiceTest {

    @Mock AircraftRepository aircraftRepository;

    @InjectMocks
    AircraftService aircraftService;

    ArrayList <Aircraft> aircrafts = new ArrayList<Aircraft>();
    Pilot pilot = new Pilot("John", "Doe",28);
    Aircraft raptor = new Aircraft("Raptor", pilot);
    Aircraft shadow = new Aircraft("Shadow",pilot);


    @Test
    void shouldSaveAircraft(){
        //arrange
        when(aircraftRepository.save(raptor)).thenReturn(raptor);
        // act
        Aircraft actualAircraft = aircraftService.saveAircraft(raptor);
        //assert
        verify (aircraftRepository, times (1)).save(any(Aircraft.class));
        assertThat(actualAircraft).isEqualTo(raptor);
    }

    @Test
    void shouldFindAllAircraft(){
        aircrafts.add(raptor);
        aircrafts.add(shadow);

        when(aircraftRepository.findAll()).thenReturn(aircrafts);

        List<Aircraft> listAllAircraft = aircraftService.findAllAircraft();

        verify (aircraftRepository, times(1)).findAll();
        assertThat(listAllAircraft).isEqualTo(aircrafts);
    }

    @Test
    void shouldFindAircraftById(){

        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(shadow));

        Aircraft foundSingleAircraft = aircraftService.getAnAircraft(1L);

        verify (aircraftRepository, times(1)).findById(1L);
        assertThat(foundSingleAircraft).isEqualTo(shadow);
    }

    @Test
    void shouldDeleteAircraft(){

        aircraftService.deleteAircraft(1L);
        verify(aircraftRepository, times(1)).deleteById(1L);
    }
}