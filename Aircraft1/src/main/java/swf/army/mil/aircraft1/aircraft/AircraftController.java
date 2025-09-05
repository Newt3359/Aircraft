package swf.army.mil.aircraft1.aircraft;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    @PostMapping
    public Aircraft CreateAircraft(@RequestBody Aircraft aircraft){
        Aircraft aircraft1 = new Aircraft(1L,"Shadow","Ace");
        return aircraft1;
    }
}
