package nextoo.weather.sport.app.data.entities;


import jakarta.persistence.*;
import nextoo.weather.sport.app.models.Licensees;

import java.time.LocalDate;

@Entity
@Table(name = "licensees")
public class LicenseesEntity {

    @EmbeddedId
    private LicenseesEntityId id;

    @Column(name = "start_date_license")
    private LocalDate startDate;

    @Column(name = "end_date_license")
    private LocalDate endDate;

    @MapsId("personId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person")
    private PersonsEntity personsEntity;

    @MapsId("sportId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sport")
    private SportsEntity sportsEntity;

    public SportsEntity getSportsEntity() {
        return sportsEntity;
    }

    public void setSportsEntity(SportsEntity sportsEntity) {
        this.sportsEntity = sportsEntity;
    }


    public void setPersonsEntity(PersonsEntity personsEntity) {
        this.personsEntity = personsEntity;
    }


    public LicenseesEntity() {
    }

    public LicenseesEntityId getId() {
        return id;
    }

    public void setId(LicenseesEntityId id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Licensees toLicensees() {
        return new Licensees(
                getId().getPersonId().toPerson(),
                getId().getSportId().getName(),
                getStartDate(),
                getEndDate()
        );
    }
}
