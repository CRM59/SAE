package nextoo.validation.movies_app.data.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "genres")
public class GenreEntity {
    @EmbeddedId
    private GenreEntityId id;

    @MapsId("pid")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    private ProfileEntity profileEntity;

    public GenreEntityId getId() {
        return id;
    }

    public void setId(GenreEntityId id) {
        this.id = id;
    }
}