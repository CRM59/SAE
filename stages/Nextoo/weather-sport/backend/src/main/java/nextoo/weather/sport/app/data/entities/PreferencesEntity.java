package nextoo.weather.sport.app.data.entities;

import jakarta.persistence.*;
import nextoo.weather.sport.app.models.WeatherType;

@Entity
@Table(name = "preferences")
public class PreferencesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preferencesSeq")
    @SequenceGenerator(name = "preferencesSeq", sequenceName = "preferences_id_seq", allocationSize = 1)
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "id_sport", referencedColumnName = "id")
    private SportsEntity sportId;

    @Enumerated(EnumType.STRING)
    @Column(name = "weather_type")
    private WeatherType weatherType;


    public PreferencesEntity(int id, WeatherType weatherType) {
        this.id = id;
        this.weatherType = weatherType;
    }

    public PreferencesEntity() {

    }


    public void setSportId(SportsEntity sportId) {
        this.sportId = sportId;
    }

    public void setWeatherType(WeatherType weatherType) {
        this.weatherType = weatherType;
    }
}
