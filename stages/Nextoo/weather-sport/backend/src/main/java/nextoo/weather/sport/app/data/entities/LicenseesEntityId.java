package nextoo.weather.sport.app.data.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LicenseesEntityId implements Serializable {

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_person", referencedColumnName = "id")
    private PersonsEntity personId;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "id_sport", referencedColumnName = "id")
    private SportsEntity sportId;

    public SportsEntity getSportId() {
        return sportId;
    }

    public void setSportId(SportsEntity sportId) {
        this.sportId = sportId;
    }

    public PersonsEntity getPersonId() {
        return personId;
    }

    public void setPersonId(PersonsEntity personId) {
        this.personId = personId;
    }


    public LicenseesEntityId() {

    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LicenseesEntityId that = (LicenseesEntityId) o;
        return personId == that.personId && sportId == that.sportId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, sportId);
    }

}
