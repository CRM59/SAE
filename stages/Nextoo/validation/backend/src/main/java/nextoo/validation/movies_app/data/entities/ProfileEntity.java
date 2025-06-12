package nextoo.validation.movies_app.data.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid", nullable = false)
    private Integer id;

    private String name;

    @Column(name = "tag", length = 4)
    private String tag;

    private String lang;

    private Boolean adult;

    @OneToMany(mappedBy = "profileEntity", cascade = CascadeType.PERSIST)
    private Set<GenreEntity> genres = new LinkedHashSet<>();

    @OneToMany(mappedBy = "profileEntity", cascade = CascadeType.PERSIST)
    private Set<MoviesWatchedEntity> watcheds = new LinkedHashSet<>();

    public Set<MoviesWatchedEntity> getWatcheds() {
        return watcheds;
    }

    public Set<GenreEntity> getGenres() {
        return genres;
    }

    public void setGenres(Set<GenreEntity> genres) {
        this.genres = genres;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProfileEntity that = (ProfileEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}