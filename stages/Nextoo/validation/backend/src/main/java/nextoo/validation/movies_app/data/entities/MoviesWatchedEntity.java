package nextoo.validation.movies_app.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "watched")
public class MoviesWatchedEntity {
    @EmbeddedId
    private MoviesWatchedEntityId id;

    @MapsId("pid")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pid")
    private ProfileEntity profileEntity;

    public MoviesWatchedEntityId getId() {
        return id;
    }

    public void setId(MoviesWatchedEntityId id) {
        this.id = id;
    }

}