package swf.army.mil.aircraft1.aircraft;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AircraftServiceTest {

    @Mock AircraftRepository aircraftRepository;

    @InjectMocks
    AircraftService aircraftService;

    Aircraft raptor = new Aircraft(3L, "Raptor", "Kyle");

    @Test
    void shouldSaveAircraft(){
        when(aircraftRepository.save(raptor)).thenReturn(raptor);
    }
}