package swf.army.mil.aircraft1.Pilot;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pilot")
public class PilotController {

    private final PilotService pilotService;
//
    public PilotController(PilotService pilotService){this.pilotService = pilotService;}

    @PostMapping
    public Pilot createPilot(@RequestBody Pilot pilot){
//        Pilot pilot1 = new Pilot(1L,"John","Doe", 28);
//        return pilotService.savePilot(pilot1);
        return pilotService.savePilot(pilot);
    }

    @GetMapping()
    public List<Pilot> getAllPilots(){


        return pilotService.findAllPilots();
    }

    @GetMapping("/{id}")
    public Pilot getAPilot(@PathVariable Long id){
        return pilotService.getOnePilot(id);
    }

}
