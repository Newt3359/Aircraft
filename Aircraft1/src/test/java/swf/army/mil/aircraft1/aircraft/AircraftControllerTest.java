package swf.army.mil.aircraft1.aircraft;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
public class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    AircraftService aircraftService;

    Aircraft shadow = new Aircraft(1L, "Shadow", "Ace");
    Aircraft warthog = new Aircraft(2L, "Warthog", "Jim");
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
                .andExpect(jsonPath("$.pilot").value("Ace"));
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
    }

}
