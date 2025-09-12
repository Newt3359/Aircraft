package swf.army.mil.aircraft1.Pilot;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PilotService {

    private PilotRepository pilotRepository;

    public PilotService(PilotRepository pilotRepository){
        this.pilotRepository = pilotRepository;
    }
//    private ArrayList<Pilot> pilotList = new ArrayList<Pilot>();

    public Pilot savePilot(Pilot pilot){
//        pilotList.add(pilot);
//        return pilot;
        return pilotRepository.save(pilot);
    }

    public List<Pilot> findAllPilots(){
//        Pilot john = new Pilot(1L,"John","Doe", 28);
//        Pilot frank = new Pilot(2L, "Frank", "Cline", 33);
//        ArrayList<Pilot> pilots = new ArrayList<Pilot>();
//        pilots.add(john);
//        pilots.add(frank);
//        return pilots;
        return pilotRepository.findAll();
    }

    public Pilot getOnePilot(Long id){
//        for (Pilot p : pilotList)
//            if (p.getId().equals(id)){
//                return p;
//            }
//        return null;
        return pilotRepository.findById(id).orElse(null);
    }
}
