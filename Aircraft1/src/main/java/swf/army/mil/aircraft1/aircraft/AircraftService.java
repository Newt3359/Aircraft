package swf.army.mil.aircraft1.aircraft;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AircraftService {

    private ArrayList<Aircraft> aircraftList = new ArrayList<Aircraft>();

    public Aircraft saveAircraft(Aircraft aircraft){
        aircraftList.add(aircraft);
        return aircraft;
    }
}
