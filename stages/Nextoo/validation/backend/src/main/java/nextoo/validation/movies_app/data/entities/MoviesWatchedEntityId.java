package nextoo.validation.movies_app.data.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MoviesWatchedEntityId implements Serializable {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    private ProfileEntity pid;

    private Integer mid;

    public ProfileEntity getPid() {
        return pid;
    }

    public void setPid(ProfileEntity pid) {
        this.pid = pid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MoviesWatchedEntityId that = (MoviesWatchedEntityId) o;
        return Objects.equals(pid.getId(), that.pid.getId()) && Objects.equals(mid, that.mid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid.getId(), mid);
    }
}