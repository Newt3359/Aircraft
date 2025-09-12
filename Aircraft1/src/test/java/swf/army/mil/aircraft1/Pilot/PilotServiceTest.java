package swf.army.mil.aircraft1.Pilot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PilotServiceTest {

    @Mock PilotRepository pilotRepository;

    @InjectMocks
    PilotService pilotService;

    Pilot john = new Pilot(1L,"John","Doe", 28);
    Pilot frank = new Pilot(2L, "Frank", "Cline", 33);
    ArrayList<Pilot> pilots = new ArrayList<Pilot>();

    @Test
    void shouldSavePilot(){

        when(pilotRepository.save(john)).thenReturn(john);

        Pilot actualPilot = pilotService.savePilot(john);

        verify (pilotRepository, times (1)).save(any(Pilot.class));
        assertThat(actualPilot).isEqualTo(john);
    }

    @Test
    void shouldFindAllPilots(){
        when(pilotRepository.findAll()).thenReturn(pilots);
        List<Pilot> listPilots = pilotService.findAllPilots();
        verify(pilotRepository, times(1)).findAll();
        assertThat(listPilots).isEqualTo(pilots);
    }

    @Test
    void shouldFindPilotById(){

        when(pilotRepository.findById(1L)).thenReturn(Optional.of(john));

        Pilot foundSinglePilot = pilotService.getOnePilot(1L);
        assertThat(foundSinglePilot).isEqualTo(john);
    }
}