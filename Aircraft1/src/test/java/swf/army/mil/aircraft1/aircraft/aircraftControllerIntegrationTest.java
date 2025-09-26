package swf.army.mil.aircraft1.aircraft;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import swf.army.mil.aircraft1.Pilot.Pilot;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@TestPropertySource("classpath:application-test.yaml")
@SpringBootTest
@AutoConfigureMockMvc
public class aircraftControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    Pilot pilot = new Pilot("John", "Doe",28);
    Aircraft shadow = new Aircraft("Shadow", pilot);
    Aircraft warthog = new Aircraft("Warthog", pilot);
    ArrayList<Aircraft> aircrafts = new ArrayList<Aircraft>();

    @Test
    void shouldSaveAircraft()throws Exception{
        String shadowJson = objectMapper.writeValueAsString(shadow);
        MvcResult result = mockMvc.perform(post("/api/aircraft")
                .contentType(MediaType.APPLICATION_JSON)
                .content(shadowJson))
                .andReturn();

        String responseType = result.getResponse().getContentType();
        String responseBody = result.getResponse().getContentAsString();
        Aircraft aircraftResult = objectMapper.readValue(responseBody, Aircraft.class);

        assertEquals(aircraftResult.getAirframe(), shadow.getAirframe());
        assertEquals(responseType.toString(), "application/json");
        assertEquals(aircraftResult.getPilot().getFirstName(), shadow.getPilot().getFirstName());

    }

    @Test
    void shouldGetAllAircraft()throws Exception{
        aircrafts.add(shadow);
        aircrafts.add(warthog);
        mockMvc.perform(post("/api/aircraft")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(aircrafts)))
                .andReturn();
    }

}
