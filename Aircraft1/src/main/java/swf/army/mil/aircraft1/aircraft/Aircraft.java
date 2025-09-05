package swf.army.mil.aircraft1.aircraft;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Aircraft {
    @Id
        private Long id;
        private String airframe;
        private String pilot;


        public Aircraft(){this(null,"none","none");}

        public Aircraft(Long id, String airframe, String pilot){
            this.id = id;
            this.airframe = airframe;
            this.pilot = pilot;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAirframe() {
        return airframe;
    }

    public void setAirframe(String airframe) {
        this.airframe = airframe;
    }

    public String getPilot() {
        return pilot;
    }

    public void setPilot(String pilot) {
        this.pilot = pilot;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", airframe='" + airframe + '\'' +
                ", pilot='" + pilot + '\'' +
                '}';
    }
}
