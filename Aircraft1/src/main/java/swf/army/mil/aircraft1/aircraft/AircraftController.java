package swf.army.mil.aircraft1.aircraft;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService){
        this.aircraftService = aircraftService;
    }

    @PostMapping
    public Aircraft CreateAircraft(@RequestBody Aircraft aircraft){
        return  aircraftService.saveAircraft(aircraft);
//        Aircraft aircraft1 = new Aircraft(1L,"Shadow","Ace");
//        return aircraft1;
    }

    @GetMapping("/{id}")
    public Aircraft getAnAircraft(@PathVariable Long id){
    return aircraftService.getAnAircraft(id);
    }

    @GetMapping()
    public List<Aircraft> getAircraft(){
        return aircraftService.findAllAircraft();
    }

    @DeleteMapping("/{id}")
    public String deleteAircraft(@PathVariable Long id){
        aircraftService.deleteAircraft(id);
        return "Deleted";
    }
}
