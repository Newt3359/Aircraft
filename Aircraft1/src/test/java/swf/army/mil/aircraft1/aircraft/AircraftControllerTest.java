package swf.army.mil.aircraft1.aircraft;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import swf.army.mil.aircraft1.Pilot.Pilot;

import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AircraftController.class)
@AutoConfigureMockMvc
public class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    AircraftService aircraftService;

    Pilot pilot = new Pilot(1L,"John", "Doe",28);
    Aircraft shadow = new Aircraft(1L, "Shadow", pilot);
    Aircraft warthog = new Aircraft(2L, "Warthog", pilot);
    ArrayList<Aircraft> aircrafts = new ArrayList<Aircraft>();


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAircraft() throws Exception{
        Mockito.when(aircraftService.saveAircraft(any(Aircraft.class))).thenReturn(shadow);
        String shadowJson = objectMapper.writeValueAsString(shadow);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/aircraft")
                .contentType(MediaType.APPLICATION_JSON)
                .content(shadowJson))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.airframe").value("Shadow"))
                .andExpect(jsonPath("$.pilot.id").value(1))
                .andExpect(jsonPath("$.pilot.firstName").value("John"))
                .andExpect(jsonPath("$.pilot.lastName").value("Doe"))
                .andExpect(jsonPath("$.pilot.age").value(28));
        Mockito.verify(aircraftService).saveAircraft(any(Aircraft.class));
    }

    @Test
    void shouldGetAllAircraft() throws Exception{
        aircrafts.add(shadow);
        aircrafts.add(warthog);
        Mockito.when(aircraftService.findAllAircraft()).thenReturn(aircrafts);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/aircraft"))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.pilot").value("Ace"))
                .andExpect(jsonPath("$.*").isArray());
        Mockito.verify(aircraftService).findAllAircraft();

    }

    @Test
    void getAircraftById()throws Exception{
        aircrafts.add(shadow);
        aircrafts.add(warthog);
        Mockito.when(aircraftService.getAnAircraft(1L)).thenReturn(shadow);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/aircraft/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
        Mockito.verify(aircraftService).getAnAircraft(1L);
    }

}
