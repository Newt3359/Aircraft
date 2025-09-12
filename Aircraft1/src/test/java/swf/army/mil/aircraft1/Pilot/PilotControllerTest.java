package swf.army.mil.aircraft1.Pilot;
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

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PilotController.class)
@AutoConfigureMockMvc
class PilotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    PilotService pilotService;

    Pilot john = new Pilot(1L,"John","Doe", 28);
    Pilot frank = new Pilot(2L, "Frank", "Cline", 33);
    ArrayList<Pilot> pilots = new ArrayList<Pilot>();

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreatePilot() throws Exception {
        Mockito.when(pilotService.savePilot(any(Pilot.class))).thenReturn(john);
        String johnJson = objectMapper.writeValueAsString(john);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/pilot")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(johnJson))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.age").value(28));
        Mockito.verify(pilotService).savePilot(any(Pilot.class));
    }

    @Test
    void shouldGetAllAircraft() throws Exception{
        pilots.add(john);
        pilots.add(frank);
        Mockito.when(pilotService.findAllPilots()).thenReturn(pilots);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/pilot"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*").isArray());
        Mockito.verify(pilotService).findAllPilots();
    }

    @Test
    void getPilotById()throws Exception{
        Mockito.when(pilotService.getOnePilot(1L)).thenReturn(john);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/pilot/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
        Mockito.verify(pilotService).getOnePilot(1L);
    }


}