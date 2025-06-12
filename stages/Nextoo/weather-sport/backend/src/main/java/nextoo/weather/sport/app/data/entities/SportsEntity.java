package nextoo.weather.sport.app.data.entities;

import jakarta.persistence.*;
import nextoo.weather.sport.app.models.Sport;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sports")
public class SportsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sportsSeq")
    @SequenceGenerator(name = "sportsSeq", sequenceName = "sports_id_seq", allocationSize = 1)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private int id;

    @Column
    private String name;

    @Column
    private boolean indoor;

    @Column
    private boolean outdoor;

    @OneToMany(mappedBy = "sportId", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private List<PreferencesEntity> preferences;

    @OneToMany(mappedBy = "sportsEntity", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, orphanRemoval = true)
    private Set<LicenseesEntity> licensees = new LinkedHashSet<>();

    public SportsEntity(int id, String name, boolean indoor, boolean outdoor, List<PreferencesEntity> preferences) {
        this.id = id;
        this.name = name;
        this.indoor = indoor;
        this.outdoor = outdoor;
        this.preferences = preferences;
    }

    public SportsEntity() {

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }

    public void setOutdoor(boolean outdoor) {
        this.outdoor = outdoor;
    }

    public void setPreferences(List<PreferencesEntity> preferences) {
        this.preferences = preferences;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SportsEntity that = (SportsEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public Sport toSport() {
        return new Sport(getId(), getName());
    }
}
