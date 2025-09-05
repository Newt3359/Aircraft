package swf.army.mil.aircraft1.aircraft;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class AircraftService {

    private ArrayList<Aircraft> aircraftList = new ArrayList<Aircraft>();

    public Aircraft saveAircraft(Aircraft aircraft){
        aircraftList.add(aircraft);
        return aircraft;
    }

    public Aircraft getAnAircraft(Long id){
        for (Aircraft ac : aircraftList)
            if (ac.getId().equals(id)){
            return ac;

        }
        return null;
    }

    public ArrayList<Aircraft> findAllAircraft(){
        Aircraft shadow = new Aircraft(1L, "Shadow", "Ace");
        Aircraft warthog = new Aircraft(2L, "Warthog", "Jim");
        aircraftList.add(shadow);
        aircraftList.add(warthog);
        return aircraftList;
    }

}
